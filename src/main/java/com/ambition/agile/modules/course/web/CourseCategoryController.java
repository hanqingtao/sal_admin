/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.course.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ambition.agile.common.mapper.JsonMapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ambition.agile.common.config.Global;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.common.utils.StringUtils;
import com.ambition.agile.modules.course.entity.CourseCategory;
import com.ambition.agile.modules.course.service.CourseCategoryService;

/**
 * 课程分类Controller
 * @author harry
 * @version 2019-07-05
 */
@Controller
@RequestMapping(value = "${adminPath}/course/courseCategory")
public class CourseCategoryController extends BaseController {

	@Autowired
	private CourseCategoryService courseCategoryService;
	
	@ModelAttribute
	public CourseCategory get(@RequestParam(required=false) String id) {
		CourseCategory entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = courseCategoryService.get(id);
		}
		if (entity == null){
			entity = new CourseCategory();
		}
		return entity;
	}
	
	@RequiresPermissions("course:courseCategory:view")
	@RequestMapping(value = {"list", ""})
	public String list(CourseCategory courseCategory, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<CourseCategory> list = courseCategoryService.findList(courseCategory); 
		model.addAttribute("list", list);
		model.addAttribute("lists", JsonMapper.toJson(list));
		return "modules/course/courseCategoryList";
	}

	@RequiresPermissions("course:courseCategory:view")
	@RequestMapping(value = "form")
	public String form(CourseCategory courseCategory, Model model) {
		if (courseCategory.getParent()!=null && StringUtils.isNotBlank(courseCategory.getParent().getId())){
			courseCategory.setParent(courseCategoryService.get(courseCategory.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(courseCategory.getId())){
				CourseCategory courseCategoryChild = new CourseCategory();
				courseCategoryChild.setParent(new CourseCategory(courseCategory.getParent().getId()));
				List<CourseCategory> list = courseCategoryService.findList(courseCategory); 
				if (list.size() > 0){
					courseCategory.setSort(list.get(list.size()-1).getSort());
					if (courseCategory.getSort() != null){
						courseCategory.setSort(courseCategory.getSort() + 30);
					}
				}
			}
		}
		if (courseCategory.getSort() == null){
			courseCategory.setSort(30);
		}
		model.addAttribute("courseCategory", courseCategory);
		return "modules/course/courseCategoryForm";
	}

	@RequiresPermissions("course:courseCategory:edit")
	@RequestMapping(value = "save")
	public String save(CourseCategory courseCategory, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, courseCategory)){
			return form(courseCategory, model);
		}
		//对于课程分类 code 为空时，则进行 code 的创建
		if(null != courseCategory){
			if(StringUtils.isEmpty(courseCategory.getCode())){
				Date d = new Date();
		        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		        String code = format.format(d);
				courseCategory.setCode(code);
			}
//	        String str = "";
//	        for(int i=0 ;i <5; i++){
//	            int n = (int)(Math.random()*90)+10;
//	            str += n;
//	        }
		}
		courseCategoryService.save(courseCategory);
		addMessage(redirectAttributes, "保存课程分类成功");
		return "redirect:"+Global.getAdminPath()+"/course/courseCategory/?repage";
	}
	
	@RequiresPermissions("course:courseCategory:edit")
	@RequestMapping(value = "delete")
	public String delete(CourseCategory courseCategory, RedirectAttributes redirectAttributes) {
		courseCategoryService.delete(courseCategory);
		addMessage(redirectAttributes, "删除课程分类成功");
		return "redirect:"+Global.getAdminPath()+"/course/courseCategory/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<CourseCategory> list = courseCategoryService.findList(new CourseCategory());
		for (int i=0; i<list.size(); i++){
			CourseCategory e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}
	
}