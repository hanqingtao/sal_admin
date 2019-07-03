package com.ambition.agile.common.aliyun.dysms;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ambition.agile.common.util.PropertiesFactory;
import com.ambition.agile.common.web.BaseController;

/**
 * 
 * ClassName DysmsController
 * @Description:阿里云短信服务Controller
 * @author xrl
 * @Date 2018年10月26日
 */
@Controller
@RequestMapping(value = "${frontPath}/dysms")
public class DysmsController extends BaseController{

	/**
	 * 
	 * @Title getVerificationCode
	 * @Description:获取验证码
	 * @author xrl
	 * @Date 2018年10月26日
	 * @param cellPhone   手机号
	 * @param model   模板   1:登录   2:注册   3:修改密码...
	 * @return
	 */
	@RequestMapping(value = "getVerificationCode", produces = {"application/json; charset=UTF-8"},method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getVerificationCode(@RequestParam(value = "cellPhone", required = false)String cellPhone,
			@RequestParam(value = "model", required = false)Integer model) {
		Map<String, Object> map=new HashMap<String, Object>();
		if(model==1){
			map=DysmsUtil.getVerificationCode(cellPhone,PropertiesFactory.getProperty("dysmsConfig.properties", "dysms.login"));
		}else if(model==2){
			map=DysmsUtil.getVerificationCode(cellPhone,PropertiesFactory.getProperty("dysmsConfig.properties", "dysms.registration"));
		}else if(model==3){
			map=DysmsUtil.getVerificationCode(cellPhone,PropertiesFactory.getProperty("dysmsConfig.properties", "dysms.change.password"));
		}
		return map;
	}
	
	/**
	 * 
	 * @Title getRegisterCode
	 * @Description:获取注册验证码
	 * @author xrl
	 * @Date 2018年10月26日
	 * @param cellPhone
	 * @return
	 */
	@RequestMapping(value = "getRegisterCode", produces = {"application/json; charset=UTF-8"},method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getRegisterCode(@RequestParam(value = "cellPhone", required = false)String cellPhone) {
		return DysmsUtil.getRegisterCode(cellPhone);
	}
	
	/**
	 * 
	 * @Title getLoginCode
	 * @Description:获取登录验证码
	 * @author xrl
	 * @Date 2018年10月26日
	 * @param cellPhone
	 * @return
	 */
	@RequestMapping(value = "getLoginCode", produces = {"application/json; charset=UTF-8"},method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getLoginCode(@RequestParam(value = "cellPhone", required = false)String cellPhone) {
		return DysmsUtil.getLoginCode(cellPhone);
	}
	
	/**
	 * 
	 * @Title getChangePasswordCode
	 * @Description:获取修改密码验证码
	 * @author xrl
	 * @Date 2018年10月26日
	 * @param cellPhone
	 * @return
	 */
	@RequestMapping(value = "getChangePasswordCode", produces = {"application/json; charset=UTF-8"},method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getChangePasswordCode(@RequestParam(value = "cellPhone", required = false)String cellPhone) {
		return DysmsUtil.getChangePasswordCode(cellPhone);
	}
	
}
