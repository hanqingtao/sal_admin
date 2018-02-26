package com.ambition.agile.modules.org.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.modules.org.entity.Org;
import com.ambition.agile.modules.org.entity.OrgBak;
import com.ambition.agile.modules.org.service.OrgBakService;
import com.ambition.agile.modules.org.service.OrgService;

@Controller
@RequestMapping(value = "${adminPath}/org/orgbak")
public class OrgBakController extends BaseController{
	
	@Autowired
	private OrgBakService orgBakService;
	@Autowired
	private OrgService orgService;
	
	/**
	 * 机构备案列表
	 * @param org
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("org:org:view")
	@RequestMapping(value = "orgBakList")
	public String orgBakList(Org org, HttpServletRequest request, HttpServletResponse response, Model model) {
		org = orgService.get(org);
		OrgBak orgBak = new OrgBak();
		orgBak.setOrgId(Integer.parseInt(org.getId()));
		Page<OrgBak> page = orgBakService.findPage(new Page<OrgBak>(request, response), orgBak); 
		model.addAttribute("org", org);
		model.addAttribute("page", page);
		return "modules/org/orgBakList";
	}
	/**
	 * 备案详情
	 * @param orgBak
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("org:org:view")
	@RequestMapping(value = "orgBakDetail")
	public String orgBakDetail(OrgBak orgBak, HttpServletRequest request, HttpServletResponse response, Model model) {
		orgBak = orgBakService.get(orgBak);
		Map<String, Object> pMap = new HashMap<>();
		String sn = orgBak.getSn();
		Integer orgId = orgBak.getOrgId();
		pMap.put("sn", sn);
		pMap.put("orgId", orgId);
		//企业资质证明备案
		List<Map<String, Object>> orgBakQualification = orgBakService.getBakQualificationBySnAndOrgId(pMap);
		//从业人员备案
		List<Map<String, Object>> orgBakStaff = orgBakService.getBakStaffBySnAndOrgId(pMap); 
		//近3年业绩
		List<Map<String, Object>> orgBakAchieve = orgBakService.getBakAhieveBySnAndOrgId(pMap);
		
		model.addAttribute("orgBak", orgBak);
		model.addAttribute("orgBakQualification", orgBakQualification);
		model.addAttribute("orgBakStaff", orgBakStaff);
		model.addAttribute("orgBakAchieve", orgBakAchieve);
		return "modules/org/orgBakDetails";
	}
	
}
