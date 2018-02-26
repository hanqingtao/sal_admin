/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.org.web;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ambition.agile.common.config.Global;
import com.ambition.agile.common.ftp.FtpConfigHolder;
import com.ambition.agile.common.ftp.FtpUtils;
import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.util.ExcelExportUtil;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.common.utils.StringUtils;
import com.ambition.agile.modules.org.entity.Org;
import com.ambition.agile.modules.org.entity.OrgCheck;
import com.ambition.agile.modules.org.entity.OrgProjectExcel;
import com.ambition.agile.modules.org.service.OrgCheckService;
import com.ambition.agile.modules.org.service.OrgService;
import com.ambition.agile.modules.pro.service.ProjectService;
import com.ambition.agile.modules.sys.entity.User;
import com.sun.tools.corba.se.idl.StringGen;

/**
 * 抽查管理Controller
 * @author harry
 * @version 2017-10-23
 */
@Controller
@RequestMapping(value = "${adminPath}/org/orgCheck")
public class OrgCheckController extends BaseController {

	@Autowired
	private OrgCheckService orgCheckService;
	@Autowired
	private OrgService orgService;
	@Autowired
	private ProjectService projectService;
	
	@ModelAttribute
	public OrgCheck get(@RequestParam(required=false) String id) {
		OrgCheck entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = orgCheckService.get(id);
		}
		if (entity == null){
			entity = new OrgCheck();
		}
		return entity;
	}
	
	@RequiresPermissions("org:orgCheck:view")
	@RequestMapping(value = {"list", ""})
	public String list(OrgCheck orgCheck, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OrgCheck> page = orgCheckService.findPage(new Page<OrgCheck>(request, response), orgCheck); 
		model.addAttribute("page", page);
		model.addAttribute("orgCheckS", orgCheck);
		return "modules/org/orgCheckList";
	}

	@RequiresPermissions("org:orgCheck:view")
	@RequestMapping(value = "form")
	public String form(OrgCheck orgCheck, Model model) {
		if(orgCheck != null && orgCheck.getId() != null){
			orgCheck.setRateOrg(orgCheck.getRateOrg() * 100);
			orgCheck.setRateProject(orgCheck.getRateProject() * 100);
		}
		model.addAttribute("orgCheck", orgCheck);
		return "modules/org/orgCheckForm";
	}

	@RequiresPermissions("org:orgCheck:edit")
	@RequestMapping(value = "save")
	public String save(OrgCheck orgCheck, Model model, RedirectAttributes redirectAttributes,HttpSession session) {
		if (!beanValidator(model, orgCheck)){
			return form(orgCheck, model);
		}
		String sstring = System.currentTimeMillis()+"";
		orgCheck.setSn(sstring);
		//随机一个区域，百分比机构，每个机构百分比项目，生成Excel返回文件地址
		Integer areaId = orgCheckService.getIntAreaId();
		Double rateOrg = orgCheck.getRateOrg(); //待获取的机构个数百分比
		Double rateOrg_d = rateOrg / 100;
		orgCheck.setRateOrg(rateOrg_d);
		Double ratePro = orgCheck.getRateProject(); //项目百分比
		Double ratePro_d = ratePro / 100;
		orgCheck.setRateProject(ratePro_d);
		
		//获取数据生成Excel到服务器,返回服务器Excel地址
		String filePath = genExcelPath(areaId,rateOrg_d,ratePro_d,sstring);
		
		orgCheck.setPath(filePath);
		orgCheck.setCreateDate(new Date());
		orgCheck.setCreateBy((User)session.getAttribute("user"));
		
		orgCheckService.save(orgCheck);
		addMessage(redirectAttributes, "保存抽查管理成功");
		return "redirect:"+Global.getAdminPath()+"/org/orgCheck/?repage";
	}
	
	/**
	 * 生成Excel返回服务器文件地址
	 * @param areaId
	 * @param rateOrg_d
	 * @param ratePro_d
	 * @return 返回Excel文件地址
	 */
	private String genExcelPath(Integer areaId, Double rateOrg_d, Double ratePro_d,String sstring) {
		Integer orgCountInarea = orgService.getOrgCountByAreaId(areaId); //该区域机构个数
		Long num = Math.round(orgCountInarea.intValue() * rateOrg_d);
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("areaId", areaId);
		paramMap.put("num", num.intValue());
		List<Org> limitOrgs = orgService.getLimitOrgs(paramMap); //获取区域下，百分比个Org
		//再获取Org下的项目Pro
		List<OrgProjectExcel> pList = new LinkedList<>();
		for(Org org : limitOrgs){
			Integer orgId = Integer.parseInt(org.getId());
			//获取该机构下项目总数
			Integer proCount = projectService.getCountByOrgId(orgId);
			long proNum = Math.round(proCount.intValue() * ratePro_d);
			if(proNum > 0){
				paramMap.put("orgId", orgId);
				paramMap.put("proNum", proNum); 
				List<OrgProjectExcel> pList1 = projectService.getLimitProjects(paramMap);
				if( pList1 != null && pList1.size() > 0){
					pList.addAll(pList1);
				}
			}
		}
		//生成Excel
		String path = genExcelReturnPath(pList,sstring);
		
		return path;
	}
	/**
	 * 生成Excel 上传FTP
	 * @param pList
	 * @param sstring
	 * @return
	 */
	private String genExcelReturnPath(List<OrgProjectExcel> pList,String sstring) {
		try {
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			
			ExcelExportUtil<OrgProjectExcel> excelExportUtil = new ExcelExportUtil<>();
			
			String[] headers = {"地区","代理机构名称","统一社会信用代码","项目名称","项目编号"};
			
			excelExportUtil.exportExcel("调查名单", headers, pList, outputStream, null);
			InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
			FtpUtils.uploadFile("/excel", sstring+".xls", inputStream);
			String dbPath = "/excel/" + sstring + ".xls";
			String path = FtpConfigHolder.getFtpFileAddress() + dbPath;
			return path;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequiresPermissions("org:orgCheck:edit")
	@RequestMapping(value = "delete")
	public String delete(OrgCheck orgCheck, RedirectAttributes redirectAttributes) {
		orgCheckService.delete(orgCheck);
		addMessage(redirectAttributes, "删除抽查管理成功");
		return "redirect:"+Global.getAdminPath()+"/org/orgCheck/?repage";
	}

}