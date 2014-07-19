package com.sniper.survey.struts2.action.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;

import com.sniper.survey.model.Files;
import com.sniper.survey.service.impl.FilesService;

@Namespace("/admin/file-upload")
public class FileUploadAction extends BaseAction<Files> {

	private static final long serialVersionUID = 1L;

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
	private String order;
	private String path;
	private String localUrl;

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

	public String getLocalUrl() {
		return localUrl;
	}

	public void setLocalUrl(String localUrl) {
		this.localUrl = localUrl;
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
		// return "http://www.yummyshandong.com/";
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
	public String upload() throws IOException {

		System.out.println(imgFile);
		System.out.println(imgFileFileName);
		System.out.println(imgFileContentType);
		if (imgFile == null) {
			return alert("文件上传失败");
		}
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

	@SuppressWarnings("unchecked")
	@Action(value = "htmlmanager", results = { @Result(name = "success", type = "json", params = {
			"root", "result" }) })
	public String htmlmanager() {

		// 根目录路径，可以指定绝对路径，比如 /var/www/attached/
		String rootPath = ServletActionContext.getServletContext().getRealPath(
				"/")
				+ "/attachments/";
		// 根目录URL，可以指定绝对路径，比如 http://www.yoursite.com/attached/
		String rootUrl = ServletActionContext.getRequest().getContextPath()
				+ "/attachments/";

		System.out.println("rootUrl:" + rootUrl);
		// 图片扩展名
		String[] fileTypes = new String[] { "gif", "jpg", "jpeg", "png", "bmp" };

		String dirName = getDir();
		if (dirName != null) {
			if (!Arrays.<String> asList(
					new String[] { "image", "flash", "media", "file" })
					.contains(dirName)) {
				return alert("Invalid Directory name.");
			}

			rootPath += dirName + "/";
			rootUrl += dirName + "/";
			File saveDirFile = new File(rootPath);
			if (!saveDirFile.exists()) {
				saveDirFile.mkdirs();
			}
		}
		// 根据path参数，设置各路径和URL
		String path = this.path != null ? this.path : "";
		String currentPath = rootPath + path;
		String currentUrl = rootUrl + path;
		String currentDirPath = path;
		String moveupDirPath = "";
		if (!"".equals(path)) {
			String str = currentDirPath.substring(0,
					currentDirPath.length() - 1);
			moveupDirPath = str.lastIndexOf("/") >= 0 ? str.substring(0,
					str.lastIndexOf("/") + 1) : "";
		}

		// 排序形式，name or size or type
		String order = this.order != null ? this.order.toLowerCase() : "name";

		// 不允许使用..移动到上一级目录
		if (path.indexOf("..") >= 0) {
			return alert("Access is not allowed.");
		}
		// 最后一个字符不是/
		if (!"".equals(path) && !path.endsWith("/")) {
			return alert("Parameter is not valid.");
		}
		// 目录不存在或不是目录
		System.out.println(currentPath);
		File currentPathFile = new File(currentPath);
		if (!currentPathFile.isDirectory()) {
			return alert("Directory does not exist.");
		}
		System.out.println(currentPathFile.listFiles().length);
		// 遍历目录取的文件信息
		List<Hashtable<String, Object>> fileList = new ArrayList<>();
		if (currentPathFile.listFiles() != null) {
			for (File file : currentPathFile.listFiles()) {
				Hashtable<String, Object> hash = new Hashtable<>();
				String fileName = file.getName();
				if (file.isDirectory()) {
					hash.put("is_dir", true);
					hash.put("has_file", (file.listFiles() != null));
					hash.put("filesize", 0L);
					hash.put("is_photo", false);
					hash.put("filetype", "");
				} else if (file.isFile()) {
					String fileExt = fileName.substring(
							fileName.lastIndexOf(".") + 1).toLowerCase();
					hash.put("is_dir", false);
					hash.put("has_file", false);
					hash.put("filesize", file.length());
					hash.put("is_photo", Arrays.<String> asList(fileTypes)
							.contains(fileExt));
					hash.put("filetype", fileExt);
				}
				hash.put("filename", fileName);
				hash.put("datetime",
						new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(file
								.lastModified()));
				fileList.add(hash);
			}
		}

		if ("size".equals(order)) {
			Collections.sort(fileList, new SizeComparator());
		} else if ("type".equals(order)) {
			Collections.sort(fileList, new TypeComparator());
		} else {
			Collections.sort(fileList, new NameComparator());
		}

		result.put("moveup_dir_path", moveupDirPath);
		result.put("current_dir_path", currentDirPath);
		result.put("current_url", currentUrl);
		result.put("total_count", fileList.size());
		result.put("file_list", fileList);

		return SUCCESS;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * 返回错误
	 * 
	 * @param msg
	 * @return
	 */
	private String alert(String msg) {
		result.put("error", 1);
		result.put("message", msg);
		return SUCCESS;
	}

	public class NameComparator implements Comparator {
		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable) a;
			Hashtable hashB = (Hashtable) b;
			if (((Boolean) hashA.get("is_dir"))
					&& !((Boolean) hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean) hashA.get("is_dir"))
					&& ((Boolean) hashB.get("is_dir"))) {
				return 1;
			} else {
				return ((String) hashA.get("filename"))
						.compareTo((String) hashB.get("filename"));
			}
		}
	}

	public class SizeComparator implements Comparator {
		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable) a;
			Hashtable hashB = (Hashtable) b;
			if (((Boolean) hashA.get("is_dir"))
					&& !((Boolean) hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean) hashA.get("is_dir"))
					&& ((Boolean) hashB.get("is_dir"))) {
				return 1;
			} else {
				if (((Long) hashA.get("filesize")) > ((Long) hashB
						.get("filesize"))) {
					return 1;
				} else if (((Long) hashA.get("filesize")) < ((Long) hashB
						.get("filesize"))) {
					return -1;
				} else {
					return 0;
				}
			}
		}
	}

	public class TypeComparator implements Comparator {
		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable) a;
			Hashtable hashB = (Hashtable) b;
			if (((Boolean) hashA.get("is_dir"))
					&& !((Boolean) hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean) hashA.get("is_dir"))
					&& ((Boolean) hashB.get("is_dir"))) {
				return 1;
			} else {
				return ((String) hashA.get("filetype"))
						.compareTo((String) hashB.get("filetype"));
			}
		}
	}

}
