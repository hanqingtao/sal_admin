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
			Course course = null;
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
						course = new Course();
						Cell courseCatalgoName = row.getCell(0);
						Cell courseCatalgoCode = row.getCell(1);
						Cell courseName = row.getCell(2);
						Cell courseCode = row.getCell(3);
						Cell courseVideoPath = row.getCell(4);
						
						if(courseCatalgoName != null){							
							courseCatalgoName.setCellType(Cell.CELL_TYPE_STRING);
							course.setCategoryName(courseCatalgoName.getStringCellValue());
						}
						
						if(courseCatalgoCode != null){						
							courseCatalgoCode.setCellType(Cell.CELL_TYPE_STRING);
							course.setCategoryCode(courseCatalgoCode.getStringCellValue());
						}
						if(courseCode != null){							
							courseCode.setCellType(Cell.CELL_TYPE_STRING);
							course.setCourseCode(courseCode.getStringCellValue());
						}
						if(courseName != null){
							courseName.setCellType(Cell.CELL_TYPE_STRING);
							course.setName(courseName.getStringCellValue());
						}
						if(courseVideoPath != null){
							courseVideoPath.setCellType(Cell.CELL_TYPE_STRING);
							course.setVideoPath(courseVideoPath.getStringCellValue());
						}
						courseList.add(course);
					}
				}
				
			}
			return courseList;//ApiResponse.success("Excel read success!", userList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;//ApiResponse.fail(500, "Excel异常，请检查");
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
//        if (path.contains(Constants.POINT)) {
//           return path.substring(path.lastIndexOf(Constants.POINT) + 1, path.length());
//        }
       return null;
	}
}
