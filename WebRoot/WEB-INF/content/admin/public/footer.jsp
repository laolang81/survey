<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
		
		<!-- /container -->
		</div>
<div id="footer" class="bs-footer" role="contentinfo">
	<p class="alignleft">
		<s:text name="Thanks for using"></s:text>
		<s:property value="#configInfo['cg_webname']"/>(<s:property value="#attr.TimeSpent"/>,<s:property value="#attr.phpMemory"/>)
	</p>
	<p id="footer-upgrade" class="alignright">
	${attr.htmlPath }
		<s:text name="Version" /><s:property value="#Version"/>
	</p>
	
</div>
</body>
</html>