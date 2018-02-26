/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.center.pro;

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
import com.ambition.agile.modules.pro.entity.CreditRecord;
import com.ambition.agile.modules.pro.service.CreditRecordService;

/**
 * 信用记录Controller
 * @author harry
 * @version 2017-10-18
 */
@Controller
@RequestMapping(value = "${frontPath}/pro/creditRecord")
public class CreditRecordWebController extends BaseController {

	@Autowired
	private CreditRecordService creditRecordService;
	
	
	
	@RequestMapping(value = "list")
	public String list(CreditRecord creditRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CreditRecord> page = creditRecordService.findPage(new Page<CreditRecord>(request, response), creditRecord); 
		model.addAttribute("page", page);
		return "center/credit/credit";
	}

	

}