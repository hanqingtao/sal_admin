/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.org.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ambition.agile.common.config.Global;
import com.ambition.agile.common.util.MailBean;
import com.ambition.agile.common.util.MailUtil;
import com.ambition.agile.modules.org.constant.OrgConstant;
import com.ambition.agile.modules.org.dao.OrgDao;
import com.ambition.agile.modules.org.entity.Org;

/**
 * 代理机构Service
 * @author harry
 * @version 2017-08-02
 */
//@Transactional(readOnly = true)
@Service
@Lazy(false)
public class OrgTaskService {
	private static int lastsDay = 3; //
	 @Resource
	private OrgDao orgDao;
	 @Resource
	 private OrgService orgService;
	 
	 @Value("${mail.from}")  
		private String mailFrom; 
		
		@Autowired
		private MailUtil mailUtil;
	 
	 @Resource
	 private OrgUserService orgUserService;
	 
	 /*
	 @Scheduled(cron="0/1 * * * * ? ")  
	 public void myTestWork(){  
	      System.out.println("##########ssss");  
	 }
	
	*/
	 /**
	  * 审核任务
	  */
	 @Scheduled(cron = "0 15 3 * * ?")  
	 public void auditTask(){  
		 System.out.println("定时任务开始...");  
		 Map<String, Object> pMap = new HashMap<>();
		 pMap.put("lastsDay", lastsDay);
		 //查询位于提交中的Org（status = 1），并且 提交日期 到 当前时间 达到3天以上 ，设置自动省通过2
		 pMap.put("status", 1);
		 List<Org> status1Orgs = orgDao.getStatus1OrgsByLastsDay(pMap);
		 System.out.println("status1Orgs:size:"+status1Orgs.size());
		 
		//创建邮件  
			StringBuffer maiContents = new StringBuffer(255);
		    MailBean mailBean = new MailBean();
		    String mailFrom = Global.getConfig("mail.from");//"dragonhanqingtao@163.com";
		    System.out.println("##################@@@ mailFrom:"+mailFrom);
		    mailBean.setFrom(mailFrom);
		    mailBean.setSubject("中央投资项目招标服务与管理平台通知邮件");  
		    
		 
		 for(Org org:status1Orgs){
			 org.setStatus(OrgConstant.ORG_STATUS_PROVINCE);
			 org.setAuditTime(new Date());
			 orgService.save(org);

			 try{
				 String[] addresses = {org.getOrgUser().getEmail()};
					mailBean.setToEmails(addresses); 
					maiContents.append("备案申请已经通过省发展改革委的审核，现已提交到招标中心，审核一般需要4-7个工作日，请耐心等待。审核通过后会有邮件通知。 ");
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
		 
		 
		 //查询位于省通过中的Org（status = 2），并且 省审核日期 到 当前时间 达到3天以上 ，设置自动招标中心通过 3
		 pMap.put("status", 2);
		 List<Org> status2Orgs = orgDao.getStatus2OrgsByLastsDay(pMap);
		 System.out.println("status2Orgs:size:"+status2Orgs.size());
		 for(Org org:status2Orgs){
			 org.setStatus(OrgConstant.ORG_STATUS_PASS);
			 org.setFinalTime(new Date());
			 orgService.save(org);
			 try{
				 String[] addresses = {org.getOrgUser().getEmail()};
					mailBean.setToEmails(addresses); 
					maiContents.append("恭喜你，你的备案申请已经通过， 列入机构名录！ ");
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
		 
		 
		 
		 System.out.println("定时任务结束...");  
	 }
}