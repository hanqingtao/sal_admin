/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.pro.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ambition.agile.common.config.Global;
import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.common.utils.StringUtils;
import com.ambition.agile.modules.pro.entity.Engineer;
import com.ambition.agile.modules.pro.service.EngineerService;

/**
 * 工程信息表Controller
 * @author harry
 * @version 2017-08-03
 */
@Controller
@RequestMapping(value = "${adminPath}/pro/engineer")
public class EngineerController extends BaseController {

	@Autowired
	private EngineerService engineerService;
	
	@ModelAttribute
	public Engineer get(@RequestParam(required=false) String id) {
		Engineer entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = engineerService.get(id);
		}
		if (entity == null){
			entity = new Engineer();
		}
		return entity;
	}
	
	@RequiresPermissions("pro:engineer:view")
	@RequestMapping(value = {"list", ""})
	public String list(Engineer engineer, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Engineer> page = engineerService.findPage(new Page<Engineer>(request, response), engineer); 
		model.addAttribute("page", page);
		return "modules/pro/engineerList";
	}

	@RequiresPermissions("pro:engineer:view")
	@RequestMapping(value = "form")
	public String form(Engineer engineer, Model model) {
		model.addAttribute("engineer", engineer);
		return "modules/pro/engineerForm";
	}

	@RequiresPermissions("pro:engineer:edit")
	@RequestMapping(value = "save")
	public String save(Engineer engineer, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, engineer)){
			return form(engineer, model);
		}
		engineerService.save(engineer);
		addMessage(redirectAttributes, "保存工程信息表成功");
		return "redirect:"+Global.getAdminPath()+"/pro/engineer/?repage";
	}
	
	@RequiresPermissions("pro:engineer:edit")
	@RequestMapping(value = "delete")
	public String delete(Engineer engineer, RedirectAttributes redirectAttributes) {
		engineerService.delete(engineer);
		addMessage(redirectAttributes, "删除工程信息表成功");
		return "redirect:"+Global.getAdminPath()+"/pro/engineer/?repage";
	}

}