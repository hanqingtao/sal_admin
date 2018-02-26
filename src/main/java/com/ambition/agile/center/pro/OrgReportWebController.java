/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.center.pro;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ambition.agile.common.config.Global;
import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.common.utils.StringUtils;
import com.ambition.agile.modules.pro.entity.OrgReport;
import com.ambition.agile.modules.pro.service.OrgReportService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 代理机构举报信息Controller
 * @author harry
 * @version 2017-08-03
 */
@Controller
@RequestMapping(value = "${frontPath}/pro/orgReport")
public class OrgReportWebController extends BaseController {

	@Autowired
	private OrgReportService orgReportService;
	
	 
	 
	@RequestMapping(value = "save")
	public void save(OrgReport orgReport, Model model, RedirectAttributes redirectAttributes,HttpServletResponse response) {
		 
		orgReportService.save(orgReport);
		
		try{
				 			
				 ObjectMapper mapper = new ObjectMapper();  
					String str = mapper.writeValueAsString("举报成功！"); 
					PrintWriter writer = response.getWriter();
					writer.println(str);
					}catch(Exception e){
						e.printStackTrace();
					}

		 
	}
	
	 

}