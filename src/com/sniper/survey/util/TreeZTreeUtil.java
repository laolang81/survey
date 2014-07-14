package com.sniper.survey.util;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 树形数据组装
 * 
 * @author sniper
 * 
 */
public class TreeZTreeUtil {

	StringBuffer buffer = new StringBuffer();
	{
		buffer.append("<script type=\"text/javascript\"><!--");
		buffer.append("function getFont(treeId, node) {");
		buffer.append("	return node.font ? node.font : {};");
		buffer.append("};");
		buffer.append("var setting = {");
		buffer.append("	view:{");
		buffer.append("		showIcon:false,");
		buffer.append("		showLine:true,");
		buffer.append("		showTitle:true,");
		buffer.append("		fontCss: getFont,");
		buffer.append("		nameIsHTML: true");
		buffer.append("		},");
		buffer.append("	data: {");
		buffer.append("		simpleData: {");
		buffer.append("			enable:true");
		buffer.append("		},");
		buffer.append("		key: {");
		buffer.append("			title:\"title\"");
		buffer.append("		}");
		buffer.append("	}");
		buffer.append("};");
		buffer.append("");
		buffer.append("$(document).ready(function(){");
		buffer.append("zTreeObj = $.fn.zTree.init($(\"#ztree\"), setting, zNodes);");
		buffer.append("});");
		buffer.append("//-->");
		buffer.append("</script>");

	}

	private Collection<GrantedAuthority> authorities;
	
	public TreeZTreeUtil() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();

		authorities = (Collection<GrantedAuthority>) userDetails
				.getAuthorities();
		
		for(GrantedAuthority authority: authorities){
			System.err.println("输出结果" + authority.getAuthority());
		}
		
	}

}
