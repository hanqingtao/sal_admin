/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.org.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.ambition.agile.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 

import com.ambition.agile.common.persistence.DataEntity;
import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.common.util.ComUtil;
import com.ambition.agile.common.util.MailBean;
import com.ambition.agile.common.util.MailUtil;
import com.ambition.agile.modules.org.entity.Org;
import com.ambition.agile.modules.org.entity.OrgAudit;
import com.ambition.agile.modules.pro.dao.ProjectDao;
import com.ambition.agile.modules.org.constant.OrgConstant;
import com.ambition.agile.modules.org.dao.OrgAuditDao;
import com.ambition.agile.modules.org.dao.OrgDao;
import com.ambition.agile.modules.org.dao.OrgUserDao;

/**
 * 代理机构Service
 * @author harry
 * @version 2017-08-02
 */
@Service
@Transactional(readOnly = true)
public class OrgService extends CrudService<OrgDao, Org> {
	
	@Value("${mail.from}")  
	private String mailFrom; 
	
	@Autowired
	private MailUtil mailUtil;
	 @Resource
	private OrgDao orgDao;
	 @Resource
	 private ProjectDao projectDao;
	 @Resource
	 private OrgUserDao orgUserDao;
	 
	 @Resource
	 private OrgAuditDao orgAuditDao;

	public Org get(String id) {
		return super.get(id);
	}
	
	public List<Org> findList(Org org) {
		return super.findList(org);
	}
	
	public Page<Org> findPage(Page<Org> page, Org org) {
		return super.findPage(page, org);
	}
	
	@Transactional(readOnly = false)
	public void save(Org org) {
		super.save(org);
	}
	
	@Transactional(readOnly = false)
	public void delete(Org org) {
		super.delete(org);
	}
	
	public int selectOrgcode(String sc_code) {
		return orgDao.selectOrgcode(sc_code);
	}
	
	
	public List<Org> findOrgInfoByNum() {
		return orgDao.findOrgInfoByNum();
	}
	
	public int getOrgByScCode(Org org) {
		return orgDao.getOrgByScCode(org);
	}
	public void update(Org org){
		orgDao.update(org);
	}
	
	
	public List<Org> getOrgByNature(String nature) {
		return orgDao.getOrgByNature(nature);
	}
	
	/**
	 * 获取区域下的统计信息（机构总数、项目总数）
	 * @param areaId
	 * @return
	 */
	public Map<String, Integer> getAreaStaticByAreaId(Integer areaId){
		return orgDao.getAreaStaticByAreaId(areaId);
	}
	
	/**
	 * 获取区域下当年的统计信息（项目数、中标金额）
	 * @param areaId
	 * @return
	 */
	//public Map<String, Integer> getAreaStaticByAreaIdYear(Integer areaId){
		public Map<String, Integer> getAreaStaticByAreaIdYear(Map map){
		return orgDao.getAreaStaticByAreaIdYear(map);
	}
	
	/**
	 * 获取区域下当月每周的统计信息（项目数、中标金额）
	 * @param areaId
	 * @return
	 */
	public Map<String, Integer> getArearStaticByAreaIdWeek(Map<String, Object> map){
		return orgDao.getArearStaticByAreaIdWeek(map);
	}
	
	/**
	 * 获取首页的招标业绩统计信息
	 * @param param
	 * @return
	 */
	public List<Map<String, Object>> getOrgStaticForIndex(Map<String, Object> map){
		return orgDao.getOrgStaticForIndex(map);
	}
	
	/**
	 * 获取某个区域下机构个数
	 * @param areaId
	 * @return
	 */
	public Integer getOrgCountByAreaId(Integer areaId) {
		return orgDao.getOrgCountByAreaId(areaId);
	}
	/**
	 * 获取制定个数的机构
	 * @param num
	 * @return
	 */
	public List<Org> getLimitOrgs(Map<String, Object> paramMap) {
		return orgDao.getLimitOrgs(paramMap);
	}
	/**
	 * 更新企业近三年业绩
	 * @param org
	 */ 
	@Transactional(readOnly = false)
	public void updateRecentMoney(Org org) {
		//近三年项目金额
		Double recentMoney1 = projectDao.getProRecentMoney(org); //status = 1 and 3年内
		if(ComUtil.isNullOrEmpty(recentMoney1)){
			recentMoney1 = 0d;
		}
		//近三年业绩金额
		Double recentMoney2 = projectDao.getAhieveRecentMoney(org); //isHistory = 1 and 3年内
		if(ComUtil.isNullOrEmpty(recentMoney2)){
			recentMoney2 = 0d;
		}
		org.setRecentMoney(recentMoney1 + recentMoney2);
		orgDao.updateRecentMoney(org);
		
	}
	 

	@Transactional(readOnly = false)
	public void updateStateOn(Org org) {
		orgDao.updateStateOn(org); 
	}
	
	@Transactional(readOnly = false)
	public void updateById(String OperationFlag,String idstr){
		 
		if(StringUtils.isNotEmpty(OperationFlag)&&StringUtils.isNotEmpty(idstr)){
		 List<String> mailList=orgUserDao.findEmailById(idstr);
		 List<String> ids = Arrays.asList(StringUtils.split(idstr,","));  
		 
		//创建邮件  
			StringBuffer maiContents = new StringBuffer(255);
		    MailBean mailBean = new MailBean();
		    mailBean.setFrom(mailFrom);
		    mailBean.setSubject("中央投资项目招标服务与管理平台通知邮件");  
		     int size = mailList.size();  
		     String[] arr = (String[])mailList.toArray(new String[size]);  
		     
		   
		
		 Map<String,Object> map=new HashMap<String,Object>();
		
		if(OrgConstant.ORG_STATUS_STOP8.equals(OperationFlag)){
			//暂停
			 map.put("status", OrgConstant.ORG_STATUS_STOP8);
			 map.put("id",ids);
			 orgDao.updateById(map);
		}else if(OrgConstant.ORG_STATUS_USE9.equals(OperationFlag)){
			//启动
			 map.put("status", OrgConstant.ORG_STATUS_USE9);
			 map.put("id",ids);
			 orgDao.updateById(map);
		}else if(OrgConstant.ORG_STATUS_BLANK.equals(OperationFlag)){
			 map.put("status", OrgConstant.ORG_STATUS_BLANK);
			 map.put("id",ids);
			 orgDao.updateById(map);
			    try{
			    	 
			    	mailBean.setToEmails(arr); 
					maiContents.append("你的公司已被列入黑名单");
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
			    
				
		}else if(OrgConstant.ORG_STATUS_PROVINCE.equals(OperationFlag)||OrgConstant.ORG_STATUS_PROVINCE5.equals(OperationFlag)){
			 //省发改委通过和不通过
			
			 try{
			    	mailBean.setToEmails(arr); 
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
			
		}else if(OrgConstant.ORG_STATUS_PASS.equals(OperationFlag)){
			//招标中心通过
			 try{
			    	mailBean.setToEmails(arr); 
					maiContents.append("你的备案申请已经通过， 列入机构名录！ ");
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
		}else if(OrgConstant.ORG_STATUS_PROVINCE6.equals(OperationFlag)){
			OrgAudit orgAudit=new OrgAudit();
			
			//招标中心拒绝
			 try{
			    	mailBean.setToEmails(arr); 
					maiContents.append("招标中心拒绝了你的备案申请，以下是这次备案申请未通过的原因。<br/>"+orgAuditDao.get(orgAudit.getId()).getContent());
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
		 
	}
		
	}
}