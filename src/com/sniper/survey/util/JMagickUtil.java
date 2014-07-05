package com.sniper.survey.util;

import java.io.File;

import magick.ImageInfo;
import magick.MagickException;
import magick.MagickImage;


/**
 * JMagick 调用 ImageMagick接口
 * @author sniper
 *
 */
public class JMagickUtil {
	
	static{
		System.setProperty("jmagick.systemclassloader", "no");
	}

	public JMagickUtil() {
		// Resize
		System.setProperty("jmagick.systemclassloader", "no");
		System.out.println("sss");
		
	}
	/**
	 * 图片缩小
	 * @param sourceFileName
	 * @param w
	 * @param h
	 * @throws MagickException
	 */
	public static void resize(String sourceFileName, int w, int h) throws MagickException{
		
		ImageInfo info = new ImageInfo(sourceFileName);
		//MagickImage image = new MagickImage();
		//image.allocateImage(info);
		//MagickImage image2 = image.scaleImage(w, h);
		//File file = new File(sourceFileName);
		//System.out.println(file.getName());
		
		//image2.setFileName(sourceFileName);
		//image2.writeImage(info);
	}
}
