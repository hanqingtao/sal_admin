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

/**
 * 网站首页 Controller
 * @author harry
 * @version 2017-5-29
 */
@Controller
@RequestMapping(value = "${frontPath}")
public class IndexController extends BaseController{
	
	
	@Autowired
	private ArticleService articleService;
	@Autowired
	private ArticleDataService articleDataService;

	/**
	 * 网站首页
	 */
	@RequestMapping
	public String index(Model model) {
	    
		return "index";
	}
}
