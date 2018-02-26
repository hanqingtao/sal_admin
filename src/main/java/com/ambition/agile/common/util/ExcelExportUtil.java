package com.ambition.agile.common.util;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

/**
 * 通用导出Excel
 * @author ckl
 *
 * @param <T>：任意数据类型
 */
public class ExcelExportUtil <T> {
		/**
		 * 
		 * @param title 表格标题
		 * @param headers ：表格头
		 * @param dataset ：数据集合
		 * @param out ：表格输出流,可做输出本地文件或者上传FTP等处理
		 * @param pattern ：时间类型的格式 不给，默认值："yyyy-MM-dd"
		 */
	   @SuppressWarnings({ "unchecked", "deprecation" })
	   public void exportExcel(String title, String[] headers,Collection<T> dataset, OutputStream out, String pattern) {
		   if(ComUtil.isNullOrEmpty(pattern)){
			   pattern = "yyyy-MM-dd"; //默认时间格式
		   }
	      // 声明一个工作薄
	      HSSFWorkbook workbook = new HSSFWorkbook();
	      // 生成一个表格
	      HSSFSheet sheet = workbook.createSheet(title);
	      // 设置表格默认列宽度为15个字节
	      sheet.setDefaultColumnWidth(20);
	      // 生成一个样式,作为表格头的样式
	      HSSFCellStyle style = workbook.createCellStyle();
	      // 设置这些样式
	      style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
	      style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	      style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	      style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	      style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	      style.setBorderTop(HSSFCellStyle.BORDER_THIN);
	      style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	      // 生成一个字体
	      HSSFFont font = workbook.createFont();
	      font.setColor(HSSFColor.VIOLET.index);
	      font.setFontHeightInPoints((short) 12);
	      font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	      // 把字体应用到当前的样式
	      style.setFont(font);
	      // 生成并设置另一个样式
	      HSSFCellStyle style2 = workbook.createCellStyle();
	      style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
	      style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	      style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	      style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	      style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
	      style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
	      style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	      style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	      // 生成另一个字体
	      HSSFFont font2 = workbook.createFont();
	      font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
	      // 把字体应用到当前的样式
	      style2.setFont(font2);
	     
	 
	      //产生表格标题行
	      HSSFRow row = sheet.createRow(0);
	      for (int i = 0; i < headers.length; i++) {
	         HSSFCell cell = row.createCell(i);
	         cell.setCellStyle(style);
	         HSSFRichTextString text = new HSSFRichTextString(headers[i]);
	         cell.setCellValue(text);
	      }
	 
	      //遍历集合数据，产生数据行
	      Iterator<T> it = dataset.iterator();
	      int index = 0;
	      while (it.hasNext()) {
	         index++;
	         row = sheet.createRow(index);
	         T t = (T) it.next();
	         //获取属性值
	         Field[] fields = t.getClass().getDeclaredFields();
	         for (int i = 0; i < fields.length; i++) {
	            HSSFCell cell = row.createCell(i);
	            cell.setCellStyle(style2);
	            Field field = fields[i];
	            String fieldName = field.getName();
	            String getMethodName = "get"
	                   + fieldName.substring(0, 1).toUpperCase()
	                   + fieldName.substring(1);
	            try {
	                Class tCls = t.getClass();
	                Method getMethod = tCls.getMethod(getMethodName,
	                      new Class[] {});
	                Object value = getMethod.invoke(t, new Object[] {});
	                //判断值的类型后进行强制类型转换
	                String textValue = null; //cell中的值
	                if (value instanceof Date) {
	                   Date date = (Date) value;
	                   SimpleDateFormat sdf = new SimpleDateFormat(pattern);
	                    textValue = sdf.format(date);
	                }else{
	                   //其它数据类型都当作字符串简单处理
	                   textValue = value.toString();
	                }
	                if(textValue!=null){
	                   Pattern p = Pattern.compile("^//d+(//.//d+)?$");  
	                   Matcher matcher = p.matcher(textValue);
	                   if(matcher.matches()){
	                      //是数字当作double处理
	                      cell.setCellValue(Double.parseDouble(textValue));
	                   }else{
	                      HSSFRichTextString richString = new HSSFRichTextString(textValue);
	                      HSSFFont font3 = workbook.createFont();
	                      font3.setColor(HSSFColor.BLUE.index);
	                      richString.applyFont(font3);
	                      cell.setCellValue(richString);
	                   }
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	         }
	 
	      }
	      try {
	         workbook.write(out);
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
	 
	   }
}
