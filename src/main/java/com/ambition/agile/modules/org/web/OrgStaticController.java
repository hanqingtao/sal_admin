/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.org.web;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ambition.agile.common.ApiResponse;
import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.util.DateTimeUtil;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.modules.org.entity.Org;
import com.ambition.agile.modules.org.service.OrgService;
import com.ambition.agile.modules.sys.entity.Area;
import com.ambition.agile.modules.sys.service.AreaService;

/**
 * 统计Controller
 * @author harry
 * @version 2017-08-02
 */
@Controller
@RequestMapping(value = "${adminPath}/static")
public class OrgStaticController extends BaseController {

	@Autowired
	private OrgService orgService;

	@Autowired
	private AreaService areaService;
	
	/**
	 * 业绩排行
	 * @param model
	 * @param org
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("org:org:view")
	@RequestMapping(value = "orderStatic")
	public String orderStatic(Model model, Org org, HttpServletRequest request, HttpServletResponse response) {
		org.setId("1");
		Page<Org> page = orgService.findPage(new Page<Org>(request, response), org);
		model.addAttribute("page", page);
		//前台显示的年份 当前年份，及10年前的年份 用于查询
		List yearList = new ArrayList();
		int currentYear = DateTimeUtil.getYear();
		for(int i=0,t=10;i<t;i++){
			yearList.add(currentYear-i);
		}
		List<Area> areaList = areaService.findAll();
		model.addAttribute("areaList", areaList);
		model.addAttribute("yearList", yearList);
		return "modules/static/orderStatic";
	}
	
	/**
	 * 区域统计
	 * @param model
	 * @param org
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("org:org:view")
	@RequestMapping(value = "areaStatic")
	public String areaStatic(Model model, Org org, HttpServletRequest request, HttpServletResponse response) {

		return "modules/static/areaStatic";
	}
	
	/**
	 * 获取区域的统计信息
	 * @param request
	 * @param response
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "getAreaStaticByCode")
	@ResponseBody
	public ApiResponse<?> getAreaStaticByCode(HttpServletRequest request, HttpServletResponse response, 
			String code, Integer id){
		//System.out.println("------------------------------" + code);
		if(StringUtils.isEmpty(code)){
			return ApiResponse.fail(301, "参数有误！");
		}
		Map tmap = orgService.getAreaStaticByAreaId(id);
		return ApiResponse.success(tmap);
	}
	
	@RequestMapping(value = "getOrgListByArea")
	public String getOrgListByArea(Model model, Org org, HttpServletResponse response, HttpServletRequest request) {
		Page<Org> page = new Page<Org>(request, response);
		if(org != null && org.getArea() != null && !StringUtils.isEmpty(org.getArea().getId())){
			org.setId("1");
			page.setPageSize(10);
			page = orgService.findPage(page, org);
		}
		model.addAttribute("page", page);
		return "modules/static/areaStatic_orgList";
	}
	

}