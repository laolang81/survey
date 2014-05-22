<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
		
		<!-- /container -->
		
		<div id="footer">
			<p class="alignleft">
				<s:text name="Thanks for using"></s:text>
				<s:property value="#configInfo['cg_webname']"/>(<s:property value="#TimeSpent"/>,<s:property value="#phpMemory"/>)
			</p>
			<p id="footer-upgrade" class="alignright">
				<s:text name="Version" /><s:property value="#Version"/>
			</p>
			
</div>			
</body>
</html>