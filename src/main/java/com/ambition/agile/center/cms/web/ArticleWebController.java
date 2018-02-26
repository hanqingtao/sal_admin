package com.ambition.agile.center.cms.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ambition.agile.center.cms.constant.ArticleConstant;
import com.ambition.agile.common.config.Global;
import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.util.FtpUtils;
import com.ambition.agile.common.utils.StringUtils;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.modules.cms.entity.Article;
import com.ambition.agile.modules.cms.entity.ArticleData;
import com.ambition.agile.modules.cms.entity.Category;
import com.ambition.agile.modules.cms.entity.Site;
import com.ambition.agile.modules.cms.service.ArticleDataService;
import com.ambition.agile.modules.cms.service.ArticleService;
import com.ambition.agile.modules.cms.service.CategoryService;
import com.ambition.agile.modules.sys.utils.UserUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

 

@Controller
@RequestMapping(value = "${frontPath}/cms/article")
public class ArticleWebController extends BaseController{
	@Autowired
	private ArticleService articleService;
	
	
	@Autowired
	private CategoryService categoryService;
	
	
	@Autowired
	private ArticleDataService articleDataService;
	/**
	 * 首页获取7条内容
	 * @param model
	 * @param response
	 * @param page
	 * @param article
	 * @param category
	 * @param redirectAttributes
	 * @param session
	 * @param categoryId
	 */
	@RequestMapping(value = "Seven")
	public void getSerSevenStrip(Model model,HttpServletResponse response,Page page,Article article, Category category,RedirectAttributes redirectAttributes, HttpSession session,String categoryId) {
		if(StringUtils.isNotEmpty(categoryId)){
			 category.setId(categoryId);
			 article.setCategory(category);
			 article.setDelFlag(ArticleConstant.POSTED_1);
			 List<Article> articleList=articleService.findList(article);
			 if(articleList.size()>=8){
				 articleList=articleList.subList(0, 7);
			 }
			 
			  ObjectMapper mapper = new ObjectMapper();
				try {
					String str = mapper.writeValueAsString(articleList);
					PrintWriter writer = response.getWriter();
					writer.println(str);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		
	}
	
	
	/**
	 * 查看新闻详情
	 * @param model
	 * @param response
	 * @param page
	 * @param article
	 * @param category
	 * @param redirectAttributes
	 * @param session
	 * @param categoryId
	 */
	@RequestMapping(value = "con")
	public String getCon(Model model,HttpServletResponse response,Article article,ArticleData  articleData,Page page,String id, Category category,RedirectAttributes redirectAttributes, HttpSession session,String categoryId) {
		  if(StringUtils.isNotEmpty(id)){
			  articleData=articleDataService.get(id);
			  article= articleService.get(id);
		  }
		  model.addAttribute("articleData",articleData);
		  model.addAttribute("article",article);
		return "center/common/newsDetails";
	}
	/**
	 * 预览新闻详情
	 * @param model
	 * @param response
	 * @param page
	 * @param article
	 * @param category
	 * @param redirectAttributes
	 * @param session
	 * @param categoryId
	 */
	@RequestMapping(value = "preview")
	public String preview(Model model,HttpServletResponse response,Article article,ArticleData  articleData,Page page,String id, Category category,RedirectAttributes redirectAttributes, HttpSession session,String categoryId) {
		  if(StringUtils.isNotEmpty(id)){
			  articleData=articleDataService.get(id);
			  article= articleService.get(id);
		  }
		  model.addAttribute("articleData",articleData);
		  model.addAttribute("article",article);
		return "center/common/newsPreview";
	}
	
	 
	
	/**
	 * 获取新闻
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "all")
	public String getAll(Model model,HttpServletResponse response,Page<Article> page,Article article, HttpServletRequest request,Category category,RedirectAttributes redirectAttributes, HttpSession session,String categoryId) {
		  
		if(StringUtils.isNotEmpty(categoryId)){
			 category.setId(categoryId);
			 article.setCategory(category);
			 article.setDelFlag(ArticleConstant.POSTED_1);
			 page= articleService.findPage(new Page<Article>(request, response), article);
			 
			  
		}
		model.addAttribute("page",page);
		return "center/news/newsList";
	}
	 
	/**
	 * 获取新闻
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "list")
	public String list(Model model,HttpServletResponse response,Page<Article> page,Article article, HttpServletRequest request,Category category,RedirectAttributes redirectAttributes, HttpSession session,String categoryId) {
		  
		if(StringUtils.isNotEmpty(categoryId)){
			 category.setId(categoryId);
			 article.setCategory(category);
			 article.setDelFlag(ArticleConstant.POSTED_1);
			 page= articleService.findPage(new Page<Article>(request, response), article);
			 
			  
		}
		model.addAttribute("page",page);
		return "center/news/list";
	}
	/**
	 * 获取新闻
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "vise")
	public String getVise(Model model,HttpServletResponse response,Site site,Page<Article> page,Article article, HttpServletRequest request,Category category,RedirectAttributes redirectAttributes, HttpSession session,String categoryId) {
		  
		if(StringUtils.isNotEmpty(categoryId)){
			 category.setId(categoryId);
			 article.setCategory(category);
			 article.setDelFlag(ArticleConstant.POSTED_1);
			 page= articleService.findPage(new Page<Article>(request, response), article);
			
			  
		} 
		 model.addAttribute("page",page);
		 model.addAttribute("categoryId",categoryId);
		return "center/supervise/superviseList";
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "title")
	public void getTitle(Model model,HttpServletResponse response,Category category, HttpServletRequest request,RedirectAttributes redirectAttributes, HttpSession session,String inMenu) {
		  
		if(StringUtils.isNotEmpty(inMenu)){
			 category.setInMenu(inMenu);
		 
			 List<Category> categoryList=categoryService.findList(category);
			 
			  ObjectMapper mapper = new ObjectMapper();
				try {
					String str = mapper.writeValueAsString(categoryList);
					PrintWriter writer = response.getWriter();
					writer.println(str);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}
	 
	
	/**
	 * 获取首页焦点
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "img")
	public void getFocusMap(Model model,HttpServletResponse response,Article article, HttpServletRequest request,RedirectAttributes redirectAttributes, HttpSession session,String inMenu) {
		  
		 
		 
			 List<Article> articleList=articleService.findList(article);
			 
			  ObjectMapper mapper = new ObjectMapper();
				try {
					String str = mapper.writeValueAsString(articleList);
					PrintWriter writer = response.getWriter();
					writer.println(str);
				} catch (Exception e) {
					e.printStackTrace();
				}
		 
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
					String path = "/cms";
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
	
	
	   @RequestMapping({"delete"})
	    public String delete(Article article, String categoryId,  RedirectAttributes redirectAttributes)
	      {
	       articleService.delete(article);
	     return "redirect:"+Global.getAdminPath()+"/cms/article/?repage&category.id="+(categoryId != null ? categoryId : "");
	     }
}
