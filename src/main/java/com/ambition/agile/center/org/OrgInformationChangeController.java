/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.center.org;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.junit.runner.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ambition.agile.common.BaseConfigHolder;
import com.ambition.agile.common.config.Global;
import com.ambition.agile.common.ftp.FtpConfigHolder;
import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.util.ComUtil;
import com.ambition.agile.common.util.FtpUtils;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.modules.org.constant.OrgConstant;
import com.ambition.agile.modules.org.entity.Org;
import com.ambition.agile.modules.org.entity.OrgAchieve;
import com.ambition.agile.modules.org.entity.OrgQualification;
import com.ambition.agile.modules.org.entity.OrgStaff;
import com.ambition.agile.modules.org.entity.OrgUser;
import com.ambition.agile.modules.org.service.OrgAchieveService;
import com.ambition.agile.modules.org.service.OrgQualificationService;
import com.ambition.agile.modules.org.service.OrgService;
import com.ambition.agile.modules.org.service.OrgStaffService;
import com.ambition.agile.modules.org.service.OrgUserService;
import com.ambition.agile.modules.sys.entity.Area;
import com.ambition.agile.modules.sys.entity.Dict;
import com.ambition.agile.modules.sys.service.AreaService;
import com.ambition.agile.modules.sys.utils.DictUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ambition.agile.common.utils.StringUtils;

/**
 * 机构信息变更
 * @author ckl
 *
 */
@Controller
@RequestMapping(value = "${frontPath}/org/orgChange")
public class OrgInformationChangeController extends BaseController {
	@Autowired
	private OrgService orgService;

	@Autowired
	private OrgUserService orgUserService;

	@Autowired
	private OrgStaffService orgStaffService;
	
	@Autowired
	private OrgAchieveService orgAchieveService;
	
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private OrgQualificationService orgQualificationService;
	
	
	

	@RequestMapping(value = "toOrgTip")
	public String toOrgTip(Org org, Model model, HttpSession session,
			@RequestParam(required=true)Integer tagNum) {
		OrgUser user = (OrgUser) session.getAttribute("user");
		if (user == null) {
			return "center/login";
		}
		OrgUser orgUser=orgUserService.get(user);
		org = orgUser.getOrg();
		if(tagNum != 1){
			if(!(null != orgUser && null != orgUser.getOrg() && StringUtils.isNotEmpty(orgUser.getOrg().getId()))){
				return "center/login";
			}
		}
		if(tagNum == 1){ //企业基本信息
			if(orgUser != null && orgUser.getOrg()!= null &&StringUtils.isNotEmpty(orgUser.getOrg().getId()) ){
				org=orgService.get(orgUser.getOrg().getId());
				//更新是否已经添加过 三种信息的状态字段
				if(orgQualificationService.findOrgQualificationByOrgId(Integer.valueOf(orgUser.getOrg().getId())).size()>0){
					//if(org.getQualificationOn() != 1){}
					org.setQualificationOn(1);
				}else{
					org.setQualificationOn(0);
				}
				if(orgStaffService.findStaffByOrgId(orgUser.getOrg().getId()).size()>0){
					//if(org.getStaffOn() != 1){}
					org.setStaffOn(1);
					
				}else{
					org.setStaffOn(0);
				}
				if(orgAchieveService.findAchieveByOrgIdNoYears(orgUser.getOrg().getId()).size()>0){
					//if(org.getAchieveOn() != 1){}
					org.setAchieveOn(1);
					
				}else{
					org.setAchieveOn(0);
				}
				orgService.updateStateOn(org);
				
			}
			model.addAttribute("org", org);
			return "center/change/changeOrgInfoInner";
			
		}else if(tagNum == 2){
			List<OrgQualification> orgQualificationList=orgQualificationService.findOrgQualificationByOrgId(Integer.valueOf(orgUser.getOrg().getId()));
			model.addAttribute("orgQualificationList", orgQualificationList);
			if(orgUser != null && orgUser.getOrg()!= null &&StringUtils.isNotEmpty(orgUser.getOrg().getId()) ){
				org=orgService.get(orgUser.getOrg().getId());
			}
			//获取 证书 分类  列表
			String option= "qualification_grade";
			List<Dict> list = DictUtils.getDictList(option);
			String qualStr = "<option value=\"\">请选择</option>";
			if(list != null){
				for(int i=0,t = list.size();i<t;i++){
					Dict dict = list.get(i);
					qualStr += "<option value="+dict.getValue()+">"+dict.getLabel()+"</option>";
					//str+="<option value="+data[i].value+">"+data[i].label+"</option>";
				}
			}
			model.addAttribute("qualStr",qualStr);
			model.addAttribute("org", org);
			
			return "center/change/changeOrgQualification";
		}else if(tagNum == 3){
			org=orgService.get(orgUser.getOrg().getId());
			model.addAttribute("org", org);
			
			return "center/change/changeOrgStaff";
		}else if(tagNum == 4){
			if(orgUser != null && orgUser.getOrg()!= null &&StringUtils.isNotEmpty(orgUser.getOrg().getId()) ){
				org=orgService.get(orgUser.getOrg().getId());
			}
			model.addAttribute("org", org);
			return "center/change/changeOrgAchieve";
			
		}else if(tagNum == 5){
			org=orgService.get(orgUser.getOrg().getId());
			model.addAttribute("org", org);
			return "center/change/changeFgw";
			
		}else if(tagNum == 6){
			org=orgService.get(orgUser.getOrg().getId());
			model.addAttribute("org", org);
			return "center/change/changeFgw2";
			
		}
		
		
		return "center/change/changeOrgInfo";
	}
	
	
	

	
}