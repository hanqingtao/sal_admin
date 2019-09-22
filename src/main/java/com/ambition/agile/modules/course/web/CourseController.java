/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.course.web;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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

import com.ambition.agile.common.aliyun.oss.OSSConfig;
import com.ambition.agile.common.aliyun.oss.OSSUploadUtil;
import com.ambition.agile.common.config.Global;
import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.util.ExcelUtil;
import com.ambition.agile.common.utils.StringUtils;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.modules.course.entity.Course;
import com.ambition.agile.modules.course.entity.CourseCategory;
import com.ambition.agile.modules.course.service.CourseCategoryService;
import com.ambition.agile.modules.course.service.CourseService;

/**
 * 课程Controller
 *
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
	public Course get(@RequestParam(required = false) String id) {
		Course entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = courseService.get(id);
		}
		if (entity == null) {
			entity = new Course();
		}
		return entity;
	}

	@RequiresPermissions("course:course:view")
	@RequestMapping(value = { "list", "" })
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

	// @RequiresPermissions("course:course:toCourseImport")
	@RequestMapping(value = "toCourseImport")
	public String courseImport(HttpServletRequest request, HttpServletResponse response, Model model) {
		// response.setContentType("test/html;charset=UTF-8");
		// model.addAttribute("course", course);
		return "modules/course/toCourseImport";
	}

	@RequiresPermissions("course:course:courseImport")
	@RequestMapping(value = "courseImport")
	public String courseImport(@RequestParam(value = "uploadFile", required = false) MultipartFile uploadFile,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		
		int suceeNum = 0;
		int errorNum = 0;
		if (null != uploadFile) {
			InputStream inputStream = null;
			try {
				inputStream = uploadFile.getInputStream();
				if (null != inputStream) {
					ArrayList<Course> courseList = (ArrayList<Course>) ExcelUtil.readExcelToCourse(inputStream);

					if (null != courseList && courseList.size() > 0) {
						for (int i = 0; i < courseList.size(); i++) {
							Course course = courseList.get(i);
							if (null != course) {
								boolean flag = true;
								String errorMsg = "";
								// 判断数据的正确性
								if (null == course.getCategoryName() || course.getCategoryName().isEmpty()) {
									flag = false;
									errorMsg = "课程分类名称不能为空!";
									errorNum = errorNum + 1;
									continue;
								}
								// 判断数据的正确性
								if (null == course.getCategoryCode() || course.getCategoryCode().isEmpty()) {
									flag = false;
									errorMsg = "课程分类code不能为空!";
									errorNum = errorNum + 1;
									continue;
								}
								// 判断数据的正确性
								if (null == course.getName() || course.getName().isEmpty()) {
									flag = false;
									errorMsg = "课程名称不能为空!";
									errorNum = errorNum + 1;
									continue;
								}
								// 判断数据的正确性
								if (null == course.getCourseCode() || course.getCourseCode().isEmpty()) {
									flag = false;
									errorMsg = "课程code不能为空!";
									errorNum = errorNum + 1;
									continue;
								}
								List<Course> courseOld = (List<Course>) courseService.getByCode(course.getCourseCode());

								/*
								 * if (null == courseOld || courseOld.size() < 1) { continue; }
								 */

								if (courseOld.size() > 0) {
									flag = false;
									errorMsg = "部分课程已经存在系统中!";
									errorNum = errorNum + 1;
									continue;
								}
								// 判断 是否为空
								if (null == course.getReply() || course.getReply().isEmpty()) {
									course.setReply("请欣赏");
								}
								// 判断数据的正确性
								if (null == course.getVideoPath() || course.getVideoPath().isEmpty()) {
									flag = false;
									errorMsg = "课程路径地址不能为空!";
									errorNum = errorNum + 1;
									continue;
								}
								//OSSConfig ossConfig = OSSConfig.getOssConfigInstance();
								//course.setVideoPath(ossConfig.getVideoUrl() + course.getVideoPath());
								// 设置 课程 类型 1 默认
								course.setCourseType("1");
								if (null != course.getCategoryCode()) {
									List<CourseCategory> courseCategoryList = courseCategoryService
											.getByCode(course.getCategoryCode());
									if(!courseCategoryList.isEmpty()){
									CourseCategory courseCategory = (CourseCategory) courseCategoryList.get(0);
									if (null != courseCategory.getId() && !courseCategory.getId().isEmpty()) {
										course.setCategoryName(courseCategory.getName());
										course.setCategoryId(Integer.parseInt(courseCategory.getId()));
										course.setCategoryCode(courseCategory.getCode());
										course.setCategoryIds(courseCategory.getParentIds());
									}
									}
								}
								suceeNum = suceeNum + 1;
								courseService.save(course);
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("import coures faile,message: " + e.getMessage());
			}
		}
		String message = "";
		if(suceeNum>0){
			message = "成功导入:"+suceeNum+"条数据.";
		}
		if(errorNum>0){
			message = "<br> 未导入:"+errorNum+"条数据.";
		}
		model.addAttribute("message",message);
		// response.setContentType("test/html;charset=UTF-8");
		// model.addAttribute("course", course);
		return "modules/course/toCourseImport";
	}

	@RequiresPermissions("course:course:edit")
	@RequestMapping(value = "save")
	public String save(Course course, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, course)) {
			return form(course, model);
		}
		// System.out.println("###course.getCategoryId###"+course.getCategoryId());
		if (null != course && course.getCategoryId() != null) {
			CourseCategory courseCategory = courseCategoryService.get(course.getCategoryId() + "");
			if (null != courseCategory && null != courseCategory.getParentIds()) {
				course.setCategoryIds(courseCategory.getParentIds());
			}
			// System.out.println("###courseCategory.getParentIds()###"+courseCategory.getParentIds());
			if (null != courseCategory && null != courseCategory.getName()) {
				course.setCategoryName(courseCategory.getName());
			}

//			if(null != courseCategory && null != courseCategory.getName()){
//				course.setName( courseCategory.getName());
//			}
		}
		System.out.println("###courseCategory.getName()###"+course.getVideoName() +"video Path :" + course.getVideoPath());
		courseService.save(course);
		addMessage(redirectAttributes, "保存课程成功");
		System.out.println("####get course   Id##"+course.getId());
		return "redirect:" + Global.getAdminPath() + "/course/course/?repage";
	}

	@RequiresPermissions("course:course:edit")
	@RequestMapping(value = "delete")
	public String delete(Course course, RedirectAttributes redirectAttributes) {
		courseService.delete(course);
		addMessage(redirectAttributes, "删除课程成功");
		return "redirect:" + Global.getAdminPath() + "/course/course/?repage";
	}

}