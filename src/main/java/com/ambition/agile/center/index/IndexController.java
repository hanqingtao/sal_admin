/**
 * Copyright &copy; 2012-2016   All rights reserved.
 */
package com.ambition.agile.center.index;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ambition.agile.center.cms.constant.ArticleConstant;
import com.ambition.agile.common.ApiResponse;
import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.util.DateTimeUtil;
import com.ambition.agile.common.utils.StringUtils;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.modules.cms.entity.Article;
import com.ambition.agile.modules.cms.entity.ArticleData;
import com.ambition.agile.modules.cms.entity.Category;
import com.ambition.agile.modules.cms.service.ArticleDataService;
import com.ambition.agile.modules.cms.service.ArticleService;
import com.ambition.agile.modules.org.constant.OrgConstant;
import com.ambition.agile.modules.org.entity.Org;
import com.ambition.agile.modules.org.service.OrgService;
import com.ambition.agile.modules.pro.constant.ProConstant;
import com.ambition.agile.modules.pro.entity.CreditRecord;
import com.ambition.agile.modules.pro.entity.Project;
import com.ambition.agile.modules.pro.service.CreditRecordService;
import com.ambition.agile.modules.pro.service.ProjectService;

/**
 * 网站首页 Controller
 * @author harry
 * @version 2017-5-29
 */
@Controller
@RequestMapping(value = "${frontPath}")
public class IndexController extends BaseController{
	
	@Autowired
	private OrgService orgService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private ArticleDataService articleDataService;
	@Autowired
	private CreditRecordService  creditRecordService;
	/**
	 * 网站首页
	 */
	@RequestMapping
	public String index(Model model,Org org,Project project) {
	    
		List<Org> orgList=orgService.findOrgInfoByNum();
		List<Project> projectList=projectService.findProjectInfoByNum();
		
		model.addAttribute("orgList",orgList);
		model.addAttribute("projectList",projectList);
		return "index";
	}
	/**
	 *首页进入代理机构
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping(value = "agent")
	public String orgEntry(HttpServletRequest request, HttpServletResponse response,Model model,Org org) {
		return "center/agent/agentList";
	}
	/**
	 * 进入中央投资项目
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "pro")
	public String proEntry(Project project,HttpServletRequest request, HttpServletResponse response,Model model) {
		project.setStatus(ProConstant.PROJECT_STATUS_PASS);
		Page<Project> pageQuery = new Page<Project>(request, response);
		pageQuery.setOrderBy("bid_time");
		Page<Project> page = projectService.findPage(pageQuery, project);
		   model.addAttribute("page",page);
		
		return "center/investmentPro/proList";
	}
	
	/**
	 * 黑名单内容
	 * @param request
	 * @param org
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "black")
	public ModelAndView black(HttpServletRequest request,Org org,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		org.setStatus(OrgConstant.ORG_STATUS_BLANK);
		Page<Org> page = orgService.findPage(new Page<Org>(request, response), org);
		  mv.addObject("page",page);
		mv.setViewName("center/black/blackList");
		return mv;
	}
	
	/**
	 * 加载日常监督
	 * @param request
	 * @param org
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "prison")
	public String prison(Model model,HttpServletResponse response,Article article, HttpServletRequest request,Category category,RedirectAttributes redirectAttributes, HttpSession session,String categoryId) {
		Page<Article> page =null;
		if(StringUtils.isNotEmpty(categoryId)){
			 category.setId(categoryId);
			 article.setCategory(category);
			 page= articleService.findPage(new Page<Article>(request, response), article);
			
			  
		}
		model.addAttribute("page",page);
		return "center/supervise/superviseList";
		 
	}
	
	
	/**
	 * 加载信用记录
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "credit")
	public String credit(Model model,HttpServletRequest request,CreditRecord creditRecord,HttpServletResponse response) {
		 
		Page<CreditRecord> page = creditRecordService.findPage(new Page<CreditRecord>(request, response), creditRecord);
		
		model.addAttribute("page",page);
		 
		return "center/credit/credit";
	}
	/**
	 * 加载违规投诉内容
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "complaint")
	public ModelAndView complaint(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("center/complaint/complaint");
		return mv;
	}
	/**
	 * 更多新闻
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "news")
	public String getAll(Model model,String id,String delFlag,HttpServletResponse response,Article article, HttpServletRequest request,Category category,RedirectAttributes redirectAttributes, HttpSession session,String categoryId) {
		 
		  Page<Article> page =null;
		if(StringUtils.isNotEmpty(categoryId)){
			ArticleData  articleData=articleDataService.get(article.getId());
			 category.setId(categoryId);
			 article.setCategory(category);
			 article.setDelFlag(ArticleConstant.POSTED_1);
			 page= articleService.findPage(new Page<Article>(request, response), article);
			 model.addAttribute("articleData",articleData);
			  
		}
		model.addAttribute("page",page);
		model.addAttribute("categoryId",categoryId);  
		model.addAttribute("article",article);  
		model.addAttribute("id",id);   
		return "center/news/newsList";
	}
	
	@RequestMapping(value = "index/getOrgStaticForIndex")
	@ResponseBody
	public ApiResponse<List<Map<String, Object>>> getOrgStaticForIndex(HttpServletRequest request, HttpServletResponse response, 
			String type, String param){
		//System.out.println("------------------------------" + type + " - " + param);
		if(StringUtils.isEmpty(type)){
			return ApiResponse.fail(301, "参数有误！");
		}
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		if("area".equals(type) && "1".equals(param)){
			param = "";
		}
		paramsMap.put(type, param);
		List<Map<String, Object>> list = orgService.getOrgStaticForIndex(paramsMap);
		return ApiResponse.success(list);
	}
	
	/**
	 * 加载区域统计页面
	 * @param model
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "index/toIndexAreaTop")
	public String toIndexArea(Model model, HttpServletResponse response, HttpServletRequest request) {
		return "center/common/indexAreaTop";
	}
	
	/**
	 * 获取区域的统计信息
	 * @param request
	 * @param response
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "index/getIndexAreaTop")
	@ResponseBody
	public ApiResponse<?> getIndexAreaTop(HttpServletRequest request, HttpServletResponse response, 
			String code, Integer id){
		//System.out.println("------------------------------" + code);
		if(StringUtils.isEmpty(code)){
			return ApiResponse.fail(301, "参数有误！");
		}
		Map<String,Object> parPer=new HashMap<String,Object>();
		parPer.put("areaId",id);
		Map tmap = orgService.getAreaStaticByAreaIdYear(parPer);
		return ApiResponse.success(tmap);
	}
	
	@RequestMapping(value = "index/getIndexAreaDown")
	public String getIndexAreaDown(Model model,Integer areaId,HttpServletResponse response, HttpServletRequest request) {
		//System.out.println("=========================================" + DateTimeUtil.getYear() + "年 " + DateTimeUtil.getMonth() + "月");
		Integer year = DateTimeUtil.getYear();
		Integer month = DateTimeUtil.getMonth();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(areaId != null && areaId != 0){			
			paramMap.put("areaId", areaId);
		}
		Map tmap = orgService.getArearStaticByAreaIdWeek(paramMap);
		model.addAttribute("projectCountStr", tmap.get("project_count1") + "," + tmap.get("project_count2") + "," + tmap.get("project_count3") + "," + tmap.get("project_count4"));
		model.addAttribute("bidMoneyStr", tmap.get("bid_money1") + "," + tmap.get("bid_money2") + "," + tmap.get("bid_money3") + "," + tmap.get("bid_money4"));
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		return "center/common/indexAreaDown";
	}
	
}
