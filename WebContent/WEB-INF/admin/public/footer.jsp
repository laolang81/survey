	
		</div>
		<!-- /container -->
		
		<div id="footer">
			<p class="alignleft">
				<s:text name="Thanks for using"></s:text>
				<?php echo $this->configInfo['cg_webname'];?>
				(
				<?php echo $this->TimeSpent,',',$this->phpMemory?>
				)
			</p>
			<p id="footer-upgrade" class="alignright">
				<?php echo $this->translate('Version'),' ',$this->Version?>
			</p>
			
			
</body>
</html>