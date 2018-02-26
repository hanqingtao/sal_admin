/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.center.org;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ambition.agile.common.ApiResponse;
import com.ambition.agile.common.config.Global;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.util.ExcelUtil;
import com.ambition.agile.common.util.FtpUtils;
import com.ambition.agile.common.utils.StringUtils;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.modules.org.entity.Org;
import com.ambition.agile.modules.org.entity.OrgAchieve;
import com.ambition.agile.modules.org.entity.OrgStaff;
import com.ambition.agile.modules.org.entity.OrgUser;
import com.ambition.agile.modules.org.service.OrgStaffService;
import com.ambition.agile.modules.org.service.OrgUserService;
import com.ambition.agile.modules.sys.entity.Dict;
import com.ambition.agile.modules.sys.utils.DictUtils;
import com.ambition.agile.modules.org.service.OrgService;

/**
 * 代理机构专职人员Controller
 * 
 * @author harry
 * @version 2017-08-03
 */
@Controller
@RequestMapping(value = "${frontPath}/org/orgStaff")
public class OrgStaffWebController extends BaseController {

	@Autowired
	private OrgStaffService orgStaffService;
	
	@Autowired
	private OrgUserService orgUserService;
	
	
	@Autowired
	private OrgService orgService;

	@ModelAttribute
	public OrgStaff get(@RequestParam(required = false) String id) {
		OrgStaff entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = orgStaffService.get(id);
		}
		if (entity == null) {
			entity = new OrgStaff();
		}
		return entity;
	}

	@RequestMapping(value = { "list", "" })
	public String list(OrgStaff orgStaff, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OrgStaff> page = orgStaffService.findPage(new Page<OrgStaff>(request, response), orgStaff);
		model.addAttribute("page", page);
		return "center/org/orgStaff";
	}

	@RequestMapping(value = "form")
	public String form(OrgStaff orgStaff, Model model) {
		model.addAttribute("orgStaff", orgStaff);
		return "center/org/orgStaff";
	}

	@RequestMapping(value = "save")
	@ResponseBody
	public ApiResponse<?> save( OrgStaff orgStaff,
			 Model model, RedirectAttributes redirectAttributes, HttpSession session) {
		
		
		OrgUser user = (OrgUser) session.getAttribute("user");
		OrgUser orgUser=orgUserService.get(user);
		if (user != null) {
			orgStaff.setOrgId(Integer.valueOf(orgUser.getOrg().getId()));
		} else {
			return ApiResponse.fail(300, "请登录");
		}
		orgStaffService.save(orgStaff);
		
		return ApiResponse.success(null);
		
	}
	
	@RequestMapping(value = "delete")
	public String delete(OrgStaff orgStaff, RedirectAttributes redirectAttributes) {
		orgStaffService.delete(orgStaff);
		addMessage(redirectAttributes, "删除代理机构专职人员成功");
		return "center/org/orgStaff";
	}
	
	@RequestMapping(value = "change")
	public String change(@RequestParam(value = "file1", required = false) MultipartFile file1,
			@RequestParam(value = "file2", required = false) MultipartFile file2, OrgStaff orgStaff, Org org,
			OrgAchieve orgAchieve, Model model, RedirectAttributes redirectAttributes, HttpSession session) {

		  
		
		try {
			if (file1 != null && StringUtils.isNotEmpty(file1.getOriginalFilename()) && file2 != null
					&& StringUtils.isNotEmpty(file2.getOriginalFilename())) {
				String path = "path";
				String fileName1 = FtpUtils.getUploadFileNameToDB_new(file1.getOriginalFilename(),
						orgStaff.getCardPhoto() == null ? "a" : orgStaff.getCardPhoto());
				String fileName2 = FtpUtils.getUploadFileNameToDB_new(file1.getOriginalFilename(),
						orgStaff.getProtitlePhoto() == null ? "a" : orgStaff.getProtitlePhoto());
				String headPicPath1 = path + "/" + fileName1;
				String headPicPath2 = path + "/" + fileName2;
				InputStream fileInput1 = file1.getInputStream();
				InputStream fileInput2 = file2.getInputStream();
				FtpUtils.uploadFile(path, fileName1, fileInput1);
				FtpUtils.uploadFile(path, fileName2, fileInput2);

				orgStaff.setCardPhoto(headPicPath1);
				orgStaff.setProtitlePhoto(headPicPath2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		orgStaff.setSex("1");
		orgStaff.setDegree("2");
		orgStaff.setWorkType("1");
		orgStaffService.save(orgStaff);
		OrgUser user = (OrgUser) session.getAttribute("user");
		if(user!=null){
			 OrgUser orgUser=orgUserService.get(user);
		 
			 if(orgUser.getOrg()!=null){
				List<OrgStaff> staffList=orgStaffService.findStaffByOrgId(orgUser.getOrg().getId());
				model.addAttribute("staffList", staffList);
			 }
		  
		}else{
			return "center/login";	
		}
		
		model.addAttribute("message", "保存代理机构专职人员成功");
		model.addAttribute("org", org);
		model.addAttribute("orgAchieve", orgAchieve);
	 
		
		return "redirect:/center/org/orgAchieve/changeEntry";
	}

	/**
	 * 上传注册机构专职入口
	 * 
	 * @param orgStaff
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "entry")
	public String entry(OrgStaff orgStaff, Model model,HttpSession session) {
		OrgUser user=(OrgUser)session.getAttribute("user");
		if(user==null){
			return "center/login";
		}
		OrgUser orgUser=orgUserService.get(user);
		
		model.addAttribute("org", orgUser.getOrg());
		model.addAttribute("orgStaff", orgStaff);
		return "center/org/orgStaff";
	}
	/**
	 * ajax加载人员分页
	 * @param orgStaff
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "staffPage")
	public String staffPage( 
			Page<OrgStaff> page,Integer pageNo,Integer pageSize,
			Model model,HttpSession session,HttpServletRequest request,HttpServletResponse response) {
		OrgUser user=(OrgUser)session.getAttribute("user");
		if(user==null){
			return "center/login";
		}
		OrgUser orgUser=orgUserService.get(user);
		if(page == null){
	 		page = new Page<>();
	 	}
		page.setPageSize(99999999);
	 	if(pageNo == null){
	 		pageNo = 1;
	 	}
	 	page.setPageNo(pageNo);
	 	
	 	OrgStaff orgStaff = new OrgStaff();
	 	String orgIdstr = orgUser.getOrg().getId();
	 	if(StringUtils.isNotEmpty(orgIdstr)){
	 		orgStaff.setOrgId(Integer.parseInt(orgIdstr));
	 	}
	 	page = orgStaffService.findPage(page,orgStaff);
//	 	page = orgStaffService.findPage(new Page<>(request, response),orgStaff);
	 	model.addAttribute("page", page);
	 	model.addAttribute("orgId", orgIdstr);
	 	
		return "center/org/staffPage";
	}

	@RequestMapping(value = "imp")
	@ResponseBody
	public Map<String, Object> imporgStaff(@RequestParam(value = "imporgStaff", required = false) MultipartFile imporgStaff,
			OrgStaff orgStaff, Model model, HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		 SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		OrgUser user=(OrgUser)session.getAttribute("user");
		Map<String, Object> resMap = new HashMap<>();
		int messageCode = 200;
		if(user==null){
			messageCode = 401; //未登录
			resMap.put("messageCode", messageCode);
			return  resMap;
		}
		OrgUser orgUser=orgUserService.get(user);
	 
		
		if (imporgStaff != null && !StringUtils.isEmpty(imporgStaff.getOriginalFilename())) {
			try {
				InputStream inputStream = imporgStaff.getInputStream();
				ApiResponse<List<Map<String, Object>>> orgStaffResult =ExcelUtil.readExcelToStaffs(inputStream);
				if(orgStaffResult.isSuccess()){
					List<Map<String, Object>> qmaps = orgStaffResult.getBody();//获取单元格数据
					if(qmaps!=null&&qmaps.size()>0){
						Integer countNum = 3;
						Map<String, Object> map2 = new HashMap<>();
						orgStaff.setOrgId(Integer.valueOf(orgUser.getOrg().getId()));
					    for (Map<String, Object> map : qmaps) {
					    	//一个身份证只能在一个Org中验证
					    	String card = (String) map.get("card");
					    	String orgIdstr = orgUser.getOrg().getId();
					    	map2.put("card", card);
					    	map2.put("orgId", orgIdstr);
					    	Integer num = orgStaffService.findNumByCardAndOrg(map2);
					    	if(num > 0){
					    		System.out.println("第"+(countNum-2)+"行的身份证已经在其它企业注册过!");
					    		messageCode = 402; //
								resMap.put("messageCode", messageCode);
								resMap.put("message", "第"+(countNum-2)+"行的身份证已经在其它企业注册过!");
								return  resMap;
					    	} 
					    	
					    	//验证单元格数据
					    	System.out.println(map.get("name")+"获得第"+countNum+"的name");
					    	System.out.println(map.get("sex")+"获得第"+countNum+"的sex");
					    	System.out.println(map.get("card")+"获得第"+countNum+"的card");
					    	System.out.println(map.get("workStart")+"获得第"+countNum+"的workStart");
					    	System.out.println(map.get("university")+"获得第"+countNum+"的university");
					    	System.out.println(map.get("degree")+"获得第"+countNum+"的degree");
					    	System.out.println(map.get("proTitle")+"获得第"+countNum+"的proTitle");
					    	System.out.println(map.get("dept")+"获得第"+countNum+"的dept");
					    	System.out.println(map.get("workType")+"获得第"+countNum+"的workType");
					    	System.out.println(map.get("ssn")+"获得第"+countNum+"的workType");
					    	if(map.get("name") == null){
					    		messageCode = 402; //
								resMap.put("messageCode", messageCode);
								resMap.put("message", "导入文件中的第"+ countNum +"行的专职人名为空！");
								return  resMap;
//								 model.addAttribute("message", "导入文件中的第"+ countNum +"行的专职人名为空！");
//								 return "center/org/orgStaff";
							}
					    	
					    	if(map.get("sex") == null){
					    		messageCode = 402; //
								resMap.put("messageCode", messageCode);
								resMap.put("message", "导入文件中的第"+ countNum +"行的性别为空！");
								return  resMap;
//								 model.addAttribute("message", "导入文件中的第"+ countNum +"行的性别为空！");
//								 return "center/org/orgStaff";
							}
					    	if(map.get("card") == null){
					    		messageCode = 402; //
								resMap.put("messageCode", messageCode);
								resMap.put("message", "导入文件中的第"+ countNum +"行的身份证号为空！");
								return  resMap;
//								 model.addAttribute("message", "导入文件中的第"+ countNum +"行的身份证号为空！");
//								 return "center/org/orgStaff";
							}
							//在这里应该验证是否在系统数据库存在
							if(orgStaffService.findNumByCard((String)map.get("card"))>0){
								messageCode = 402; //
								resMap.put("messageCode", messageCode);
								resMap.put("message", "导入文件中的第"+ countNum +"行的身份证号为空！");
								return  resMap;
//								model.addAttribute("message", "导入文件中的第"+ countNum +"行的身份证号与系统中身份证号重复！");
//								 return "center/org/orgStaff";
							}
							if(map.get("workStart") == null){
								messageCode = 402; //
								resMap.put("messageCode", messageCode);
								resMap.put("message", "导入文件中的第"+ countNum +"行的工作开始时间为空！");
								return  resMap;
//								 model.addAttribute("message", "导入文件中的第"+ countNum +"行的工作开始时间为空！");
//								 return "center/org/orgStaff";
							}
							if(map.get("university") == null){
								messageCode = 402; //
								resMap.put("messageCode", messageCode);
								resMap.put("message", "导入文件中的第"+ countNum +"行的毕业学校为空！");
								return  resMap; 
//								model.addAttribute("message", "导入文件中的第"+ countNum +"行的毕业学校为空！");
//								 return "center/org/orgStaff";
							}
							if(map.get("degree") == null){
								messageCode = 402; //
								resMap.put("messageCode", messageCode);
								resMap.put("message", "导入文件中的第"+ countNum +"行的学历为空！");
								return  resMap; 
//								model.addAttribute("message", "导入文件中的第"+ countNum +"行的学历为空！");
//								 return "center/org/orgStaff";
							}
							if(map.get("proTitle") == null){
								messageCode = 402; //
								resMap.put("messageCode", messageCode);
								resMap.put("message", "导入文件中的第"+ countNum +"行的职称为空！");
								return  resMap;
//								model.addAttribute("message", "导入文件中的第"+ countNum +"行的职称为空！");
//								 return "center/org/orgStaff";
							}
							if(map.get("dept") == null){
								messageCode = 402; //
								resMap.put("messageCode", messageCode);
								resMap.put("message", "导入文件中的第"+ countNum +"行的部门为空！");
								return  resMap;
//								model.addAttribute("message", "导入文件中的第"+ countNum +"行的部门为空！");
//								 return "center/org/orgStaff";
							}
							if(map.get("workType") == null){
								messageCode = 402; //
								resMap.put("messageCode", messageCode);
								resMap.put("message", "导入文件中的第"+ countNum +"行的劳动关系为空！");
								return  resMap; 
//								model.addAttribute("message", "导入文件中的第"+ countNum +"行的劳动关系为空！");
//								 return "center/org/orgStaff";
							}
							if(map.get("ssn") == null){
								messageCode = 402; //
								resMap.put("messageCode", messageCode);
								resMap.put("message", "导入文件中的第"+ countNum +"行的社会保险号为空！");
								return  resMap; 
//								model.addAttribute("message", "导入文件中的第"+ countNum +"行的劳动关系为空！");
//								 return "center/org/orgStaff";
							}
							countNum ++;
						}
					    for (Map<String, Object> map : qmaps) {
					    	
					    	orgStaff.setId(null);
							orgStaff.setName((String) map.get("name"));
							 if("男".equals(map.get("sex"))){
								 orgStaff.setSex("1");	 
							 }else if("女".equals(map.get("sex"))){
								 orgStaff.setSex("2");
							 }else{
								 orgStaff.setSex("2");
							 }
							orgStaff.setCard((String) map.get("card"));
							orgStaff.setUniversity((String) map.get("university"));
							orgStaff.setDegree(DictUtils.getDictValue((String)map.get("degree"),"degree",(String)map.get("degree")));
							orgStaff.setWorkType(DictUtils.getDictValue((String)map.get("workType"),"work_type",(String)map.get("workType")));
							orgStaff.setProTitle((String) map.get("proTitle"));
							orgStaff.setDept((String)map.get("dept"));
							orgStaff.setWorkStart(dateformat.parse((String) map.get("workStart")));
							orgStaff.setSsn((String)map.get("ssn"));
							orgStaffService.save(orgStaff);
					    }
					    
						model.addAttribute("message", "上传专职文件成功");
					}else{
						model.addAttribute("message","导入文件为空");						
					}
					
				}else{
					model.addAttribute("message","导入文件失败");
				}
			} catch (IOException e) {

				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 

		}
		
//		Page<OrgStaff> page = orgStaffService.findPage(new Page<OrgStaff>(request, response), orgStaff);
//		model.addAttribute("page", page);
//		model.addAttribute("orgStaff", orgStaff);
		messageCode = 200; //
		resMap.put("messageCode", messageCode);
		return resMap;
//		return "center/org/orgStaff";
	}
	

	/**
	 * 备案上传专职人员的入口
	 * @param orgStaff
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "changeEntry")
	public String changeEntry(OrgStaff orgStaff, Model model,HttpSession session) {
		model.addAttribute("orgStaff", orgStaff);
		OrgUser user = (OrgUser) session.getAttribute("user");
		 if(user!=null){
			 OrgUser orgUser=orgUserService.get(user);
		 
			 if(orgUser.getOrg()!=null){
				List<OrgStaff> staffList=orgStaffService.findStaffByOrgId(orgUser.getOrg().getId());
				System.out.println(staffList+"获取下专职List");
				model.addAttribute("staffList", staffList);
			 }
		}else{
			return "center/login";	
		}
		return "center/change/changeOrgStaff";
	}
	
	

}