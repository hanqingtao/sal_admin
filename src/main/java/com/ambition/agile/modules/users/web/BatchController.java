/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.users.web;

import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.adobe.xmp.impl.Utils;
import com.ambition.agile.common.config.Global;
import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.util.ComUtil;
import com.ambition.agile.common.util.DateTimeUtil;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.common.utils.StringUtils;
import com.ambition.agile.modules.sys.entity.Dict;
import com.ambition.agile.modules.sys.service.DictService;
import com.ambition.agile.modules.sys.utils.UserUtils;
import com.ambition.agile.modules.users.entity.Batch;
import com.ambition.agile.modules.users.entity.Cdkey;
import com.ambition.agile.modules.users.service.BatchService;
import com.ambition.agile.modules.users.service.CdkeyService;

import ch.qos.logback.classic.pattern.Util;

/**
 * 批次Controller
 * @author harry
 * @version 2019-12-07
 */
@Controller
@RequestMapping(value = "${adminPath}/users/batch")
public class BatchController extends BaseController {

	@Autowired
	private BatchService batchService;
	
	@Autowired
	private  CdkeyService cdkeyService;
	
	@Autowired
	private DictService dictService;
	
	@ModelAttribute
	public Batch get(@RequestParam(required=false) String id) {
		Batch entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = batchService.get(id);
		}
		if (entity == null){
			entity = new Batch();
		}
		return entity;
	}
	
	@RequiresPermissions("users:batch:view")
	@RequestMapping(value = {"list", ""})
	public String list(Batch batch, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Batch> page = batchService.findPage(new Page<Batch>(request, response), batch); 
		model.addAttribute("page", page);
		return "modules/users/batchList";
	}

	//导出
	@RequiresPermissions("users:batch:view")
	@RequestMapping(value = {"batchExport", "batchExport"})
	public void batchExport(String id,
			HttpServletRequest request, HttpServletResponse response, Model model) {
				try {
//					User user1= UserUtils.getUser();
//					SysUser sysUser = sysUserService.get(user1.getId());
					Batch batch = batchService.get(id);
					Cdkey cdkey = new Cdkey();
					cdkey.setBatchId(Integer.parseInt(id));
					List<Cdkey>	cdkeyList = cdkeyService.findListByBatch(cdkey);
					Map<String, Object> map=new HashMap<String, Object>();
					 
						//List<Project> findListByexpId = projectService.findListByexpId(name, utunitId, area, provinceId, projectYear, direction, keyTask);
						// 创建HSSFWorkbook对象(excel的文档对象)
						HSSFWorkbook wb = new HSSFWorkbook();
						// 建立新的sheet对象（excel的表单）
						HSSFSheet sheet = wb.createSheet(batch.getName()+"批量开卡信息");
						HSSFRow row1 = sheet.createRow(0);
						HSSFCell cell = row1.createCell(0);
						Date date=new Date();
						if(cdkeyList != null && cdkeyList.size()>0){
							String time=DateTimeUtil.dateToString(date,"yyyy-MM-dd HH:mm");
							String beginTime = DateTimeUtil.dateToString(batch.getBeginTime(),"yyyy-MM-dd HH:mm");
							String endTime =  DateTimeUtil.dateToString(batch.getEndTime(),"yyyy-MM-dd HH:mm");
							// 设置单元格内容
							cell.setCellValue(batch.getName()+"-导出时间:"+time+"卡,有效期:"+beginTime+"--"+endTime);
						}else{
							// 设置单元格内容
							cell.setCellValue("项目信息");
						}
						//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
						
						//设置行高
						row1.setHeight((short)1000);
						//设置列宽
						sheet.setColumnWidth(0,9000);
						sheet.setColumnWidth(1,9000);
						sheet.setColumnWidth(2,9000);
						sheet.setColumnWidth(3,5000);
						sheet.setColumnWidth(4,5000);

						// 设置字体   
						HSSFFont font = wb.createFont();   
						//font.setColor(HSSFFont.COLOR_RED);   //颜色  
						font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);   //加粗   
						font.setFontHeightInPoints((short)20);     //字体大小
						// 设置样式   
						HSSFCellStyle cellStyle = wb.createCellStyle();
						cellStyle.setFont(font);
						cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);   //左右居中
						cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中 
						cell.setCellStyle(cellStyle);
						// 在sheet里创建第二行
						HSSFRow row2 = sheet.createRow(1);
						// 创建单元格并设置单元格内容
						
						row2.createCell(0).setCellValue("激活码");
						row2.createCell(1).setCellValue("状态");
						row2.createCell(2).setCellValue("创建时间");
						row2.createCell(3).setCellValue("激活时间");
//						row2.createCell(4).setCellValue("开始时间");
//						row2.createCell(5).setCellValue("结束时间");
//						String[] lists = voteList.split(",");
						
						if(cdkeyList!=null&&cdkeyList.size()>0){
							int j=2;
							for (Cdkey cdkeyTemp: cdkeyList) {
								HSSFRow rowTemp = sheet.createRow(j);
								Dict dict =new Dict();
								dict.setValue(cdkeyTemp.getStatus());
								dict.setType("cdkey_status");
								String status = "";
								List<Dict> cdkeyStatusDict = dictService.findList(dict);
								for(Dict dictTemp:cdkeyStatusDict){
									if(dictTemp.getLabel() != null){
										status += dictTemp.getLabel()+",";
									}
								}
								
								if(status.lastIndexOf(",")==status.length()-1) {
									status = status.substring(0,status.length()-1);
								}
							
								rowTemp.createCell(0).setCellValue(cdkeyTemp.getCode());
								if(status != null){
									rowTemp.createCell(1).setCellValue(status);
								}
								if( cdkeyTemp.getCreateDate() != null ){
									String createDate = DateTimeUtil.dateToString(batch.getCreateDate(),"yyyy-MM-dd HH:mm");
									rowTemp.createCell(2).setCellValue(createDate);
								}
								if(null != cdkeyTemp.getActiveDate()){
									String activeDate = DateTimeUtil.dateToString(batch.getCreateDate(),"yyyy-MM-dd HH:mm");
									rowTemp.createCell(3).setCellValue(activeDate);
								}
								
								j++;
							}
						}
//						String[] split = voteList.split(",");
//						int length = split.length;
						int reginCount =4;//length-1;
						sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, reginCount));
						OutputStream out = response.getOutputStream();
						response.setHeader("Content-disposition","attachment;filename="+date.getTime()+".xls");
						response.setContentType("application/msexcel;charset=UTF-8");
						wb.write(out);
						out.flush();
						out.close();
						System.out.println("export cdkey List success!!!");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	
	
	@RequiresPermissions("users:batch:view")
	@RequestMapping(value = "form")
	public String form(Batch batch, Model model) {
		model.addAttribute("batch", batch);
		return "modules/users/batchForm";
	}

	@RequiresPermissions("users:batch:edit")
	@RequestMapping(value = "save")
	public String save(Batch batch, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, batch)){
			return form(batch, model);
		}
		batchService.save(batch);
		addMessage(redirectAttributes, "保存批次成功");
		return "redirect:"+Global.getAdminPath()+"/users/batch/?repage";
	}
	
	@RequiresPermissions("users:batch:edit")
	@RequestMapping(value = "delete")
	public String delete(Batch batch, RedirectAttributes redirectAttributes) {
		batchService.delete(batch);
		addMessage(redirectAttributes, "删除批次成功");
		return "redirect:"+Global.getAdminPath()+"/users/batch/?repage";
	}

}