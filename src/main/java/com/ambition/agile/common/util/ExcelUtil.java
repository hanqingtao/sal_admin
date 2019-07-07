package com.ambition.agile.common.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.ambition.agile.modules.course.entity.Course;
import com.ambition.agile.modules.course.entity.CourseCategory;
//import com.adks.dubbo.commons.ApiResponse;

/**
 * apache poi 操作excel 方法
 * @author harry
 *
 */
public class ExcelUtil{
	
	/**
	 * excle 读取用户返回User集合，兼容2003-2007-2010excel(需求修改，导入用户返回excel,excel中的数据是导入失败的用户)
	 * @param type :目标实体类型
	 * @param path ：excel 文件
	 * @return
	 */
	@SuppressWarnings("unused")
	public static  List<Course> readExcelToCourse(InputStream inputStream){
		
		try {
			Workbook workbook = WorkbookFactory.create(inputStream);
			if(workbook == null){
				return null;//ApiResponse.fail(500, "Excel workbook is null...");
			}
			Course user = null;
			List<Course> courseList = new ArrayList<>();
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
						user = new Course();
						Cell cell_loginName = row.getCell(0);
						Cell cell_userName = row.getCell(1);
						Cell cell_email = row.getCell(2);
						Cell cell_deptName = row.getCell(3);
						Cell cell_telphone = row.getCell(4);
						Cell cell_gender = row.getCell(5);
						Cell cell_icard = row.getCell(6);
						
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
						Integer gender = 1;
						if(cell_gender != null){
							cell_gender.setCellType(Cell.CELL_TYPE_STRING);
							String gs = cell_gender.getStringCellValue();
							if(gs != null && gs.equals("女")){
								gender = 0;
							}
						}
						user.setGender(gender);
						if(cell_icard != null){
							cell_icard.setCellType(Cell.CELL_TYPE_STRING);
							user.setIcard(cell_icard.getStringCellValue());
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
	}
	
	@SuppressWarnings("unused")
    public static  ApiResponse<Map> renameExcelFileName(InputStream inputStream){
        
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);
            if(workbook == null){
                return ApiResponse.fail(500, "Excel workbook is null...");
            }
            User user = null;
            Map map = new HashMap<>();
            List list = new ArrayList<>();
            System.out.println("sheet个数:"+workbook.getNumberOfSheets());
            //循环excel sheet
            for(int numSheet = 0; numSheet < 1; numSheet++){
                Sheet sheet = workbook.getSheetAt(numSheet);
                if(sheet == null){
                    continue;
                }
                System.out.println("第"+(numSheet+1)+"个sheet的行数："+sheet.getLastRowNum());
                //循环row，从第三行开始，第一行是表头
                for(int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++){
                    Row row = sheet.getRow(rowNum);
                    if(row != null){
                        user = new User();
                        Cell cell_Name = row.getCell(0);
                        Cell cell_newName = row.getCell(1);
                        map.put(rowNum+"",cell_Name.getStringCellValue()+ cell_newName.getStringCellValue());
                        //System.out.println(cell_Name.getStringCellValue());
                        list.add(rowNum, cell_Name.getStringCellValue()+ cell_newName.getStringCellValue());
                       // System.out.println(cell_newName.getStringCellValue());
                        if(cell_Name != null){                         
                            cell_Name.setCellType(Cell.CELL_TYPE_STRING);
                            //System.out.println(cell_Name.getStringCellValue());
                        }
                        
                        if(cell_newName != null){                          
                            cell_newName.setCellType(Cell.CELL_TYPE_STRING);
                            //System.out.println(cell_newName.getStringCellValue());
                        }
                       
//                        if(user.getLogin_name() != null && user.getUser_name() != null){
//                            userList.add(user);
//                        }
                    }
                }
                
            }
//            Collections.sort(list);
//            for(int t=0;t<list.size();t++){
//               
//                
//            }
            System.out.println("@@@@@@"+map.size());
            return ApiResponse.success("Excel read success!", map);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ApiResponse.fail(500, "Excel异常，请检查");
    }
	
	@SuppressWarnings("unused")
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
						if(cell_domain != null){
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
						orgList.add(map);
					}
				}
				
			}
			return ApiResponse.success("Excel read success!", orgList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ApiResponse.fail(500, "Excel异常，请检查");
	}
	
	
	/**
	 * 导入试题
	 * @param inputStream
	 * @return
	 */
	@SuppressWarnings("unused")
	public static  ApiResponse<List<Map<String, Object>>> readExcelToQuestions(InputStream inputStream){
		try {
			Workbook workbook = WorkbookFactory.create(inputStream);
			if(workbook == null){
				return ApiResponse.fail(500, "Excel workbook is null...");
			}
			Map<String, Object> map = null;
			List<Map<String, Object>> questionList = new ArrayList<>();
			System.out.println("sheet个数:" + workbook.getNumberOfSheets());
			//循环excel sheet
			for(int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++){
				Sheet sheet = workbook.getSheetAt(numSheet);
				if(sheet == null){
					continue;
				}
				System.out.println("第"+(numSheet+1)+"个sheet的行数："+sheet.getLastRowNum());
				//循环row，从第三行开始，第一行是文件描述，第二行是标题
				for(int rowNum = 3; rowNum <= sheet.getLastRowNum(); rowNum++){
					Row row = sheet.getRow(rowNum);
					if(row != null){
						map = new HashMap<String, Object>();
						// 题型
						Cell cell_questionType = row.getCell(0);
						// 分数
						Cell cell_score = row.getCell(1);
						// 题目内容
						Cell cell_name = row.getCell(2);
						// 可选项
						Cell cell_options = row.getCell(3);
						// 答案
						Cell cell_answer = row.getCell(4);
						// 课程唯一标识
						Cell cell_courseCode = row.getCell(5);
						
						if(cell_questionType != null){							
							cell_questionType.setCellType(Cell.CELL_TYPE_STRING);
							map.put("question_type_str", cell_questionType.getStringCellValue());
						}
						if(cell_score != null){
							cell_score.setCellType(Cell.CELL_TYPE_STRING);
							map.put("score", cell_score.getStringCellValue());
						}
						if(cell_name != null){
							cell_name.setCellType(Cell.CELL_TYPE_STRING);
							String tname = cell_name.getStringCellValue();
							if(StringUtils.isNotEmpty(tname)){								
								for(int i = 10; i < 14; i++){
									tname = tname.replaceAll(String.valueOf((char)i), "");
								}
							}
							map.put("name", tname);
						}
						if(cell_options != null){
							cell_options.setCellType(Cell.CELL_TYPE_STRING);
							map.put("options", cell_options.getStringCellValue());
						}
						if(cell_answer != null){
							cell_answer.setCellType(Cell.CELL_TYPE_STRING);
							map.put("answer", cell_answer.getStringCellValue());
						}
						if(cell_courseCode != null){
							cell_courseCode.setCellType(Cell.CELL_TYPE_STRING);
							map.put("course_code", cell_courseCode.getStringCellValue());
						}
						if(map.get("question_type_str") != null && map.get("name") != null){
							questionList.add(map);
						}
					}
				}
			}
			return ApiResponse.success("Excel read success!", questionList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ApiResponse.fail(500, "Excel异常，请检查");
	}
	
	
	/**
	 * 课程导入试题
	 * @param inputStream
	 * @return
	 */
	@SuppressWarnings("unused")
	public static  ApiResponse<List<Map<String, Object>>> readExcelToQuestion(InputStream inputStream){
		try {
			Workbook workbook = WorkbookFactory.create(inputStream);
			if(workbook == null){
				return ApiResponse.fail(500, "Excel workbook is null...");
			}
			Map<String, Object> map = null;
			List<Map<String, Object>> questionList = new ArrayList<>();
			//循环excel sheet
			for(int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++){
				Sheet sheet = workbook.getSheetAt(numSheet);
				if(sheet == null){
					continue;
				}
				//System.out.println("第"+(numSheet+1)+"个sheet的行数："+sheet.getLastRowNum());
				//循环row，从第三行开始，第一行是文件描述，第二行是标题
				for(int rowNum = 3,t =sheet.getLastRowNum() ; rowNum <= t; rowNum++){
					Row row = sheet.getRow(rowNum);
					if(row != null){
						map = new HashMap<String, Object>();
						// 题型
						Cell cell_questionType = row.getCell(0);
						// 分数
						Cell cell_score = row.getCell(1);
						// 题目内容
						Cell cell_name = row.getCell(2);
						// 可选项5
						Cell cell_options = row.getCell(3);
						// 答案
						Cell cell_answer = row.getCell(4);
						// 课程唯一标识
						//Cell cell_courseCode = row.getCell(5);
						
						
						if(cell_questionType != null){							
							cell_questionType.setCellType(Cell.CELL_TYPE_STRING);
							map.put("question_type_str", cell_questionType.getStringCellValue());
						}
						String score=null;
						if(cell_score != null){
							cell_score.setCellType(Cell.CELL_TYPE_STRING);
							for(int j=10;j<14;j++){  
		                		score = cell_score.getStringCellValue().replaceAll(String.valueOf((char)j), "");  
		                    }
							map.put("score",score);
						}
						String name=null;
						if(cell_name != null){
							cell_name.setCellType(Cell.CELL_TYPE_STRING);
							String tname = cell_name.getStringCellValue();
							if(StringUtils.isNotEmpty(tname)){								
								for(int i = 10; i < 14; i++){
									tname = tname.replaceAll(String.valueOf((char)i), "");
								}
							}
							map.put("name", tname);
						}
						String options=null;
						if(cell_options != null){
							cell_options.setCellType(Cell.CELL_TYPE_STRING);
							for(int j=10;j<14;j++){  
		                		options = cell_options.getStringCellValue().replaceAll(String.valueOf((char)j), "");  
		                    }
							map.put("options",options);
						}
						String answer=null;
						if(cell_answer != null){
							
							cell_answer.setCellType(Cell.CELL_TYPE_STRING);
							for(int j=10;j<14;j++){  
								answer = cell_answer.getStringCellValue().replaceAll(String.valueOf((char)j), "");  
		                    }
							map.put("answer", answer.toUpperCase());
						}
						/*if(cell_courseCode != null){
							cell_courseCode.setCellType(Cell.CELL_TYPE_STRING);
							map.put("course_code", cell_courseCode.getStringCellValue());
						}*/
						if(map.get("question_type_str") != null && map.get("name") != null){
							questionList.add(map);
						}
					}
				}
			}
			return ApiResponse.success("Excel read success!", questionList);
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
	@SuppressWarnings("unused")
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
						Cell cell_publishTime = row.getCell(2);
						Cell cell_courseCode = row.getCell(3);
						Cell cell_pptNum = row.getCell(4);
						Cell cell_coverPath = row.getCell(5);
						Cell cell_desc = row.getCell(6);
						Cell cell_authorName = row.getCell(7);
						
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
						// 课程发布时间
						if(cell_publishTime != null){							
							cell_publishTime.setCellType(Cell.CELL_TYPE_STRING);
							course.put("publish_time", cell_publishTime.getStringCellValue());
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
						
						if(course != null && course.get("name") != null){
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
	
	/**
	 * excle 读取课件返回List<Map<String,Object>> 集合，兼容2003-2007-2010excel
	 * @param type :目标实体类型
	 * @param path ：excel 文件
	 * @return
	 */
	@SuppressWarnings("unused")
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
	}
	
	@SuppressWarnings("unused")
	public static  void writeUserToExcel(String path,List<Map> failUserList){
		try {
			
	        FileInputStream in = new FileInputStream(path);
	        Workbook  workbook = new HSSFWorkbook(in);
			List<User> userList = new ArrayList<>();
			System.out.println("sheet个数:"+workbook.getNumberOfSheets());
			Sheet sheet = workbook.getSheetAt(0);
			if(sheet.getLastRowNum()+1>=2){
				sheet.shiftRows(sheet.getLastRowNum()+1, 2*sheet.getLastRowNum()-1, -(sheet.getLastRowNum()-1));
			}
			//写入数据
			FileOutputStream  out =  new FileOutputStream(path);
			workbook.write(out);
			for (int j = 0; j < failUserList.size(); j++) {
				Map user=failUserList.get(j);
				// 创建一行：从第三行开始，跳过属性列
                Row row = sheet.createRow(j + 2);	
                String loginName=(user.get("loginName")==null?"":user.get("loginName")+"");
                String userName=(user.get("userName")==null?"":user.get("userName")+"");
                String email=(user.get("email")==null?"":user.get("email")+"");
                String deptName=(user.get("deptName")==null?"":user.get("deptName")+"");
                String telphone=(user.get("telphone")==null?"":user.get("telphone")+"");
                String gender=(user.get("gender")==null?"":user.get("gender")+"");
                String icard=(user.get("icard")==null?"":user.get("icard")+"");
                String errorMsg=user.get("errorMsg")+"";
                // 在一行内循环
                Cell first = row.createCell(0);
                first.setCellValue(loginName);
                Cell second = row.createCell(1);
                second.setCellValue(userName);
                Cell third = row.createCell(2);
                third.setCellValue(email);
                Cell four = row.createCell(3);
                four.setCellValue(deptName);
                Cell five = row.createCell(4);
                five.setCellValue(telphone);
                Cell six = row.createCell(5);
                six.setCellValue(gender);
                Cell seven = row.createCell(6);
                seven.setCellValue(icard);
                Cell eight= row.createCell(7);
                eight.setCellValue(errorMsg);
			}
			out =  new FileOutputStream(path);
	        workbook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
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
