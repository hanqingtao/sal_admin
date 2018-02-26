/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.center.org;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ambition.agile.common.ApiResponse;
import com.ambition.agile.common.config.Global;
import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.util.FtpUtils;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.common.utils.StringUtils;
import com.ambition.agile.modules.org.entity.Org;
import com.ambition.agile.modules.org.entity.OrgQualification;
import com.ambition.agile.modules.org.entity.OrgStaff;
import com.ambition.agile.modules.org.entity.OrgUser;
import com.ambition.agile.modules.org.service.OrgQualificationService;
import com.ambition.agile.modules.org.service.OrgUserService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 代理机构资格Controller
 * @author harry
 * @version 2017-08-03
 */
@Controller
@RequestMapping(value = "${frontPath}/org/orgQualification")
public class OrgQualificationWebController extends BaseController {

	@Autowired
	private OrgQualificationService orgQualificationService;
	
	@Autowired
	private OrgUserService orgUserService;
	
	@ModelAttribute
	public OrgQualification get(@RequestParam(required=false) String id) {
		OrgQualification entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = orgQualificationService.get(id);
		}
		if (entity == null){
			entity = new OrgQualification();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(OrgQualification orgQualification,OrgStaff orgStaff, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OrgQualification> page = orgQualificationService.findPage(new Page<OrgQualification>(request, response), orgQualification); 
		model.addAttribute("page", page);
		model.addAttribute("orgStaff", orgStaff);
		return "center/org/orgStaff";
	}

	@RequestMapping(value = "form")
	public String form(OrgQualification orgQualification, Model model) {
		model.addAttribute("orgQualification", orgQualification);
		return "center/org/orgQualification";
	}
	/**
	 * 保存代理机构资质
	 * @param name
	 * @param grade
	 * @param flag
	 * @param orgQualification
	 * @param dbName
	 * @param model
	 * @param redirectAttributes
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "save")
	public String save(String[] name,
			String[] grade,String flag,OrgQualification orgQualification,String[] dbName,
			Model model, RedirectAttributes redirectAttributes,HttpServletRequest request,HttpSession session) {
		OrgUser user=(OrgUser)session.getAttribute("user");
		if(user!=null){
			 OrgUser orgUser=orgUserService.get(user);
			orgQualification.setOrgId(Integer.valueOf(orgUser.getOrg().getId()));
		}else{
			return "center/org/orgInfo";
		}
		 
		for (int i = 0; i < Integer.valueOf(flag); i++) {
			orgQualification.setPath(null);
			orgQualification.setName(name[i]);
			orgQualification.setGrade(grade[i]);
			if(dbName != null && dbName.length > 0){				
				orgQualification.setPath(dbName[i]);
			}
			orgQualification.setId(null);
			orgQualificationService.save(orgQualification);			
		}
		 
		model.addAttribute("message", "保存代理机构资质成功");
		model.addAttribute("org", user.getOrg());
		model.addAttribute("orgQualification", orgQualification);
		return "center/org/orgStaff";
	}
	
	/**
	 * 资质修改
	 * @param file
	 * @param name
	 * @param grade
	 * @param flag
	 * @param orgQualification
	 * @param org
	 * @param orgStaff
	 * @param model
	 * @param redirectAttributes
	 * @param request
	 * @param session
	 * @return
	 */
	
	@RequestMapping(value = "change")
	public String change(@RequestParam(value = "file", required = false) MultipartFile  file,String  name,String  grade,OrgQualification orgQualification,Org org,OrgStaff orgStaff,Model model, RedirectAttributes redirectAttributes,HttpServletRequest request,HttpSession session) {
		 
		 
	 
			
			try {
				if(file != null && StringUtils.isNotEmpty(file.getOriginalFilename())){
					String path = "path";
				    String fileName = FtpUtils.getUploadFileNameToDB_new(file.getOriginalFilename(),orgQualification.getPath()==null?"a":orgQualification.getPath());
					String headPicPath = path +  "/" + fileName;
					InputStream fileInput = file.getInputStream();
						FtpUtils.uploadFile(path, fileName, fileInput);
				 
						orgQualification.setPath(headPicPath);
				}
				} catch (Exception e) {
					e.printStackTrace();
				}
		 
			orgQualificationService.save(orgQualification);
			
			
			OrgUser user = (OrgUser) session.getAttribute("user");
			if(user!=null){
				OrgUser orgUser=orgUserService.get(user);
				if(orgUser.getOrg()!=null){
					
					List<OrgQualification> orgQualificationList=orgQualificationService.findOrgQualificationByOrgId(Integer.valueOf(orgUser.getOrg().getId()));
					model.addAttribute("orgQualificationList", orgQualificationList);
				}
				
			}
		 
		return "redirect:/center/org/orgStaff/changeEntry";
	}
	
	@RequestMapping(value = "delete")
	public void delete(OrgQualification orgQualification, RedirectAttributes redirectAttributes,HttpServletResponse response) {
		orgQualificationService.delete(orgQualification);
		ObjectMapper mapper = new ObjectMapper();
			try {
				 String msg="删除资质成功";
				 Map<String,String> mapMsg=new HashMap<String,String>();
				 mapMsg.put("status","succ");
				 mapMsg.put("result","删除资质成功");
				String str = mapper.writeValueAsString(mapMsg);
				PrintWriter writer = response.getWriter();
				writer.println(str);
			} catch (Exception e) {
				e.printStackTrace();
			}
	 
		 
	}
	/**
	 * 上传注册机构资质的入口  暂时没用 ，用保存下一步，操作替换了该方法
	 * @param orgQualification
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "entry")
	public String entry(OrgQualification orgQualification,  Model model,RedirectAttributes redirectAttributes,HttpSession session) {
		model.addAttribute("orgQualification", orgQualification);
		 /*OrgUser user=(OrgUser)session.getAttribute("user");
		if(user.getOrg()==null){
			model.addAttribute("message","请先注册机构再上传资质");
			return "center/org/orgInfo";	
		}*/
		
		return "center/org/orgQualification";
	}
	
	@RequestMapping(value = "changeEntry")
	public String recordEntry(OrgQualification orgQualification,  Model model,RedirectAttributes redirectAttributes,HttpSession session) {
		OrgUser user = (OrgUser) session.getAttribute("user");
		if(user!=null){
			OrgUser orgUser=orgUserService.get(user);
			if(orgUser.getOrg()!=null){
				List<OrgQualification> orgQualificationList=orgQualificationService.findOrgQualificationByOrgId(Integer.valueOf(orgUser.getOrg().getId()));
				model.addAttribute("orgQualificationList", orgQualificationList);
			}
		}
		return "center/change/changeOrgQualification";
	}
	/**
	 * 修改
	 * @param orgQualification
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "edit")
	@ResponseBody
	public ApiResponse<?> edit(String flag,OrgQualification orgQualification,
			HttpServletRequest request, HttpSession session) {
		//List<OrgQualification> orgQualificationList=new ArrayList<OrgQualification>();
		//OrgQualification orgQualification=new OrgQualification();
		String message = "保存成功";
		OrgUser user = (OrgUser) session.getAttribute("user");
	 
		OrgUser orgUser=orgUserService.get(user);
		 if(user!=null && orgUser != null && orgUser.getOrg()!= null
				 && StringUtils.isNotEmpty(orgUser.getOrg().getId())){
			  System.out.println(flag+"flag信息");
//			 for (int i = 0; i<Integer.valueOf(flag); i++) {
//					 orgQualification.setId(id[i]);
//				 orgQualification.setName(name[i]);
//				 orgQualification.setGrade(grade[i]);
//				 orgQualification.setPath(path[i]);
//				 System.out.println(orgQualification+"获取的每个对象");
				 orgQualification.setOrgId(Integer.valueOf(orgUser.getOrg().getId()));
				 orgQualificationService.save(orgQualification);
//				}
				  
			 
			 
		}else{
			return ApiResponse.fail(300, "请登录");
		} 
		
		return ApiResponse.success(message, null);
	}
	
	
	
	/**
	 * 修改
	 * @param orgQualification
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "modify")
	@ResponseBody
	public ApiResponse<?> modify(String flag,String orgId,OrgQualification orgQualification,
			HttpServletRequest request, HttpSession session) {
		List<OrgQualification> orgQualificationList=new ArrayList<OrgQualification>();
		//OrgQualification orgQualification=new OrgQualification();
		String message = "保存成功";
		OrgUser user = (OrgUser) session.getAttribute("user");
		System.out.println(flag+"flag信息");
		 if(user!=null){
			 OrgUser orgUser=orgUserService.get(user);
//			  
//			 for (int i = 0; i<Integer.valueOf(flag); i++) {
//				 orgQualification.setId(null);
//				 orgQualification.setName(name[i]);
//				 orgQualification.setGrade(grade[i]);
//				 orgQualification.setPath(path[i]);
				 System.out.println(orgQualification+"获取的每个对象");
				 orgQualification.setOrgId(Integer.valueOf(orgId));
				 orgQualificationService.save(orgQualification);
//				}
			 
		}else{
			return ApiResponse.fail(300, "请登录");
		} 
		
		return ApiResponse.success(message, null);
	}
	/**
	 * ajax上传图片到服务器，返回服务器图片url
	 * @return
	 */
	@RequestMapping(value = "ajaxUploadImage")
	@ResponseBody
	public Map<String, Object> ajaxUploadImage(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "file", required = true) MultipartFile file){
		String headPicPath = null;
//		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> reMap  = new HashMap<>();
		try {
				if (file != null && StringUtils.isNotEmpty(file.getOriginalFilename())) {
					String path = "/orgImg";
					String fileName = FtpUtils.getUploadFileNameToDB(file.getOriginalFilename());
					headPicPath = path + "/" + fileName;
					InputStream fileInput;
					fileInput = file.getInputStream();
					FtpUtils.uploadFile(path, fileName, fileInput);
					System.out.println("headPicPath===================="+headPicPath);
					reMap.put("resPath", headPicPath);
					return reMap;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		return null;
		
	}
	
	
}