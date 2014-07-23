package com.sniper.survey.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.sniper.survey.model.BaseEntity;
import com.sniper.survey.model.MeetUser;

public class PoiExeclExportUtil<T extends BaseEntity> {

	Workbook workbook = null;

	List<T> list = new ArrayList<>();
	List<String> denyMethods = new ArrayList<>();
	List<Method> methods = new ArrayList<>();

	public void setList(List<T> list) {
		this.list = list;
	}

	public List<T> getList() {

		return list;
	}

	public void setDenyMethods(List<String> denyMethods) {
		this.denyMethods = denyMethods;
	}

	public List<String> getDenyMethods() {
		return denyMethods;
	}

	public PoiExeclExportUtil() {
		try {
			this.workbook = new XSSFWorkbook();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createSheet(String title) {
		int countColumnNum = list.size();
		// 创建对应工作页
		Sheet sheet = this.workbook.createSheet(title);
		// 下表0开始
		Row row = sheet.createRow(0);
		// Cell cell = row.createCell(countColumnNum);

		for (int i = 0; i < countColumnNum; i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(new HSSFRichTextString(title));
		}
	}

	/**
	 * 定义样式
	 * 
	 * @return
	 */
	protected CellStyle createCellStyle() {

		CellStyle cellStyle = this.workbook.createCellStyle();
		// 设置背景颜色
		cellStyle.setFillBackgroundColor(HSSFColor.LIGHT_YELLOW.index);

		Font font = this.workbook.createFont();
		font.setColor(HSSFColor.BLACK.index);
		cellStyle.setFont(font);
		return cellStyle;
	}

	protected Font createFont() {
		Font font = this.workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		return font;
	}

	private List<Method> getMethods(T t) {
		if (methods.size() == 0) {
			Method[] methodss = t.getClass().getDeclaredMethods();
			for (int i = 0; i < methodss.length; i++) {
				Method method = methodss[i];
				String methodName = method.getName();
				if (!(methodName.startsWith("set") || denyMethods
						.contains(methodName))) {
					methods.add(method);
				}
			}
		}

		return this.methods;

	}

	public void intoData() {
		Sheet sheet = this.workbook.createSheet();

		Iterator<T> it = getList().iterator();
		// 设置第一行
		T t = it.next();
		this.methods = getMethods(t);
		Row row = sheet.createRow(0);
		for (int i = 0; i < methods.size(); i++) {
			Method method = methods.get(i);
			String methodName = method.getName();

			Cell cell = row.createCell(i);
			if (methodName.startsWith("get")) {
				cell.setCellValue(methodName.substring(3));
			} else if (methodName.startsWith("is")) {
				cell.setCellValue(methodName.substring(2));
			}
		}

		int index = 1;
		while (it.hasNext()) {

			index++;

			t = (T) it.next();
			// 下表0开始
			row = sheet.createRow(index);
			// 获取当前类的方法
			int methodLength = methods.size();
			for (int i = 0; i < methodLength; i++) {

				Method method = methods.get(i);
				String methodName = method.getName();

				// System.out.println("methodName" + index + ":" + i);
				Cell cell = row.createCell(i);
				try {

					// cell.setCellStyle(createCellStyle());
					// 执行方法
					method = t.getClass().getMethod(methodName);
					Object value = method.invoke(t);

					// System.out.println("value:" + value);
					String textValue = html(String.valueOf(value));

					if (value instanceof Integer) {
						int intValue = Integer.parseInt(textValue);
						cell.setCellValue(intValue);
					} else if (value instanceof Float) {
						float f = Float.parseFloat(textValue);
						XSSFRichTextString hssfRichTextString = new XSSFRichTextString(
								String.valueOf(f));
						cell.setCellValue(hssfRichTextString);
					} else if (value instanceof Double) {
						Double d = Double.parseDouble(textValue);
						XSSFRichTextString hssfRichTextString = new XSSFRichTextString(
								String.valueOf(d));
						cell.setCellValue(hssfRichTextString);
					} else if (value instanceof Long) {
						Long l = Long.parseLong(textValue);
						XSSFRichTextString hssfRichTextString = new XSSFRichTextString(
								String.valueOf(l));
						cell.setCellValue(hssfRichTextString);
					} else if (value instanceof Boolean) {
						Boolean boolean1 = Boolean.parseBoolean(textValue);
						cell.setCellValue(boolean1);
					} else if (value instanceof Date) {
						Date date = (Date) value;

						DateFormat dateFormat = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						cell.setCellValue(dateFormat.format(date));
					} else if (value instanceof byte[]) {
						// 二进制
						// 设置行高
						row.setHeightInPoints(60);
						// 设置宽度
						sheet.setColumnWidth(i, (short) (35.7 * 80));
						byte[] bsVaue = (byte[]) value;

						int pictureIdx = this.workbook.addPicture(bsVaue,
								Workbook.PICTURE_TYPE_JPEG);
						// 声明一个画图的顶级管理器
						CreationHelper helper = this.workbook
								.getCreationHelper();
						Drawing drawing = sheet.createDrawingPatriarch();
						ClientAnchor anchor = helper.createClientAnchor();
						anchor.setCol1(3);
						anchor.setRow1(2);
						Picture picture = drawing.createPicture(anchor,
								pictureIdx);
						// Picture.resize() works only for JPEG and PNG. Other
						// formats are not yet supported.
						picture.resize();

					} else {
						XSSFRichTextString textString = new XSSFRichTextString(
								textValue);
						// System.out.println(textString);
						cell.setCellValue(textString);
						/*
						 * if (value != null) { Pattern pattern = Pattern
						 * .compile("^//d+(//.//d+)?$"); Matcher matcher =
						 * pattern.matcher(textValue); if (matcher.matches()) {
						 * //System.out.println("textValue" //+
						 * Double.parseDouble(textValue));
						 * cell.setCellValue(Double.parseDouble(textValue)); }
						 * else {
						 * 
						 * //System.out.println("hssfRichTextString");
						 * cell.setCellValue(textValue); } } else {
						 * cell.setCellValue("空"); }
						 */

					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		}
	}

	/**
	 * 自定义插入数据
	 */
	public void customMeetUserIntoData(List<MeetUser> lists,
			Map<String, String> header) {
		Sheet sheet = this.workbook.createSheet();

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// 设置第一行
		Row row = sheet.createRow(0);
		int i = 0;
		for (String string : header.values()) {
			Cell cell = row.createCell(i);
			cell.setCellValue(string);
			i++;
		}

		int index = 1;
		for (MeetUser it : lists) {
			row = sheet.createRow(index);
			index++;
			// 设置名称
			Cell cell = row.createCell(0);
			XSSFRichTextString textString = new XSSFRichTextString(it.getName());
			cell.setCellValue(textString);
			cell = row.createCell(1);
			if (it.getSex() == 0) {
				cell.setCellValue("男");
			} else {
				cell.setCellValue("女");
			}
			cell = row.createCell(2);
			textString = new XSSFRichTextString(it.getNation());
			cell.setCellValue(textString);
			cell = row.createCell(3);
			textString = new XSSFRichTextString(it.getPost());
			cell.setCellValue(textString);
			cell = row.createCell(4);
			textString = new XSSFRichTextString(it.getUnit());
			cell.setCellValue(textString);
			cell = row.createCell(5);
			textString = new XSSFRichTextString(it.getMobilePhone());
			cell.setCellValue(textString);
			cell = row.createCell(6);
			textString = new XSSFRichTextString(it.getMoneyType());
			cell.setCellValue(textString);

			cell = row.createCell(7);
			if (it.getReportTime() != null) {
				cell.setCellValue(dateFormat.format(it.getReportTime()));
			} else {
				cell.setCellValue("");
			}

			cell = row.createCell(8);
			textString = new XSSFRichTextString(it.getCarNum());
			cell.setCellValue(textString);
			cell = row.createCell(9);
			if (it.isCarPeople()) {
				cell.setCellValue("是");
			} else {
				cell.setCellValue("否");
			}
			cell = row.createCell(10);
			if (it.getLeaveTime() != null) {
				cell.setCellValue(dateFormat.format(it.getLeaveTime()));
			} else {
				cell.setCellValue("");
			}
			cell = row.createCell(11);
			if (it.isCarLeavePeople()) {
				cell.setCellValue("是");
			} else {
				cell.setCellValue("否");
			}
			cell = row.createCell(12);
			textString = new XSSFRichTextString(it.getCarLeaveNum());
			cell.setCellValue(textString);
			cell = row.createCell(13);
			textString = new XSSFRichTextString(it.getOther());
			cell.setCellValue(textString);
			cell = row.createCell(14);
			if (it.getCreateTime() != null) {
				cell.setCellValue(dateFormat.format(it.getCreateTime()));
			} else {
				cell.setCellValue("");
			}
		}

	}

	/*
	 * 输出路径
	 */
	public void write(String filepath) throws IOException {

		FileOutputStream out = new FileOutputStream(filepath);

		workbook.write(out);
		out.close();
	}

	public static String html(String content) {

		if (content == null) {
			return "";
		}

		String html = content;
		if (!html.isEmpty()) {
			return html;
		}
		// html = html.replace( "'", "&apos;");
		html = html.replaceAll("&", "&amp;");
		html = html.replace("\"", "&quot;"); // "
		html = html.replace("\t", "&nbsp;&nbsp;");// 替换跳格
		html = html.replace(" ", "&nbsp;");// 替换空格
		html = html.replace("<", "&lt;");
		html = html.replaceAll(">", "&gt;");

		return html;
	}

}
