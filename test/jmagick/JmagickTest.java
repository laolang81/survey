package jmagick;

import magick.MagickException;

import org.junit.Test;

import com.sniper.survey.util.JMagickUtil;

public class JmagickTest {

	@Test
	public void resises() throws MagickException
	{
		System.out.println(System.getProperty("java.library.path"));
		String sourceFileName = "/approot/www/jsp/survey/WebRoot/data/502235dae3d4b.jpg";
		JMagickUtil.resize(sourceFileName, 100, 100);
		System.out.println("sssssss");
		
	}
}



