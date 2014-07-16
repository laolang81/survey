package com.sniper.survey.sitemesh3;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class MySiteMeshFilter extends ConfigurableSiteMeshFilter {

	@Override
	protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
		
		builder
			.addDecoratorPath("/*", "/WEB-INF/content/web/main.jsp")
			.addDecoratorPath("/admin*", "/WEB-INF/content/admin/main.jsp")
			.addExcludedPath("/admin/login**")
			.addExcludedPath("/myfiles**");
		
		
	}
}
