package com.sniper.survey.struts2.action.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sniper.survey.model.Files;
import com.sniper.survey.service.impl.FilesService;

@Controller
@Scope("prototype")
@Namespace("/admin/file-upload")
@ParentPackage("default")
public class FileUploadAction extends BaseAction<Files> {

	private static final long serialVersionUID = -4573693710739416249L;

	@Resource
	private FilesService filesService;

	// 对应的表单名称
	private File uf;
	// 上传的表单名称
	private String ufFileName;
	// 上传类型
	private String ufContentType;
	//文件类型地址  image/flush,file
	private String dir = "images";

	private Map<String, Files> result = new HashMap<>();

	/**
	 * @return the uf
	 */
	public File getUf() {
		return uf;
	}

	/**
	 * @param uf
	 *            the uf to set
	 */
	public void setUf(File uf) {
		this.uf = uf;
	}

	/**
	 * @return the ufFileName
	 */
	public String getUfFileName() {
		return ufFileName;
	}

	/**
	 * @param ufFileName
	 *            the ufFileName to set
	 */
	public void setUfFileName(String ufFileName) {
		this.ufFileName = ufFileName;
	}

	/**
	 * @return the ufContentType
	 */
	public String getUfContentType() {
		return ufContentType;
	}

	/**
	 * @param ufContentType
	 *            the ufContentType to set
	 */
	public void setUfContentType(String ufContentType) {
		this.ufContentType = ufContentType;
	}

	public String getDir() {
		return dir;
	}
	
	public void setDir(String dir) {
		this.dir = dir;
	}
	/**
	 * @return the result
	 */
	public Map<String, Files> getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(Map<String, Files> result) {
		this.result = result;
	}

	@Action(value = "upload", results = {
			@Result(name = "success", type = "json"),
			@Result(name = "input", type = "json") })
	@SkipValidation
	public String upload() throws IOException {

		//上传文件
		uploadFile(uf);

		return SUCCESS;
	}

	private void uploadFile(File f) throws FileNotFoundException, IOException {

		ServletContext context = ServletActionContext.getServletContext();
		// 保存地址
		String dir = context.getRealPath("../data/");
		System.out.println(context.getRealPath("./"));
		System.out.println(dir);
		
		
		String savePath = getSaveDir();
		String saveUrl = savePath +  getSaveFilename();
		
		System.out.println(savePath);
		System.out.println(saveUrl);
		File fileDir = new File(savePath);
		if(!fileDir.isDirectory()){
			fileDir.mkdirs();
		}
		
		FileOutputStream out = new FileOutputStream(saveUrl);
		FileInputStream in = new FileInputStream(f);

		byte[] buffer = new byte[1024];
		int len = 0;

		while ((len = in.read(buffer)) != -1) {
			out.write(buffer, 0, len);
		}
		out.close();
		in.close();
	}
	/**
	 * 获取保存的地址
	 * @return
	 */
	private String getSaveDir()
	{
		String saveDir = "/approot/www/jsp/survey/WebRoot/data/" + getDir() + "/";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		saveDir += ymd + "/" ;
		return saveDir;
	}
	/**
	 * 生成文件问名称
	 * @return
	 */
	private String getSaveFilename()
	{
		String fileExt = ufFileName.substring(ufFileName.lastIndexOf(".") + 1).toLowerCase();
		//if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String newFileName = df.format(new Date()) + "_" + new Random().nextInt(10000) + "." + fileExt;
		return newFileName;
	}
	
	/**
	 * 吧上传的文件保存在数据库
	 * 
	 * @return
	 */
	private int saveFiles() {
		Files files = new Files();
		
		return 0;
	}

	@Action(value = "htmlmanager", results = { @Result(name = "success", type = "json") })
	public String htmlmanager() {
		return SUCCESS;
	}
}
