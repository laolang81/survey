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
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.apache.struts2.json.annotations.JSON;
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
	private File imgFile;
	// 上传的表单名称
	private String imgFileFileName;
	// 上传类型
	private String imgFileContentType;
	// 文件类型地址 image/flush,file
	private String dir = "images";

	/**
	 * 设置网页显示url
	 */
	private String webUrl;

	private Map<String, Object> result = new HashMap<>();

	public File getImgFile() {
		return imgFile;
	}

	public void setImgFile(File imgFile) {
		this.imgFile = imgFile;
	}

	public String getImgFileFileName() {
		return imgFileFileName;
	}

	public void setImgFileFileName(String imgFileFileName) {
		this.imgFileFileName = imgFileFileName;
	}

	public String getImgFileContentType() {
		return imgFileContentType;
	}

	public void setImgFileContentType(String imgFileContentType) {
		this.imgFileContentType = imgFileContentType;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	/**
	 * 返回域名部分加目录
	 * 
	 * @return
	 */
	public String getWebUrl() {
		if (null == webUrl) {
			HttpServletRequest request = ServletActionContext.getRequest();
			webUrl = request.getScheme() + "://" + request.getServerName();
			if (request.getServerPort() != 80) {
				webUrl += ":" + request.getServerPort();
			}
			webUrl += request.getContextPath();
		}
		return webUrl;
	}

	/**
	 * @return the result
	 */
	@JSON(serialize = false)
	public Map<String, Object> getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	@Action(value = "upload", results = {
			@Result(name = "success", type = "json", params = { "root",
					"result" }),
			@Result(name = "input", type = "json", params = { "root", "result" }) })
	@SkipValidation
	public String upload() throws IOException {

		// 上传文件
		String saveUrl = uploadFile(imgFile);
		result.put("error", 0);
		result.put("url", getWebUrl() + saveUrl);
		
		return SUCCESS;
	}

	private String uploadFile(File f) throws FileNotFoundException, IOException {

		// 项目根目录
		String rootDir = ServletActionContext.getServletContext().getRealPath(
				"/");
		// 保存的目录，是固定目录，只能更改rootpath目录移动文件
		String saveFileName = getSaveFilename();
		String savePath = getSaveDir();
		// 用户前台显示
		String saveUrl = savePath;
		
		// 组装真的url
		savePath = rootDir + savePath;
		// 检查文件夹是否存在
		File fileDir = new File(savePath);
		if (!fileDir.isDirectory()) {
			fileDir.mkdirs();
		}
		savePath += saveFileName;
		saveUrl += saveFileName;

		FileOutputStream out = new FileOutputStream(savePath);
		FileInputStream in = new FileInputStream(f);

		byte[] buffer = new byte[1024];
		int len = 0;

		while ((len = in.read(buffer)) != -1) {
			out.write(buffer, 0, len);
		}
		out.close();
		in.close();

		return saveUrl;
	}

	/**
	 * 获取保存的地址
	 * 
	 * @return
	 */
	private String getSaveDir() {

		String saveDir = "/attachments/" + getDir() + "/";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		saveDir += ymd + "/";
		return saveDir;
	}

	/**
	 * 生成文件问名称
	 * 
	 * @return
	 */
	private String getSaveFilename() {
		String fileExt = imgFileFileName.substring(
				imgFileFileName.lastIndexOf(".") + 1).toLowerCase();
		// if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String newFileName = df.format(new Date()) + "_"
				+ new Random().nextInt(10000) + "." + fileExt;
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
