/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.pro.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ambition.agile.common.config.Global;
import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.util.MailBean;
import com.ambition.agile.common.util.MailUtil;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.common.utils.StringUtils;
import com.ambition.agile.modules.org.entity.Org;
import com.ambition.agile.modules.org.service.OrgService;
import com.ambition.agile.modules.org.service.OrgUserService;
import com.ambition.agile.modules.pro.entity.CreditRecord;
import com.ambition.agile.modules.pro.service.CreditRecordService;

/**
 * 信用记录Controller
 * @author harry
 * @version 2017-10-18
 */
@Controller
@RequestMapping(value = "${adminPath}/pro/creditRecord")
public class CreditRecordController extends BaseController {
	
	@Value("${mail.from}")  
	private String mailFrom; 
	
	@Autowired
	private MailUtil mailUtil;

	@Autowired
	private CreditRecordService creditRecordService;
	
	@Autowired
	private OrgService orgService;
	
	@Autowired
	private OrgUserService orgUserService;
	
	@ModelAttribute
	public CreditRecord get(@RequestParam(required=false) String id) {
		CreditRecord entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = creditRecordService.get(id);
		}
		if (entity == null){
			entity = new CreditRecord();
		}
		return entity;
	}
	
	@RequiresPermissions("pro:creditRecord:view")
	@RequestMapping(value = {"list", ""})
	public String list(CreditRecord creditRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CreditRecord> page = creditRecordService.findPage(new Page<CreditRecord>(request, response), creditRecord); 
		model.addAttribute("page", page);
		return "modules/pro/creditRecordList";
	}

	@RequiresPermissions("pro:creditRecord:view")
	@RequestMapping(value = "form")
	public String form(CreditRecord creditRecord, Model model,String orgId) {
		if(StringUtils.isNotEmpty(orgId)){
			Org org=orgService.get(orgId);
			model.addAttribute("creditRecord", creditRecord);
			model.addAttribute("org",org);	
		}
		return "modules/pro/creditRecordForm";
	}

	@RequiresPermissions("pro:creditRecord:edit")
	@RequestMapping(value = "save")
	public String save(CreditRecord creditRecord, Model model,Org org, RedirectAttributes redirectAttributes,String orgId,HttpServletRequest request,HttpServletResponse response) {
		if (!beanValidator(model, creditRecord)){
			return form(creditRecord, model,orgId);
		}
		org.setId(orgId);
		creditRecord.setOrg(org);
		creditRecordService.save(creditRecord);
		addMessage(redirectAttributes, "保存信用记录成功");
		List<String> list=orgUserService.findEmailById(orgId);
		
		 
		CreditRecord creditRecordObj=new CreditRecord();
		org=orgService.get(orgId);
		creditRecordObj.setOrg(org);
		Page<CreditRecord> page = creditRecordService.findPage(new Page<CreditRecord>(request, response), creditRecordObj); 
		
		
		//创建邮件  
		StringBuffer maiContents = new StringBuffer(255);
	    MailBean mailBean = new MailBean();
	    String mailFrom = Global.getConfig("mail.from");//"dragonhanqingtao@163.com";
	    System.out.println("##################@@@ mailFrom:"+mailFrom);
	    mailBean.setFrom(mailFrom);
	    mailBean.setSubject("中央投资项目招标服务与管理平台通知邮件");  
	    
			    try{
			    	 String[] addresses = {list.get(0)};
					 mailBean.setToEmails(addresses); 
					maiContents.append("你收到了一条信用记录");
					maiContents.append("<table border='1'>");
					maiContents.append("    <tr>");
					maiContents.append("        <th style='width:158px;'>被处理的代理机构名称</th>");
					maiContents.append("        <th style='width:110px;'>项目编号</th>");
					maiContents.append("        <th style='width:186px;'>项目名称</th>");
					maiContents.append("        <th style='width:80px;'>负责人</th>");
					maiContents.append("        <th style='width:400px;'>违规情况</th>");
					maiContents.append("        <th style='width:132px;'>处理决定</th>");
					maiContents.append("        <th style='width:132px;'>时间</th>");
					maiContents.append("    </tr>");
					maiContents.append("        <tr>");
					 maiContents.append("            <td>"+creditRecord.getOrg().getName()+"</td>");
					maiContents.append("            <td>"+creditRecord.getProjectCode()+"</td>");
					maiContents.append("            <td>"+creditRecord.getProjectName()+"</td>");
					maiContents.append("            <td>"+creditRecord.getLeader()+"</td>");
					maiContents.append("            <td>"+creditRecord.getInstruction()+"</td>");
					maiContents.append("            <td>"+creditRecord.getResult()+"</td>");
					maiContents.append("            <td>"+creditRecord.getCreateTime()+"</td>");
					maiContents.append("        </tr>");
					maiContents.append("  ");
					maiContents.append("");
					maiContents.append("</table>");
					
					
					
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
		model.addAttribute("page", page);
		model.addAttribute("org", org);
		
		return "modules/pro/creditRecordNoteList";
	}
	
	@RequiresPermissions("pro:creditRecord:edit")
	@RequestMapping(value = "delete")
	public String delete(CreditRecord creditRecord, RedirectAttributes redirectAttributes) {
		creditRecordService.delete(creditRecord);
		addMessage(redirectAttributes, "删除信用记录成功");
		return "redirect:"+Global.getAdminPath()+"/pro/creditRecord/?repage";
	}
	
	@RequiresPermissions("pro:creditRecord:edit")
	@RequestMapping(value = "find")
	public String find(CreditRecord creditRecord,Org org,Model model, RedirectAttributes redirectAttributes,HttpServletRequest request,HttpServletResponse response) {
		if(org!=null){
			org=orgService.get(org);
			CreditRecord creditRecordObj=new CreditRecord();
					creditRecordObj.setOrg(org);
		 
			Page<CreditRecord> page = creditRecordService.findPage(new Page<CreditRecord>(request, response), creditRecordObj); 
			model.addAttribute("page", page);
			model.addAttribute("org", org);
			
		}
		return "modules/pro/creditRecordNoteList";	
	}

}
