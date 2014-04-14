package survey;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.jws.soap.SOAPBinding.Style;

import org.apache.catalina.startup.SetContextPropertiesRule;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDataFormatter;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

public class TestPoi {

	@Test
	public void testPost() throws FileNotFoundException, IOException
	{
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		//工作表
		HSSFSheet sheet1 = hssfWorkbook.createSheet("s1");
		HSSFSheet sheet2 = hssfWorkbook.createSheet("s2");
		//行
		HSSFRow row1 = sheet1.createRow(0);
		//单元格
		HSSFCell cell = row1.createCell(0);
		cell.setCellValue(false);
		row1.createCell(1).setCellValue(Calendar.getInstance());
		row1.createCell(2).setCellValue(new Date());
		row1.createCell(3).setCellValue(1114444.11212121);
		row1.createCell(4).setCellValue(new HSSFRichTextString("aaaaaaaaaaa"));
		
		//创建数据他见格式
		HSSFDataFormat format = hssfWorkbook.createDataFormat();
	
		
		//格式化数据
		//日期格式化
		HSSFCellStyle cellStyle = hssfWorkbook.createCellStyle();
		
		cellStyle.setDataFormat(format.getFormat("yyyy-mm-dd hh:mm:ss"));
		//设置样式
		row1.getCell(1).setCellStyle(cellStyle);
		row1.getCell(2).setCellStyle(cellStyle);
		//doubule格式化
		cellStyle = hssfWorkbook.createCellStyle();
		cellStyle.setDataFormat(format.getFormat("#,###.000"));
		row1.getCell(3).setCellStyle(cellStyle);
		//设置列款
		sheet1.setColumnWidth(1, 200);
		
		//自动列款,(单位:1/20点)
		sheet1.autoSizeColumn(2);
		
		sheet1.setColumnWidth(7, 7000);
		
		//自动会绕文本
		cellStyle = hssfWorkbook.createCellStyle();
		
		
		
		hssfWorkbook.write(new FileOutputStream(new File("1.xls")));
		
	}
}
