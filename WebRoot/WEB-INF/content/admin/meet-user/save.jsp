<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<s:form action="upload" namespace="/admin/file-upload"  method="post" enctype="multipart/form-data">
	<s:file name="uf" label="FileName"></s:file>
	<button type="submit" class="btn btn-danger">保存</button>
</s:form>

