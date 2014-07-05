package jmagick;

import java.awt.Color;
import java.awt.Font;

import magick.MagickException;

import org.junit.Test;

import com.sniper.survey.util.ImageUtils;
import com.sniper.survey.util.JMagickUtil;

public class JmagickTest {

	@Test
	public void resises() throws MagickException
	{
		System.out.println(System.getProperty("java.library.path"));
		String sourceFileName = "/approot/www/jsp/survey/WebRoot/data/502235dae3d4b.jpg";
		JMagickUtil.resize(sourceFileName, 100, 100);
		System.out.println("sssssss");
		ImageUtils.pressText(sourceFileName, "旺仔之印", "宋体", Font.BOLD | Font.ITALIC, 20,
				Color.BLACK, 100, 100, 0.8f);
		//ImageUtils.resize(sourceFileName, 100, 100, false);
	}
}

