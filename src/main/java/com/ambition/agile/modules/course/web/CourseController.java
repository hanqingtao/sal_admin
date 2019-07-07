/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.course.web;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ambition.agile.common.config.Global;
import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.utils.StringUtils;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.modules.course.entity.Course;
import com.ambition.agile.modules.course.entity.CourseCategory;
import com.ambition.agile.modules.course.service.CourseCategoryService;
import com.ambition.agile.modules.course.service.CourseService;

/**
 * 课程Controller
 * @author harry
 * @version 2019-07-05
 */
@Controller
@RequestMapping(value = "${adminPath}/course/course")
public class CourseController extends BaseController {

	@Autowired
	private CourseService courseService;
	
	@Autowired
	private CourseCategoryService courseCategoryService;
	
	@ModelAttribute
	public Course get(@RequestParam(required=false) String id) {
		Course entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = courseService.get(id);
		}
		if (entity == null){
			entity = new Course();
		}
		return entity;
	}
	
	@RequiresPermissions("course:course:view")
	@RequestMapping(value = {"list", ""})
	public String list(Course course, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Course> page = courseService.findPage(new Page<Course>(request, response), course); 
		model.addAttribute("page", page);
		return "modules/course/courseList";
	}

	@RequiresPermissions("course:course:view")
	@RequestMapping(value = "form")
	public String form(Course course, Model model) {
		model.addAttribute("course", course);
		return "modules/course/courseForm";
	}
	
	@RequiresPermissions("course:course:courseImport")
	@RequestMapping(value = "courseImport")
	public String courseImport(@RequestParam(value="uploadFile",required=false) MultipartFile uploadFile,
			HttpServletRequest request,
			HttpServletResponse response,
			Model model
			) {
		if(null != uploadFile){
			InputStream inputStream = null;
			try{
				inputStream = uploadFile.getInputStream();
				if(null != inputStream){
					
					
				}
				
			}
			
		}
		response.setContentType("test/html;charset=UTF-8");
		//model.addAttribute("course", course);
		return "modules/course/courseImport";
	}

	@RequiresPermissions("course:course:edit")
	@RequestMapping(value = "save")
	public String save(Course course, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, course)){
			return form(course, model);
		}
		System.out.println("###course.getCategoryId###"+course.getCategoryId());
		if(null != course && course.getCategoryId() != null){
			CourseCategory  courseCategory = courseCategoryService.get(course.getCategoryId()+"");
			if(null != courseCategory && null != courseCategory.getParentIds()){
				course.setCategoryIds( courseCategory.getParentIds());
			}
			System.out.println("###courseCategory.getParentIds()###"+courseCategory.getParentIds());
			if(null != courseCategory && null != courseCategory.getName()){
				course.setCategoryName( courseCategory.getName());
			}

//			if(null != courseCategory && null != courseCategory.getName()){
//				course.setName( courseCategory.getName());
//			}
		}
		System.out.println("###courseCategory.getName()###"+course.getCategoryName());
		courseService.save(course);
		System.out.println("####getCategoryIds##"+course.getCategoryIds());
		addMessage(redirectAttributes, "保存课程成功");
		return "redirect:"+Global.getAdminPath()+"/course/course/?repage";
	}
	
	@RequiresPermissions("course:course:edit")
	@RequestMapping(value = "delete")
	public String delete(Course course, RedirectAttributes redirectAttributes) {
		courseService.delete(course);
		addMessage(redirectAttributes, "删除课程成功");
		return "redirect:"+Global.getAdminPath()+"/course/course/?repage";
	}

}