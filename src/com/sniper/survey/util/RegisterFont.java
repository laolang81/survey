package com.sniper.survey.util;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class RegisterFont {

	private InputStream in;

	public RegisterFont() {
	}

	public RegisterFont(String url) throws Exception {

		File file = new File(url);	
		if (!file.exists()) {
			throw new Exception(url + "字体文件不存在");
		}
		if (!file.canRead()) {
			throw new Exception("字体文件不可读");
		}
		// 读取字体字节流
		String urlPath = file.getAbsolutePath();
		InputStream inputStream = new FileInputStream(urlPath);
		this.in = inputStream;
	}

	public RegisterFont(InputStream in) {
		this.in = in;
	}

	/**
	 * 注册字体 并返回注册的名称
	 * 
	 * @param url
	 * @throws Exception
	 */
	public String reginterFont() throws Exception {
		
		// 创建字体
		Font font = Font.createFont(Font.TRUETYPE_FONT, in);
		// 返回本地 GraphicsEnvironment
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		// 创建字体
		if (ge.registerFont(font)) {
			// 注册成功
		} else {
			// 已经被注册字体存在
		}
		in.close();
		return font.getFontName();
	}

	/*
	 * public　void registerFont(String url)　 {　 　　var　fontURL　=　new　File(url);　
	 * 　
	 * 　var　font　=　java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT,　fontURL
	 * );　 　　var　ge　=　GraphicsEnvironment.getLocalGraphicsEnvironment();　
	 * 　　var　clazz　=　ge.getClass();　
	 * 　　var　registerFont　=　clazz.getMethod("registerFont",　font.getClass());　
	 * 　　var　success　=　(registerFont.invoke(ge,　font))　as　Boolean;　 　
	 * 　　if(success)　{　 　　　　//text.font　=　Font　{　name:　"创艺简行楷"　size:　36　};　
	 * 　　}　else　{　 　　　　//println("not　success!");　 　　}　 }　 　
	 */
	/*
	 * var　text:　Text　=　Text　{　
	 * 　　　　translateX:　bind　(300　-　text.layoutBounds.width)　/　2.0　
	 * 　　　　content:　"世界，你好！"　 　　　　font:　Font{name:"微软雅黑"　size:　22}　 　　　　}　 　
	 * var　button　:　Button　=　Button　{　
	 * 　　translateX:　bind　(300　-　button.layoutBounds.width)　/　2.0　
	 * 　　text:　"Register　Font"　 　　action:　function()　{　
	 * 　　　　registerFont("d:/cy23.TTF");　 　　}　 }　 　 var　vBox　=　VBox　{　
	 * 　　layoutY:　50　 　　spacing:　20　 　　content:　[　text,　button　]　 }　 　 Stage　{　
	 * 　　title:　"Custom　Font"　 　　resizable:　false　 　　scene:　Scene　{　
	 * 　　　　width:　300　 　　　　height:　150　 　　　　content:　vBox　 　　}　 }　
	 */
}
