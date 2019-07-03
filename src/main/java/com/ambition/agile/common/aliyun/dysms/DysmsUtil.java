package com.ambition.agile.common.aliyun.dysms;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.FormatType;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.ambition.agile.common.util.PropertiesFactory;
import com.ibm.icu.text.SimpleDateFormat;

public class DysmsUtil {
	
	private static String product; // 云通信短信API
	private static String domain; // 产品域名
	private static String territory; // 地域
	private static String signModel="工业招标投标公共服务平台";
	private static String accessKeyId; // 连接keyId
	private static String accessKeySecret; // 连接秘钥
	
	static{
		product = PropertiesFactory.getProperty("dysmsConfig.properties", "dysms.product");
		domain = PropertiesFactory.getProperty("dysmsConfig.properties", "dysms.domain");
		territory = PropertiesFactory.getProperty("dysmsConfig.properties", "dysms.territory");
		accessKeyId = PropertiesFactory.getProperty("dysmsConfig.properties", "dysms.accessKeyId");
		accessKeySecret = PropertiesFactory.getProperty("dysmsConfig.properties", "dysms.accessKeySecret");
		/*System.out.println("——————————domain:"+domain);
		System.out.println("———————————territory:"+territory);*/
	}
	
	/*private static String product="Dysmsapi"; // 云通信短信API
	private static String domain="dysmsapi.aliyuncs.com"; // 产品域名
	private static String signModel="工业招标投标公共服务平台";//签名名称
	private static String accessKeyId="LTAIRGumvLYjEhaw"; // 连接keyId
	private static String accessKeySecret="64yPkvCfjcOkMsjuGohBGJkrn32amF"; // 连接秘钥
*/	
	/**
	 * 
	 * @Title getVerificationCode
	 * @Description:
	 * @author xrl
	 * @Date 2018年10月26日
	 * @param cellPhone  手机号
	 * @param model       模板
	 * @return
	 */
	public static Map<String, Object> getVerificationCode(String cellPhone,String model){
		Map<String, Object> map=new HashMap<String,Object>();
		try {
			
			String code="";
			//生成随机数
			Random random=new Random();
			for(int i=0;i<=5;i++){
				int ran=random.nextInt(10);
	 			code+=ran;
			}
			
	        //可自助调整超时时间
	        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
	        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

	        //初始化acsClient,暂不支持region化
	        IClientProfile profile = DefaultProfile.getProfile(territory, accessKeyId, accessKeySecret);
	        DefaultProfile.addEndpoint(territory, territory, product, domain);
	        IAcsClient acsClient = new DefaultAcsClient(profile);

	        //组装请求对象-具体描述见控制台-文档部分内容
	        SendSmsRequest request = new SendSmsRequest();
	        //修改数据提交方式
	        request.setMethod(MethodType.POST);
	        //修改数据交互格式
	        request.setAcceptFormat(FormatType.JSON);
	        //必填:待发送手机号
	        request.setPhoneNumbers(cellPhone);
	        //必填:短信签名-可在短信控制台中找到
	        request.setSignName(signModel);
	        //必填:短信模板-可在短信控制台中找到
	        request.setTemplateCode(model);
	        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
	        request.setTemplateParam("{\"code\":\""+code+"\"}");

	        //hint 此处可能会抛出异常，注意catch
	        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
	        if(sendSmsResponse.getCode()!="OK"&&!"OK".equals(sendSmsResponse.getCode())){
	        	code="";
	        }
	        map.put("code",code);
	        map.put("message",verificationCode(sendSmsResponse.getCode()));//错误信息
	        
	        System.out.println("————————VerificationCode————————");
	        System.out.println("Code:"+sendSmsResponse.getCode());
	        System.out.println("Message:"+sendSmsResponse.getMessage());
	        
		} catch (Exception e) {
			map.put("code", "");
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 
	 * @Title getRegisterCode
	 * @Description:获取注册验证码
	 * @author xrl
	 * @Date 2018年10月26日
	 * @param cellPhone  手机号  
	 * @return
	 */
	public static Map<String, Object> getRegisterCode(String cellPhone){
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			
			String code="";
			//生成随机数
			Random random=new Random();
			for(int i=0;i<=5;i++){
				int ran=random.nextInt(10);
	 			code+=ran;
			}
			
	        //可自助调整超时时间
	        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
	        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

	        //初始化acsClient,暂不支持region化
	        IClientProfile profile = DefaultProfile.getProfile(territory, accessKeyId, accessKeySecret);
	        DefaultProfile.addEndpoint(territory,territory, product, domain);
	        IAcsClient acsClient = new DefaultAcsClient(profile);

	        //组装请求对象-具体描述见控制台-文档部分内容
	        SendSmsRequest request = new SendSmsRequest();
	        //修改数据提交方式
	        request.setMethod(MethodType.POST);
	        //修改数据交互格式
	        request.setAcceptFormat(FormatType.JSON);
	        //必填:待发送手机号
	        request.setPhoneNumbers(cellPhone);
	        //必填:短信签名-可在短信控制台中找到
	        request.setSignName(signModel);
	        //必填:短信模板-可在短信控制台中找到
	        String model=PropertiesFactory.getProperty("dysmsConfig.properties", "dysms.registration");
	        request.setTemplateCode(model);
	        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
	        request.setTemplateParam("{\"code\":\""+code+"\"}");

	        //hint 此处可能会抛出异常，注意catch
	        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
	        if(sendSmsResponse.getCode()!="OK"&&!"OK".equals(sendSmsResponse.getCode())){
	        	code="";
	        }
	        map.put("code",code);
	        map.put("message",verificationCode(sendSmsResponse.getCode()));//错误信息
	        
	        System.out.println("————————Register————————");
	        System.out.println("Code:"+sendSmsResponse.getCode());
	        System.out.println("Message:"+sendSmsResponse.getMessage());
	        
		} catch (Exception e) {
			map.put("code", "");
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 
	 * @Title getLoginCode
	 * @Description:获取注册验证码
	 * @author xrl
	 * @Date 2018年10月26日
	 * @param cellPhone  手机号
	 * @return
	 */
	public static Map<String, Object> getLoginCode(String cellPhone){
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			
			String code="";
			//生成随机数
			Random random=new Random();
			for(int i=0;i<=5;i++){
				int ran=random.nextInt(10);
	 			code+=ran;
			}
			
	        //可自助调整超时时间
	        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
	        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

	        //初始化acsClient,暂不支持region化
	        IClientProfile profile = DefaultProfile.getProfile(territory, accessKeyId, accessKeySecret);
	        DefaultProfile.addEndpoint(territory,territory, product, domain);
	        IAcsClient acsClient = new DefaultAcsClient(profile);

	        //组装请求对象-具体描述见控制台-文档部分内容
	        SendSmsRequest request = new SendSmsRequest();
	        //修改数据提交方式
	        request.setMethod(MethodType.POST);
	        //修改数据交互格式
	        request.setAcceptFormat(FormatType.JSON);
	        //必填:待发送手机号
	        request.setPhoneNumbers(cellPhone);
	        //必填:短信签名-可在短信控制台中找到
	        request.setSignName(signModel);
	        //必填:短信模板-可在短信控制台中找到
	        String model=PropertiesFactory.getProperty("dysmsConfig.properties", "dysms.login");
	        request.setTemplateCode(model);
	        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
	        request.setTemplateParam("{\"code\":\""+code+"\"}");

	        //hint 此处可能会抛出异常，注意catch
	        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
	        if(sendSmsResponse.getCode()!="OK"&&!"OK".equals(sendSmsResponse.getCode())){
	        	code="";
	        }
	        map.put("code",code);
	        map.put("message",verificationCode(sendSmsResponse.getCode()));//错误信息
	        
	        System.out.println("————————Login————————");
	        System.out.println("Code:"+sendSmsResponse.getCode());
	        System.out.println("Message:"+sendSmsResponse.getMessage());
	        
		} catch (Exception e) {
			map.put("code", "");
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 
	 * @Title getChangePasswordCode
	 * @Description:获取修改密码验证码
	 * @author xrl
	 * @Date 2018年10月26日
	 * @param cellPhone  手机号
	 * @return
	 */
	public static Map<String, Object> getChangePasswordCode(String cellPhone){
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			
			String code="";
			//生成随机数
			Random random=new Random();
			for(int i=0;i<=5;i++){
				int ran=random.nextInt(10);
	 			code+=ran;
			}
			
	        //可自助调整超时时间
	        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
	        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

	        //初始化acsClient,暂不支持region化
	        IClientProfile profile = DefaultProfile.getProfile(territory, accessKeyId, accessKeySecret);
	        DefaultProfile.addEndpoint(territory,territory, product, domain);
	        IAcsClient acsClient = new DefaultAcsClient(profile);

	        //组装请求对象-具体描述见控制台-文档部分内容
	        SendSmsRequest request = new SendSmsRequest();
	        //修改数据提交方式
	        request.setMethod(MethodType.POST);
	        //修改数据交互格式
	        request.setAcceptFormat(FormatType.JSON);
	        //必填:待发送手机号
	        request.setPhoneNumbers(cellPhone);
	        //必填:短信签名-可在短信控制台中找到
	        request.setSignName(signModel);
	        //必填:短信模板-可在短信控制台中找到
	        String model=PropertiesFactory.getProperty("dysmsConfig.properties", "dysms.change.password");
	        request.setTemplateCode(model);
	        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
	        request.setTemplateParam("{\"code\":\""+code+"\"}");

	        //hint 此处可能会抛出异常，注意catch
	        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
	        if(sendSmsResponse.getCode()!="OK"&&!"OK".equals(sendSmsResponse.getCode())){
	        	code="";
	        }
	        map.put("code",code);
	        map.put("message",verificationCode(sendSmsResponse.getCode()));//错误信息
	        
	        
	        System.out.println("————————ChangePassword————————");
	        System.out.println("Code:"+sendSmsResponse.getCode());
	        System.out.println("Message:"+sendSmsResponse.getMessage());
	        
		} catch (Exception e) {
			map.put("code", "");
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 
	 * @Title verificationCode
	 * @Description:验证错误信息
	 * @author xrl
	 * @Date 2018年10月27日
	 * @param Code 发送短信后接口返回的Code
	 * @return
	 */
	public static String verificationCode(String Code){
		String Message="";
		if(Code=="OK"||"OK".equals(Code)){
			Message="OK";
		}else if(Code=="isv.MOBILE_NUMBER_ILLEGAL"||"isv.MOBILE_NUMBER_ILLEGAL".equals(Code)){
			Message="非法手机号，请传入11位国内号段的手机号码。";
		}else if(Code=="isv.MOBILE_COUNT_OVER_LIMIT"||"isv.MOBILE_COUNT_OVER_LIMIT".equals(Code)){
			Message="手机号码数量超过限制。";
		}else if(Code=="isv.BUSINESS_LIMIT_CONTROL"||"isv.BUSINESS_LIMIT_CONTROL".equals(Code)){
			Message="同一个手机号码发送短信验证码，支持1条/分钟，5条/小时 ，累计10条/天。";
		}
		return Message;
	}
	
	
	/**
	 * 
	 * @Title sendSms
	 * @Description:测试Demo
	 * @author xrl
	 * @Date 2018年10月26日
	 * @return
	 * @throws ClientException
	 */
	public static SendSmsResponse sendSms() throws ClientException {

		String code="";
		Random random=new Random();
		for(int i=0;i<=5;i++){
			int ran=random.nextInt(10);
 			code+=ran;
		}
		
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile(territory, accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint(territory,territory, product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //修改数据提交方式
        request.setMethod(MethodType.POST);
        //修改数据交互格式
        request.setAcceptFormat(FormatType.JSON);
        //必填:待发送手机号
        request.setPhoneNumbers("18513686571");
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(signModel);
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_148985105");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{\"code\":\""+code+"\"}");

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        return sendSmsResponse;
    }
	
	/**
	 * 
	 * @Title querySendDetails
	 * @Description:测试查询发送记录Demo
	 * @author xrl
	 * @Date 2018年10月26日
	 * @param bizId
	 * @return
	 * @throws ClientException
	 */
	public static QuerySendDetailsResponse querySendDetails(String bizId) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile(territory, accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint(territory,territory, product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象
        QuerySendDetailsRequest request = new QuerySendDetailsRequest();
        //必填-号码
        request.setPhoneNumber("15000000000");
        //可选-流水号
        request.setBizId(bizId);
        //必填-发送日期 支持30天内记录查询，格式yyyyMMdd
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
        request.setSendDate(ft.format(new Date()));
        //必填-页大小
        request.setPageSize(10L);
        //必填-当前页码从1开始计数
        request.setCurrentPage(1L);

        //hint 此处可能会抛出异常，注意catch
        QuerySendDetailsResponse querySendDetailsResponse = acsClient.getAcsResponse(request);

        return querySendDetailsResponse;
    }
	
	public static void main(String[] args) throws ClientException, InterruptedException {

        /*//发短信
        SendSmsResponse response = sendSms();
        System.out.println("短信接口返回的数据----------------");
        System.out.println("Code=" + response.getCode());
        System.out.println("Message=" + response.getMessage());
        System.out.println("RequestId=" + response.getRequestId());
        System.out.println("BizId=" + response.getBizId());
		
		//查明细
        if(response.getCode() != null && response.getCode().equals("OK")) {
            QuerySendDetailsResponse querySendDetailsResponse = querySendDetails(response.getBizId());
            System.out.println("短信明细查询接口返回数据----------------");
            System.out.println("Code=" + querySendDetailsResponse.getCode());
            System.out.println("Message=" + querySendDetailsResponse.getMessage());
            int i = 0;
            for(QuerySendDetailsResponse.SmsSendDetailDTO smsSendDetailDTO : querySendDetailsResponse.getSmsSendDetailDTOs())
            {
                System.out.println("SmsSendDetailDTO["+i+"]:");
                System.out.println("Content=" + smsSendDetailDTO.getContent());
                System.out.println("ErrCode=" + smsSendDetailDTO.getErrCode());
                System.out.println("OutId=" + smsSendDetailDTO.getOutId());
                System.out.println("PhoneNum=" + smsSendDetailDTO.getPhoneNum());
                System.out.println("ReceiveDate=" + smsSendDetailDTO.getReceiveDate());
                System.out.println("SendDate=" + smsSendDetailDTO.getSendDate());
                System.out.println("SendStatus=" + smsSendDetailDTO.getSendStatus());
                System.out.println("Template=" + smsSendDetailDTO.getTemplateCode());
            }
            System.out.println("TotalCount=" + querySendDetailsResponse.getTotalCount());
            System.out.println("RequestId=" + querySendDetailsResponse.getRequestId());
        }*/
		
		System.out.println(getRegisterCode("18513686571"));
		/*System.out.println(getLoginCode("18513686571"));
		System.out.println(getChangePasswordCode("18513686571"));*/
     }
	
}
