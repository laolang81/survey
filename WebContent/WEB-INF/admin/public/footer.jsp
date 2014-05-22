<%@ taglib uri="/struts-tags" prefix="s"%>	
		</div>
		<!-- /container -->
		
		<div id="footer">
			<p class="alignleft">
				<s:text name="Thanks for using"></s:text>
				<s:property value="#configInfo['cg_webname']"/>(<s:property value="#TimeSpent"/>,<s:property value="#phpMemory"/>)
			</p>
			<p id="footer-upgrade" class="alignright">
				<s:text name="Version" /><s:property value="#Version"/>
			</p>
			
			
</body>
</html>