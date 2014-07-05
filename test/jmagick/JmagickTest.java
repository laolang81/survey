package jmagick;

import magick.MagickException;

import org.junit.Test;

import com.sniper.survey.util.ImageUtils;
import com.sniper.survey.util.JMagickUtil;

public class JmagickTest {

	@Test
	public void resises() throws MagickException
	{
		//System.out.println(System.getProperty("java.library.path"));
		//System.setProperty("jmagick.systemclassloader", "no");
		String sourceFileName = "/approot/www/jsp/survey/WebRoot/data/2258-120410204I911.jpg";
		JMagickUtil.resize(sourceFileName, 100, 100);
		System.out.println("sssssss");
		//ImageUtils.resize(sourceFileName, 100, 100, false);
	}
}

