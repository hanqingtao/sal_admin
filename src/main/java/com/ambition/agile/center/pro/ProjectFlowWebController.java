/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.center.pro;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ambition.agile.common.BaseConfigHolder;
import com.ambition.agile.common.config.Global;
import com.ambition.agile.common.ftp.FtpConfigHolder;
import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.util.FtpUtils;
import com.ambition.agile.common.util.MailBean;
import com.ambition.agile.common.util.MailUtil;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.common.utils.StringUtils;
import com.ambition.agile.modules.org.entity.Org;
import com.ambition.agile.modules.org.entity.OrgUser;
import com.ambition.agile.modules.org.service.OrgService;
import com.ambition.agile.modules.org.service.OrgUserService;
import com.ambition.agile.modules.pro.entity.Project;
import com.ambition.agile.modules.pro.entity.ProjectFlow;
import com.ambition.agile.modules.pro.service.ProjectFlowService;
import com.ambition.agile.modules.pro.service.ProjectService;

/**
 * 项目流程Controller
 * @author harry
 * @version 2017-08-08
 */
@Controller
@RequestMapping(value = "${frontPath}/pro/projectFlow")
public class ProjectFlowWebController extends BaseController {

	@Autowired
	private ProjectFlowService projectFlowService;
	@Autowired
	private OrgUserService orgUserService;
	@Autowired
	private OrgService orgService;

	@Autowired
	private ProjectService projectService;
	@Value("${mail.from}")  
	private String mailFrom; 
	
	@Autowired
	private MailUtil mailUtil;
	@ModelAttribute
	public ProjectFlow get(@RequestParam(required=false) String id) {
		ProjectFlow entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = projectFlowService.get(id);
		}
		if (entity == null){
			entity = new ProjectFlow();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(ProjectFlow projectFlow, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ProjectFlow> page = projectFlowService.findPage(new Page<ProjectFlow>(request, response), projectFlow); 
		model.addAttribute("page", page);
		return "modules/pro/projectFlowList";
	}

	@RequestMapping(value = "form")
	public String form(ProjectFlow projectFlow, Model model) {
		model.addAttribute("projectFlow", projectFlow);
		return "modules/pro/projectFlowForm";
	}

	@RequestMapping(value = "save")
	public String save(ProjectFlow projectFlow, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, projectFlow)){
			return form(projectFlow, model);
		}
		projectFlowService.save(projectFlow);
		addMessage(redirectAttributes, "保存项目流程成功");
		return "redirect:"+Global.getFrontPath()+"/pro/projectFlow/?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(ProjectFlow projectFlow, RedirectAttributes redirectAttributes) {
		projectFlowService.delete(projectFlow);
		addMessage(redirectAttributes, "删除项目流程成功");
		return "redirect:"+Global.getFrontPath()+"/pro/projectFlow/?repage";
	}
    
	/**
	 * 进入上传公告的页面
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	
	@RequestMapping(value = "notice")
	public String notice(Model model,ProjectFlow projectFlow,String id,RedirectAttributes redirectAttributes,HttpSession session) {
	 
		String projectId=(String)session.getAttribute("projectId");
		if(projectId==null){
			session.setAttribute("projectId", id);
		}
		model.addAttribute("id",id);
		model.addAttribute("projectFlow",projectFlow);
		return "center/pro/notice";
	}
	
	/**
	 * 上传公告保存
	 * @param model
	 * @param project
	 * @param id
	 * @param noticeMedia
	 * @param redirectAttributes
	 * @return
	 */
	
	@RequestMapping(value = "noticeSave")
	public String noticeSave(@RequestParam(value = "file1", required = false) MultipartFile file1,
			HttpServletRequest request,Model model,ProjectFlow projectFlow,
			RedirectAttributes redirectAttributes,HttpSession session) {
		String headPicPath=null;
		try {
			if(file1 != null && StringUtils.isNotEmpty(file1.getOriginalFilename())){
				String path = "/projectFlow";
			    String fileName = FtpUtils.getUploadFileNameToDB(file1.getOriginalFilename());
			    headPicPath = path +  "/" + fileName;
				InputStream fileInput = file1.getInputStream();
				FtpUtils.uploadFile(path, fileName, fileInput);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		 projectFlow.setNoticeFile(headPicPath);
		 projectFlow.setNoticeStatus(1);
		 
		 projectFlowService.save(projectFlow);
		 /*ProjectFlow pf=projectFlowService.findInfoByProId(Integer.valueOf(projectId));
		 System.out.println(pf.getId()+"获取到的信息");*/ 
		model.addAttribute("message","上传公告成功");
		model.addAttribute("projectFlow",projectFlow);
//		model.addAttribute("imgServer", FtpConfigHolder.getFtpFileAddress());
		return "center/pro/tender";	
	}
	/**
	 * 进入上传招标文件的入口
	 * @param project
	 * @param request
	 * @param response
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "uploadBiddingDocEntry")
	public String uploadingBiddingDocuments(ProjectFlow projectFlow, HttpServletRequest request, HttpServletResponse response, Model model,HttpSession session) {
		  
		model.addAttribute("projectFlow", projectFlow);
//		model.addAttribute("imgServer", FtpConfigHolder.getFtpFileAddress());
		return "center/pro/tender";
	}
	/**
	 * 进入上传招标报告的入口
	 * @param project
	 * @param request
	 * @param response
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "uploadBidReportEntry")
	public String uploadBidReport(ProjectFlow projectFlow, HttpServletRequest request, 
			HttpServletResponse response,Model model,HttpSession session) {
		  
		model.addAttribute("projectFlowt", projectFlow);
//		model.addAttribute("imgServer", FtpConfigHolder.getFtpFileAddress());
		return "center/pro/report";
	}
	/**
	 * 保存上传招标文件
	 * @param file1
	 * @param request
	 * @param model
	 * @param project
	 * @param id
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "tenderSave")
	public String tenderSave(@RequestParam(value = "file1", required = false) MultipartFile file1,
			HttpServletRequest request, Model model, ProjectFlow projectFlow, 
			RedirectAttributes redirectAttributes,HttpSession session) {	
		try {
			if(file1 != null && StringUtils.isNotEmpty(file1.getOriginalFilename())){
				String path = "/projectFlow";
			    String fileName = FtpUtils.getUploadFileNameToDB(file1.getOriginalFilename());
			    String headPicPath  = path +  "/" + fileName;
				InputStream fileInput = file1.getInputStream();
				FtpUtils.uploadFile(path, fileName, fileInput);
				projectFlow.setTenderFile(headPicPath);
				projectFlow.setTenderStatus(1);
			}
		} catch (Exception e) {
				e.printStackTrace();
		}
		projectFlowService.save(projectFlow);
		model.addAttribute("mseeage","上传招标文件成功");
		model.addAttribute("projectFlow",projectFlow);
//		model.addAttribute("imgServer", FtpConfigHolder.getFtpFileAddress());
		return "center/pro/report";	
	}
	
	
	/**
	 * 进入上传评标报报告内容
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	
	@RequestMapping(value = "report")
	public String report(Model model,ProjectFlow projectFlow,RedirectAttributes redirectAttributes) {
		model.addAttribute("projectFlow",projectFlow);
		model.addAttribute("message","进入上传报告页面");
		return "center/pro/report";
	}
	
	
	/**
	 * 保存上传评标信息
	 * @param file1
	 * @param file2
	 * @param projectFlow
	 * @param model
	 * @param id
	 * @param redirectAttributes
	 * @return
	 */
	
	@RequestMapping(value = "reportSave")
	public String reportSave(@RequestParam(value = "file1", required = false) MultipartFile file1, 
			@RequestParam(value = "file2", required = false) MultipartFile file2,
			ProjectFlow projectFlow, Model model, RedirectAttributes redirectAttributes,HttpSession session) {
//		if (!beanValidator(model, projectFlow)){
//			return form(projectFlow, model);
//		}
		 
		try {
			if(file1 != null && StringUtils.isNotEmpty(file1.getOriginalFilename())&&file2 != null && StringUtils.isNotEmpty(file2.getOriginalFilename())){
				String path = "/projectFlow";
			    String fileName1 = FtpUtils.getUploadFileNameToDB(file1.getOriginalFilename());
			    String fileName2 = FtpUtils.getUploadFileNameToDB(file2.getOriginalFilename());
			    
			    String headPicPath1= path +  "/" + fileName1;
			    String headPicPath2 = path +  "/" + fileName2;
				
			    InputStream fileInput1 = file1.getInputStream();
				InputStream fileInput2 = file2.getInputStream();
				
				FtpUtils.uploadFile(path, fileName1, fileInput1);
				FtpUtils.uploadFile(path, fileName2, fileInput2);
				projectFlow.setFileEvaluation(headPicPath1);
				projectFlow.setFileEvaluationexpert(headPicPath2);
				projectFlow.setReportStatus(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		projectFlowService.save(projectFlow);
		model.addAttribute("message", "保存评标报告成功");
		model.addAttribute("projectFlow", projectFlow);
//		model.addAttribute("imgServer", FtpConfigHolder.getFtpFileAddress());
		return "center/pro/result";
	}
	
	/**
	 * 保存上传中标结果
	 * @param projectFlow
	 * @param model
	 * @param id
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "result")
	public String result(ProjectFlow projectFlow,Model model,RedirectAttributes redirectAttributes) {
		projectFlowService.delete(projectFlow);
		model.addAttribute("projectFlow",projectFlow);	
		model.addAttribute("message", "进入上传中标结果页面");
		return "center/pro/result";
	}
	/**
	 * 进入上传招标结果的入口
	 * @param project
	 * @param request
	 * @param response
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "uploadBidResultEntry")
	public String uploadingbidResult(ProjectFlow projectFlow, HttpServletRequest request, HttpServletResponse response, Model model,HttpSession session) {
	       
		model.addAttribute("projectFlow", projectFlow);
//		model.addAttribute("imgServer", FtpConfigHolder.getFtpFileAddress());
		return "center/pro/result";
	}
	/**
	 * 保存中标结果信息
	 * @param file1
	 * @param projectFlow
	 * @param model
	 * @param id
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "bidSave")
	public String bidSave(@RequestParam(value = "file1", required = false) MultipartFile file1,ProjectFlow projectFlow, Model model, RedirectAttributes redirectAttributes,HttpSession session) {
//		if (!beanValidator(model, projectFlow)){
//			return form(projectFlow, model);
//		}
		String headPicPath1=null;
		try {
			if(file1 != null && StringUtils.isNotEmpty(file1.getOriginalFilename())){
				String path = "/projectFlow";
			    String fileName1 = FtpUtils.getUploadFileNameToDB(file1.getOriginalFilename());
				headPicPath1 = path +  "/" + fileName1;
				InputStream fileInput1 = file1.getInputStream();
				FtpUtils.uploadFile(path, fileName1, fileInput1);
				projectFlow.setFileBid(headPicPath1);
				projectFlow.setBidStatus(1);
			}
		} catch (Exception e) {
				e.printStackTrace();
		}
            
//		Project project=projectService.get(String.valueOf(projectFlow.getProjectId()));
//		System.out.println(project+"保存标签的信息");
//		project.setStatus(1);
//		projectService.save(project);
		  
		projectFlowService.save(projectFlow);
		addMessage(redirectAttributes, "保存项目流程成功");
		model.addAttribute("message", "保存中标结果信息");
		model.addAttribute("projectFlow", projectFlow);	

		// 查看是否需要更新项目状态
		if(projectFlow.getNoticeStatus() != null && projectFlow.getNoticeStatus() == 1
				&& projectFlow.getTenderStatus() != null && projectFlow.getTenderStatus() == 1
				&& projectFlow.getReportStatus() != null && projectFlow.getReportStatus() == 1
				&& projectFlow.getBidStatus() != null && projectFlow.getBidStatus() == 1){                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
			Project project = projectService.get(String.valueOf(projectFlow.getProjectId()));
			project.setStatus(1);
			project.setBidMoney(projectFlow.getBidMoney()+"");
			project.setBidTime(projectFlow.getBidnoticeTime());
			project.setBidUnit(projectFlow.getBidUnit());
			projectService.save(project);
			
			
			StringBuffer maiContents = new StringBuffer(255);
		    MailBean mailBean = new MailBean();
		    String mailFrom = Global.getConfig("mail.from");//"dragonhanqingtao@163.com";
		    System.out.println("##################@@@ mailFrom:"+mailFrom);
		    mailBean.setFrom(mailFrom);
		    mailBean.setSubject("中央投资项目招标服务与管理平台通知邮件");  
			
		    String mail=null;
			
			//更新Org的三年内业绩额度
			OrgUser user=(OrgUser)session.getAttribute("user");
			if(user != null){
				OrgUser orgUser=orgUserService.get(user);
				mail=orgUser.getEmail();
				Org org = orgService.get(orgUser.getOrg());
				orgService.updateRecentMoney(org);
			}
			
			try{
				   String[] addresses = {mail};
					mailBean.setToEmails(addresses); 
				maiContents.append("你发布的"+project.getName()+"项目已发布到项目目录，向社会公众公示。 ");
				mailBean.setTemplate(maiContents.toString());  
		    }catch(Exception e){
		        e.printStackTrace();
		    }
		    
		    //发送邮件  
		    try{  
		       mailUtil.send(mailBean);
		    } catch (Exception e) {  
		       e.printStackTrace();  
		    } 
		    
		    
		    
		}
//		model.addAttribute("imgServer", FtpConfigHolder.getFtpFileAddress());
		return "center/pro/result";
	}
	
}
