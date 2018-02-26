package com.ambition.agile.center.main;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.modules.sys.entity.Area;
import com.ambition.agile.modules.sys.entity.Dict;
import com.ambition.agile.modules.sys.service.AreaService;
import com.ambition.agile.modules.sys.utils.DictUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping(value = "${frontPath}/etc")
public class DictWebController extends BaseController{
	@Autowired
	private AreaService areaService;
	/**
	 * 上传资格证书页面获取等级内容
	 * @param request
	 * @param response
	 */
	
	@RequestMapping(value = "term")
	public void getGrade(String option, HttpServletRequest request, HttpServletResponse response) {
		if(StringUtils.isNotEmpty(option)){
			try{
				List<Dict> list = DictUtils.getDictList(option);
				 ObjectMapper mapper = new ObjectMapper();  
				String str = mapper.writeValueAsString(list); 
				PrintWriter writer = response.getWriter();
				writer.println(str);
				}catch(Exception e){
					e.printStackTrace();
				}	
		}
	
	}
	
	/**
	 * 获取所有区域
	 * @param request
	 * @param response
	 */
	
	@RequestMapping(value = "area")
	public void getArea(HttpServletRequest request, HttpServletResponse response) {
		
			try{
				List<Area> arealist =areaService.findAll();
				 ObjectMapper mapper = new ObjectMapper();  
				String str = mapper.writeValueAsString(arealist); 
				PrintWriter writer = response.getWriter();
				writer.println(str);
				}catch(Exception e){
					e.printStackTrace();
				}	
		
	
	}
	
 
	 
}
