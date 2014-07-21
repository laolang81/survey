package com.sniper.survey.struts2.action.admin;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.sniper.survey.struts2.RootAction;

@Namespace("/admin/file-download")
public class FileDownloadAction extends RootAction {

	private static final long serialVersionUID = 1L;

	// 下载mime//application/vnd.ms-excel
	private String contentType;
	// 长度
	private long contentLength;
	// 下在文件名字,,一般取值为attachement;filename="ss.pdf"
	private String contentDisposition;
	// 下载文件流,默认即可
	private String inputName = "inputStream";
	private short bufferSize = 1024;
	private boolean allowCaching = false;
	private String contentCharSet = "utf-8";
	private InputStream inputStream;
	private String fileName;

	public String getContentType() {
		return contentType;
	}
	
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public long getContentLength() {
		return contentLength;
	}

	public void setContentLength(long contentLength) {
		this.contentLength = contentLength;
	}

	public String getContentDisposition() {
		return contentDisposition;
	}

	public void setContentDisposition(String contentDisposition) {
		this.contentDisposition = contentDisposition;
	}

	public String getInputName() {
		return inputName;
	}

	public void setInputName(String inputName) {
		this.inputName = inputName;
	}

	public short getBufferSize() {
		return bufferSize;
	}

	public void setBufferSize(short bufferSize) {
		this.bufferSize = bufferSize;
	}

	public boolean isAllowCaching() {
		return allowCaching;
	}

	public void setAllowCaching(boolean allowCaching) {
		this.allowCaching = allowCaching;
	}

	public String getContentCharSet() {
		return contentCharSet;
	}

	public void setContentCharSet(String contentCharSet) {
		this.contentCharSet = contentCharSet;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	@Override
	@Action(value = "index", results = { @Result(name = "success", type = "stream") })
	public String execute() throws Exception {
		// 确定各个成员变量的值
		setContentType("application/vnd.ms-excel");
		setContentDisposition("attachment;filename='" + getFileName() + "'");
		inputStream = new FileInputStream(getFileName());
		setContentLength(inputStream.available());
		return SUCCESS;
	}
}
