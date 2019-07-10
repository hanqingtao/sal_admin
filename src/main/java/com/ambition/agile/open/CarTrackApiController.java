/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.open;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ambition.agile.common.ApiResponse;
import com.ambition.agile.common.BaseConfigHolder;
import com.ambition.agile.common.config.Global;
import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.util.HttpClientUtil;
import com.ambition.agile.common.util.MD5Encrypt;
import com.ambition.agile.common.utils.StringUtils;
import com.ambition.agile.common.web.BaseController;
/**
 * 车辆轨迹信息Controller
 * @author harry
 * @version 2018-05-04
 */
@Controller
@RequestMapping(value = "${frontPath}/mobile")
public class CarTrackApiController extends BaseController {
	
	/**
	 * 用户获取 GPS 设备的轨迹 记录接口
	 * @param plate_number  （车辆 牌号 唯一标识）
	 * @param longitude （经度）
	 * @param latitude （纬度）
	 * @param timestamp （请求时间, 1970 年到此时的秒数）
	 * @param sign （签名, 所有参数名升序排列后拼接成字符串后跟密钥一起MD5）
	 * @return
	 * 
	 * http://localhost:8080/ctc/mobile/gps/addpoint?plateNumber=nm888&longitude=40.1111&latitude=116.34343&timestamp=23423423424&sign=3434
	 * 
	 * http://yingyan.baidu.com/api/v3/track/addpoint?ak=lBBPrgP1Qaa0V3zQBc6gYuKi8TXcpklY&service_id=200466&entity_name=nm888&latitude=40.111&longitude=116.3444&loc_time=1488785466&coord_type_input=wgs84
	 * 
		latitude	纬度	double(-90.0 , +90.0)	是	
		longitude	经度	double(-180.0 , +180.0)	是
	 * 
	 * 
	 */
	@RequestMapping(value="/gps/addpoint")
	@ResponseBody
	public ApiResponse<?> progress(String plateNumber, String longitude, String latitude, 
			String timestamp, String sign){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if( StringUtils.isEmpty(plateNumber) || StringUtils.isEmpty(longitude) || 
				StringUtils.isEmpty(latitude) || StringUtils.isEmpty(timestamp) || 
				StringUtils.isEmpty(sign) ){
			logger.error("params error...");
			return ApiResponse.fail(500, "无法 获取 gps 数据！原因：参数有误。");
		}
		// 间隔时间判断（前后 间隔 1分钟内允许获取课程学习记录）
//		Date date = new Date();
//		long time = date.getTime()/1000 - Long.parseLong(timestamp);
//		if(time > 60 || time < -60){
//			logger.error("timestamp error...");
//			return ApiResponse.fail(500, "无法获取课程学习记录！原因：参数有误。");
//		}
		// 判断分校标识是否正确
//		Map<String, Object> orgMap = orgWebApi.getOrgByOrgNameSn(corpid);
//		if(orgMap == null){
//			logger.error("org is null...");
//			return ApiResponse.fail(500, "无法获取课程学习记录！原因：参数有误。");
//		}
//		
		// 判断签名是否正确
//		String secretKey = BaseConfigHolder.getGpsSecretKey();
//		String params = "plateNumber=" + plateNumber + "longitude=" + longitude + "latitude=" + latitude + 
//				"timestamp=" + timestamp + secretKey;
//		if(!MD5Encrypt.encrypt(params).equals(sign)){
//			logger.error("sign error, params: " + params + "sign: " + sign);
//			return ApiResponse.fail(500, "获取 GPS 上传 记录！原因：签名,解密有误。");
//		}
		
		//先根据车牌号查询
		
		//上报 百度 api
		uploadBaiduPoint();
		return ApiResponse.success("gps信息保存成功!");
	}
	
	public String uploadBaiduPoint( ){
		
		String url = "http://yingyan.baidu.com/api/v3/track/addpoint";
		Map<String,String>  maps= new HashMap<String,String>();
		maps.put("ak", "lBBPrgP1Qaa0V3zQBc6gYuKi8TXcpklY");
		maps.put("service_id", "200466");
		maps.put("coord_type_input", "wgs84");
		maps.put("entity_name", "nm888");
		maps.put("latitude","");
		maps.put("longitude","");
		long unixTime = System.currentTimeMillis()/1000;
		maps.put("loc_time", unixTime+"");
		HttpClientUtil httpClientUtil = HttpClientUtil.getInstance();
		String post  =  httpClientUtil.sendHttpPost(url, maps);
		return post;
	}
	
	@RequestMapping(value="/gps/getCarTrack")
	@ResponseBody
	public ApiResponse<?> getCarTrack(){
		
//		Map<String, Object> resultMap = new HashMap<String, Object>();
//		if( StringUtils.isEmpty(plateNumber) || StringUtils.isEmpty(longitude) || 
//				StringUtils.isEmpty(latitude) || StringUtils.isEmpty(timestamp) || 
//				StringUtils.isEmpty(sign) ){
//			logger.error("params error...");
//			return ApiResponse.fail(500, "无法 获取 gps 数据！原因：参数有误。");
//		}
		
		String httpUrl  = "http://yingyan.baidu.com/api/v3/track/gettrack";
		Map<String,String>  maps= new HashMap<String,String>();
		maps.put("ak", "lBBPrgP1Qaa0V3zQBc6gYuKi8TXcpklY");
		maps.put("service_id", "200466");
		maps.put("entity_name", "nm888");
		long unixTimebegin = System.currentTimeMillis()/1000-86400;
		long unixTimeend = System.currentTimeMillis()/1000;
		maps.put("start_time", unixTimebegin+"");
		maps.put("end_time", unixTimeend+"");
		HttpClientUtil httpClientUtil = HttpClientUtil.getInstance();

  		String get = httpClientUtil.sendHttpGet(httpUrl, maps);
		System.out.println("#######"+get);
		/*{"status":0,"message":"成功","total":9,"size":9,"distance":608.73456347777,"toll_distance":0,
		"start_point":{"longitude":116.14732549693,"latitude":43.933974616369,"loc_time":1527126681},
		"end_point":{"longitude":116.15229152993,"latitude":43.938035896891,"loc_time":1527134725},
		"points":[{"loc_time":1527126681,"latitude":43.933974616369,"longitude":116.14732549693,"create_time":"2018-05-24 09:51:57","speed":0,"direction":0},
		          {"loc_time":1527126716,"latitude":43.934988009274,"longitude":116.1483186774,"create_time":"2018-05-24 09:52:32","speed":14.179,"direction":35},
		          {"loc_time":1527126733,"latitude":43.936013659391,"longitude":116.15030396373,"create_time":"2018-05-24 09:52:49","speed":41.41,"direction":54},
		          {"loc_time":1527126744,"latitude":43.93702498337,"longitude":116.15129776908,"create_time":"2018-05-24 09:52:59","speed":45.0624,"direction":35},
		          {"loc_time":1527126753,"latitude":43.938035896891,"longitude":116.15229152993,"create_time":"2018-05-24 09:53:09","speed":55.0602,"direction":35},
		          {"loc_time":1527131173,"latitude":43.938035896891,"longitude":116.15229152993,"create_time":"2018-05-24 11:06:14"},
		          {"loc_time":1527131185,"latitude":43.938035896891,"longitude":116.15229152993,"create_time":"2018-05-24 11:06:26","speed":0,"direction":0},
		          {"loc_time":1527134688,"latitude":43.938035896891,"longitude":116.15229152993,"create_time":"2018-05-24 12:04:49"},
		          {"loc_time":1527134725,"latitude":43.938035896891,"longitude":116.15229152993,"create_time":"2018-05-24 12:05:26","speed":0,"direction":0}]}
	*/
		
		return ApiResponse.success(get);
		
	}
	
	
//	@ModelAttribute
//	public CarTrack get(@RequestParam(required=false) String id) {
//		CarTrack entity = null;
//		if (StringUtils.isNotBlank(id)){
//			entity = carTrackService.get(id);
//		}
//		if (entity == null){
//			entity = new CarTrack();
//		}
//		return entity;
//	}
	
}