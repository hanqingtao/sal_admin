package com.ambition.agile.common.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.ambition.agile.common.ApiResponse;
import com.ambition.agile.common.constant.Constants;
import com.ambition.agile.modules.sys.utils.DictUtils;

 
 

/**
 * apache poi 操作excel 方法
 * @author ckl
 *
 */
public class ExcelUtil{
	
	/**
	 * excle 读取用户返回User集合，兼容2003-2007-2010excel
	 * @param type :目标实体类型
	 * @param path ：excel 文件
	 * @return
	 */
	/**@SuppressWarnings("unused")
	public static  ApiResponse<List<User>> readExcelToUsers(InputStream inputStream){
		
		try {
			Workbook workbook = WorkbookFactory.create(inputStream);
			if(workbook == null){
				return ApiResponse.fail(500, "Excel workbook is null...");
			}
			User user = null;
			List<User> userList = new ArrayList<>();
			System.out.println("sheet个数:"+workbook.getNumberOfSheets());
			//循环excel sheet
			for(int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++){
				Sheet sheet = workbook.getSheetAt(numSheet);
				if(sheet == null){
					continue;
				}
				System.out.println("第"+(numSheet+1)+"个sheet的行数："+sheet.getLastRowNum());
				//循环row，从第三行开始，第一行是表头
				for(int rowNum = 2; rowNum <= sheet.getLastRowNum(); rowNum++){
					Row row = sheet.getRow(rowNum);
					if(row != null){
						user = new User();
						Cell cell_loginName = row.getCell(0);
						Cell cell_userName = row.getCell(1);
						Cell cell_email = row.getCell(2);
						Cell cell_deptName = row.getCell(3);
						Cell cell_telphone = row.getCell(4);
						
						if(cell_loginName != null){							
							cell_loginName.setCellType(Cell.CELL_TYPE_STRING);
							user.setLogin_name(cell_loginName.getStringCellValue());
						}
						
						if(cell_userName != null){							
							cell_userName.setCellType(Cell.CELL_TYPE_STRING);
							user.setUser_name(cell_userName.getStringCellValue());
						}
						if(cell_email != null){							
							cell_userName.setCellType(Cell.CELL_TYPE_STRING);
							user.setEmail(cell_email.getStringCellValue());
						}
						if(cell_deptName != null){
							cell_userName.setCellType(Cell.CELL_TYPE_STRING);
							user.setDept_name(cell_deptName.getStringCellValue());
						}
						if(cell_telphone != null){
							cell_telphone.setCellType(Cell.CELL_TYPE_STRING);
							user.setTelphone(cell_telphone.getStringCellValue());
						}
						if(user.getLogin_name() != null && user.getUser_name() != null){
							userList.add(user);
						}
					}
				}
				
			}
			return ApiResponse.success("Excel read success!", userList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ApiResponse.fail(500, "Excel异常，请检查");
	}**/
	
	
	/**@SuppressWarnings("unused")
	public static  ApiResponse<List<Map<String, Object>>> readExcelToOrgs(InputStream inputStream){
		
		try {
			Workbook workbook = WorkbookFactory.create(inputStream);
			if(workbook == null){
				return ApiResponse.fail(500, "Excel workbook is null...");
			}
			Map<String, Object> map = null;
			List<Map<String, Object>> orgList = new ArrayList<>();
			System.out.println("sheet个数:"+workbook.getNumberOfSheets());
			//循环excel sheet
			for(int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++){
				Sheet sheet = workbook.getSheetAt(numSheet);
				if(sheet == null){
					continue;
				}
				System.out.println("第"+(numSheet+1)+"个sheet的行数："+sheet.getLastRowNum());
				//循环row，从第三行开始，第一行是文件描述，第二行是标题
				for(int rowNum = 2; rowNum <= sheet.getLastRowNum(); rowNum++){
					Row row = sheet.getRow(rowNum);
					if(row != null){
						map = new HashMap<String, Object>();
						// 学校名称
						Cell cell_customerName = row.getCell(1);
						// 平台名称
						//Cell cell_name = row.getCell(2);
						// 平台域名（前缀）
						Cell cell_domain = row.getCell(2);
						// 数据库名称
						//Cell cell_dbName = row.getCell(4);
						//所属地省市
						Cell cell_area = row.getCell(3);
						//经纬度
						Cell cell_jwd = row.getCell(4);
						
						if(cell_customerName != null){							
							cell_customerName.setCellType(Cell.CELL_TYPE_STRING);
							map.put("customer_name", cell_customerName.getStringCellValue());
							map.put("name", cell_customerName.getStringCellValue());
						}
						/*if(cell_name != null){							
							cell_name.setCellType(Cell.CELL_TYPE_STRING);
							map.put("name", cell_name.getStringCellValue());
						}*/
					/**	if(cell_domain != null){
							cell_domain.setCellType(Cell.CELL_TYPE_STRING);
							map.put("domain", cell_domain.getStringCellValue());
						}
						if(cell_area != null){
							cell_area.setCellType(Cell.CELL_TYPE_STRING);
							map.put("areaName", cell_area.getStringCellValue());
						}
						if(cell_jwd != null){
							cell_jwd.setCellType(Cell.CELL_TYPE_STRING);
							map.put("jingweidu", cell_jwd.getStringCellValue());
						}
						/*if(cell_dbName != null){
							cell_dbName.setCellType(Cell.CELL_TYPE_STRING);
							map.put("db_name", cell_dbName.getStringCellValue());
						}*/
	             /**	orgList.add(map);
					}
				}
				
			}
			return ApiResponse.success("Excel read success!", orgList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ApiResponse.fail(500, "Excel异常，请检查");
	}
	**/
	
	/**
	 * 导入机构专职人员
	 * @param inputStream
	 * @return
	 */
	 @SuppressWarnings("unused")
	public static  ApiResponse<List<Map<String, Object>>> readExcelToStaffs(InputStream inputStream){
		 
		try {
			Workbook workbook = WorkbookFactory.create(inputStream);
			if(workbook == null){
				return ApiResponse.fail(500, "Excel workbook is null...");
			}
			Map<String, Object> map = null;
			List<Map<String, Object>> orgStaffList = new ArrayList<Map<String, Object>>();
			System.out.println("sheet个数:" + workbook.getNumberOfSheets());
			//循环excel sheet
			for(int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++){
				Sheet sheet = workbook.getSheetAt(numSheet);
				if(sheet == null){
					continue;
				}
				System.out.println("第"+(numSheet+1)+"个sheet的行数："+sheet.getLastRowNum());
				for(int rowNum = 3; rowNum <= sheet.getLastRowNum(); rowNum++){
					Row row = sheet.getRow(rowNum);
					if(row != null){
						map = new HashMap<String, Object>();
						//姓名
						Cell cell_name = row.getCell(0);
						//性别
						Cell cell_sex = row.getCell(1);
						// 身份证号
						Cell cell_card = row.getCell(2);
						// 开始工作时间
						Cell cell_workStart = row.getCell(3);
						// 毕业院校
						Cell cell_university = row.getCell(4);
						// 学历
						Cell cell_degree= row.getCell(5);
						// 职称
						Cell cell_proTitle= row.getCell(6);
						// 部门
						Cell cell_dept= row.getCell(7);
						// 劳动关系
						Cell cell_workType= row.getCell(8);
						//社会保险号
						Cell cell_ssnType= row.getCell(9);
						
							if(cell_name != null){
								cell_name.setCellType(Cell.CELL_TYPE_STRING);
								map.put("name", cell_name.getStringCellValue());
								}
							if(cell_sex != null){
							  cell_sex.setCellType(Cell.CELL_TYPE_STRING);
							  map.put("sex", cell_sex.getStringCellValue());	  
							}
							if(cell_card != null){
								cell_card.setCellType(Cell.CELL_TYPE_STRING);
								map.put("card", cell_card.getStringCellValue());
							}

							if(cell_workStart != null){
							cell_workStart.setCellType(Cell.CELL_TYPE_STRING);
							map.put("workStart", cell_workStart.getStringCellValue());
							}
							if(cell_university != null){
							cell_university.setCellType(Cell.CELL_TYPE_STRING);
							map.put("university", cell_university.getStringCellValue());
							}
							if(cell_degree != null){
							cell_degree.setCellType(Cell.CELL_TYPE_STRING);
								map.put("degree", cell_degree.getStringCellValue());
							}
							if(cell_proTitle != null){
							cell_proTitle.setCellType(Cell.CELL_TYPE_STRING);
							map.put("proTitle", cell_proTitle.getStringCellValue());
							}

							if(cell_dept != null){
							cell_dept.setCellType(Cell.CELL_TYPE_STRING);
							map.put("dept", cell_dept.getStringCellValue());
							}

							if(cell_workType != null){
							cell_workType.setCellType(Cell.CELL_TYPE_STRING);
						      map.put("workType", cell_workType.getStringCellValue());
							}
							if(cell_ssnType != null){
								cell_workType.setCellType(Cell.CELL_TYPE_STRING);
							      map.put("ssn", cell_workType.getStringCellValue());
								}
						
							if(map.get("name") != null && map.get("sex") != null && map.get("card") != null && map.get("workStart") != null && map.get("university") != null && map.get("degree") != null && map.get("proTitle") != null && map.get("dept") != null && map.get("workType") != null){
								orgStaffList.add(map);
								}
					}
				}
			}
			return ApiResponse.success("Excel read success!", orgStaffList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ApiResponse.fail(500, "Excel异常，请检查");
	}
	 /**
	  * 导入机构招标业绩明细
	  * @param inputStream
	  * @return
	  */
	 @SuppressWarnings("unused")
	 public static  ApiResponse<List<Map<String, Object>>> readExcelToAchieve(InputStream inputStream){
		 try {
			 Workbook workbook = WorkbookFactory.create(inputStream);
			 if(workbook == null){
				 return ApiResponse.fail(500, "Excel workbook is null...");
			 }
			 Map<String, Object> map = null;
			 List<Map<String, Object>> orgAchieveList = new ArrayList<Map<String, Object>>();
			 System.out.println("sheet个数:" + workbook.getNumberOfSheets());
			 //循环excel sheet
			 for(int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++){
				 Sheet sheet = workbook.getSheetAt(numSheet);
				 if(sheet == null){
					 continue;
				 }
				 System.out.println("第"+(numSheet+1)+"个sheet的行数："+sheet.getLastRowNum());
				 for(int rowNum = 3; rowNum <= sheet.getLastRowNum(); rowNum++){
					 Row row = sheet.getRow(rowNum);
					 if(row != null){
						 map = new HashMap<String, Object>();
						 
						 Cell c1 = row.getCell(0);
						 Cell c2 = row.getCell(1);
						 Cell c3 = row.getCell(2);
						 Cell c4 = row.getCell(3); //是否中央投资项目
						 Cell c5 = row.getCell(4);
						 Cell c6= row.getCell(5);
						 Cell c7= row.getCell(6);
						 Cell c8= row.getCell(7);
						 Cell c9= row.getCell(8);
						 Cell c10= row.getCell(9);
						 Cell c11 = row.getCell(10);
						 
						 if(c1 != null){
							 c1.setCellType(Cell.CELL_TYPE_STRING);
							 map.put("num", c1.getStringCellValue());
						 }
						 if(c2 != null){
							 c2.setCellType(Cell.CELL_TYPE_STRING);
							 map.put("name", c2.getStringCellValue());	  
						 }
						 if(c3 != null){
							 c3.setCellType(Cell.CELL_TYPE_STRING);
							 map.put("type", c3.getStringCellValue());
						 }
						 
						 if(c4 != null){
							 c4.setCellType(Cell.CELL_TYPE_STRING);
							 Integer  isCenterTemp = 0;
							 if(c4.getStringCellValue().equals("是")){
								 isCenterTemp = 1;
							 }
							 map.put("isCenter", isCenterTemp);
						 }
						 if(c5 != null){
							 c5.setCellType(Cell.CELL_TYPE_STRING);
							 map.put("entrustUnit", c5.getStringCellValue());
						 }
						 if(c6 != null){
							 c6.setCellType(Cell.CELL_TYPE_STRING);
							 map.put("entrustMoney", c6.getStringCellValue());
						 }
						 if(c7 != null){
							 c7.setCellType(Cell.CELL_TYPE_STRING);
							 map.put("bidMoney", c7.getStringCellValue());
						 }
						 if(c8 != null){
							 c8.setCellType(Cell.CELL_TYPE_STRING);
							 map.put("tenderOpenTime", c8.getStringCellValue());
						 }
						 
						 if(c9 != null){
							 c9.setCellType(Cell.CELL_TYPE_STRING);
							 map.put("bidTime", c9.getStringCellValue());
						 }
						 
						 if(c10 != null){
							 c10.setCellType(Cell.CELL_TYPE_STRING);
							 map.put("noticeMedia", c10.getStringCellValue());
						 }
						 if(c11 != null){
							 c11.setCellType(Cell.CELL_TYPE_STRING);
							 map.put("noticeDate", c11.getStringCellValue());
						 }
						 boolean flag = true;
						 if(map.get("name") != null && map.get("num") != null && map.get("type") != null && map.get("entrustUnit") != null 
								 && map.get("entrustMoney") != null && map.get("bidMoney") != null 
								 && map.get("tenderOpenTime") != null && map.get("bidTime") != null 
								 && map.get("noticeMedia") != null && map.get("noticeDate") != null){
							 orgAchieveList.add(map);
						 }else{
							 return ApiResponse.fail(500, "表格中数据不正确,请检查.");
						 }
					 }
				 }
			 }
			 return ApiResponse.success("Excel read success!", orgAchieveList);
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 return ApiResponse.fail(500, "Excel异常，请检查");
	 }
	 
	/**
	 * excle 读取课程返回List<Map<String,Object>> 集合，兼容2003-2007-2010excel
	 * @param type :目标实体类型
	 * @param path ：excel 文件
	 * @return
	 */
	/**@SuppressWarnings("unused")
	public static  ApiResponse<List<Map<String, Object>>> readExcelToCourses(InputStream inputStream){
		
		try {
			Workbook workbook = WorkbookFactory.create(inputStream);
			if(workbook == null){
				return ApiResponse.fail(500, "Excel workbook is null...");
			}
			Map<String, Object> course = null;
			List<Map<String, Object>> courseList = new ArrayList<>();
			System.out.println("sheet个数:"+workbook.getNumberOfSheets());
			//循环excel sheet
			for(int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++){
				Sheet sheet = workbook.getSheetAt(numSheet);
				if(sheet == null){
					continue;
				}
				System.out.println("第"+(numSheet+1)+"个sheet的行数："+sheet.getLastRowNum());
				//循环row，从第三行开始，第一行是表头
				for(int rowNum = 2; rowNum <= sheet.getLastRowNum(); rowNum++){
					Row row = sheet.getRow(rowNum);
					if(row != null){
						course = new HashMap<String, Object>();
						Cell cell_name = row.getCell(0);
						Cell cell_categoryName = row.getCell(1);
						Cell cell_courseCode = row.getCell(2);
						Cell cell_pptNum = row.getCell(3);
						Cell cell_coverPath = row.getCell(4);
						Cell cell_desc = row.getCell(5);
						Cell cell_authorName = row.getCell(6);
						
						// 课程名称
						if(cell_name != null){							
							cell_name.setCellType(Cell.CELL_TYPE_STRING);
							course.put("name", cell_name.getStringCellValue());
						}
						// 分类名称
						if(cell_categoryName != null){							
							cell_categoryName.setCellType(Cell.CELL_TYPE_STRING);
							course.put("category_name", cell_categoryName.getStringCellValue());
						}
						// 课程CODE
						if(cell_courseCode != null){							
							cell_courseCode.setCellType(Cell.CELL_TYPE_STRING);
							course.put("course_code", cell_courseCode.getStringCellValue());
						}
						// 课程PPT数量
						if(cell_pptNum != null){							
							cell_pptNum.setCellType(Cell.CELL_TYPE_STRING);
							String pptNum = cell_pptNum.getStringCellValue();
							if(!pptNum.isEmpty()){								
								course.put("ppt_num", pptNum);
							}
						}
						// 课程图片
						if(cell_coverPath != null){							
							cell_coverPath.setCellType(Cell.CELL_TYPE_STRING);
							course.put("cover_path", cell_coverPath.getStringCellValue());
						}
						// 课程简介
						if(cell_desc != null){							
							cell_desc.setCellType(Cell.CELL_TYPE_STRING);
							course.put("description", cell_desc.getStringCellValue());
						}
						// 讲师姓名
						if(cell_authorName != null){							
							cell_authorName.setCellType(Cell.CELL_TYPE_STRING);
							course.put("author_name", cell_authorName.getStringCellValue());
						}
						
						if(course != null && course.get("name") != null && course.get("course_code") != null){
							courseList.add(course);
						}
					}
				}
			}
			return ApiResponse.success("Excel read success!", courseList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ApiResponse.fail(500, "Excel异常，请检查");
	}
	**/
	/**
	 * excle 读取课件返回List<Map<String,Object>> 集合，兼容2003-2007-2010excel
	 * @param type :目标实体类型
	 * @param path ：excel 文件
	 * @return
	 */
	/**@SuppressWarnings("unused")
	public static  ApiResponse<List<Map<String, Object>>> readExcelToCoursewares(InputStream inputStream){
		
		try {
			Workbook workbook = WorkbookFactory.create(inputStream);
			if(workbook == null){
				return ApiResponse.fail(500, "Excel workbook is null...");
			}
			Map<String, Object> courseware = null;
			List<Map<String, Object>> coursewareList = new ArrayList<>();
			System.out.println("sheet个数:"+workbook.getNumberOfSheets());
			//循环excel sheet
			for(int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++){
				Sheet sheet = workbook.getSheetAt(numSheet);
				if(sheet == null){
					continue;
				}
				System.out.println("第"+(numSheet+1)+"个sheet的行数："+sheet.getLastRowNum());
				//循环row，从第三行开始，第一行是表头
				for(int rowNum = 2; rowNum <= sheet.getLastRowNum(); rowNum++){
					Row row = sheet.getRow(rowNum);
					if(row != null){
						courseware = new HashMap<String, Object>();
						Cell cell_courseCode = row.getCell(0);
						Cell cell_name = row.getCell(1);
						Cell cell_duration = row.getCell(2);
						Cell cell_videoPath = row.getCell(3);
						Cell cell_orderNum = row.getCell(4);
						
						// 所属课程CODE
						if(cell_courseCode != null){							
							cell_courseCode.setCellType(Cell.CELL_TYPE_STRING);
							courseware.put("course_code", cell_courseCode.getStringCellValue());
						}
						// 课件名称
						if(cell_name != null){							
							cell_name.setCellType(Cell.CELL_TYPE_STRING);
							courseware.put("name", cell_name.getStringCellValue());
						}
						// 课件时长
						if(cell_duration != null){							
							cell_duration.setCellType(Cell.CELL_TYPE_STRING);
							courseware.put("duration", cell_duration.getStringCellValue());
						}
						// 课件视频地址
						if(cell_videoPath != null){							
							cell_videoPath.setCellType(Cell.CELL_TYPE_STRING);
							courseware.put("video_path", cell_videoPath.getStringCellValue());
						}
						// 课件排序号
						if(cell_orderNum != null){							
							cell_orderNum.setCellType(Cell.CELL_TYPE_STRING);
							String orderNum = cell_orderNum.getStringCellValue();
							if(!orderNum.isEmpty()){
								courseware.put("order_num", orderNum);
							}
						}
						
						if(courseware != null && courseware.get("name") != null && courseware.get("course_code") != null){
							coursewareList.add(courseware);
						}
					}
				}
			}
			return ApiResponse.success("Excel read success!", coursewareList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ApiResponse.fail(500, "Excel异常，请检查");
	}**/
	
	/**
	 * excel xlsx高版本
	 * @param path
	 * @return
	 */
	public static List<Object> readEscel_2007(String path) {
		return null;
	}

	/**
	 * excel xls 低版本
	 * @param path
	 * @return
	 */
	public static List<Object> readExcel_2003(String path) {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * 获取文件后缀名
	 * @param path
	 * @return
	 */
	@SuppressWarnings("unused")
	private static String getSuffix(String path){
	 	if(path == null || "".equals(path.trim())) {
           return null;
        }
        if (path.contains(Constants.POINT)) {
           return path.substring(path.lastIndexOf(Constants.POINT) + 1, path.length());
        }
       return null;
	}
}
