package com.sniper.survey.sitemesh3;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class MySiteMeshFilter extends ConfigurableSiteMeshFilter {

	@Override
	protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
		
		builder
			.addDecoratorPath("/admin*", "/WEB-INF/content/admin/main.jsp")
			.addExcludedPath("/admin/login**")
			.addExcludedPath("/admin/file-upload**")
			.addDecoratorPath("/*", "/WEB-INF/content/web/main.jsp")
			.addExcludedPath("/myfiles/**")
			;
		
		
	}
}
