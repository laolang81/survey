package com.sniper.survey.struts2.action.admin;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.sniper.survey.config.MeetUserData;
import com.sniper.survey.model.MeetUser;
import com.sniper.survey.service.impl.MeetUserService;
import com.sniper.survey.util.PathUtil;
import com.sniper.survey.util.PoiExeclExportUtil;
import com.sniper.survey.util.ValidateUtil;

@Namespace("/admin/meet-user")
public class MeetUserAction extends BaseAction<MeetUser> {

	private static final long serialVersionUID = 1L;
	//导出文件地址
	private String filePath;
	
	public String getFilePath() {
		return filePath;
	}
	
	
	private Map<Integer, String> sexs = new HashMap<>();
	{
		sexs = MeetUserData.getSex();
	}
	@Resource
	MeetUserService meetUserService;

	public Map<Integer, String> getSexs() {
		return sexs;
	}

	/**
	 * 读取记录数
	 */
	private List<MeetUser> meetUsers;

	/**
	 * @return the meetUsers
	 */
	public List<MeetUser> getMeetUsers() {
		return meetUsers;
	}

	@Actions({ @Action(value = "index", results = { @Result(name = "export", location = "index", type = "redirectAction", params = {
			"namespace", "/admin/file-download","filePath","${filePath}","contentType","application/vnd.ms-exce" }) }) })
	@SkipValidation
	public String index() {

		sniperUrl = "/meet-user/delete";
		// 表别名
		// String ean = meetUserService.getEntityAsName();
		String hqlwhere = "1=1";

		if (searchInteger.get("sex") != null) {
			Integer sex = searchInteger.get("sex");
			hqlwhere += " and sex =  " + sex;
		}

		String fieldName = "reportTime";
		if (null != searchInteger.get("timeType")) {
			Integer timeType = searchInteger.get("timeType");
			switch (timeType) {

			case 1:
				fieldName = "leaveTime";
				break;
			case 2:
				fieldName = "createTime";
				break;
			}
		}

		// 时间格式转换
		DateFormat dateFormat = new SimpleDateFormat(getSystemConfig().get(
				"dateFormatShort"));

		if (searchDate.get("stime") != null) {
			Date stime = searchDate.get("stime");
			hqlwhere += " and " + fieldName + " >  '"
					+ dateFormat.format(stime).replace(" ", "") + "'";
		}

		if (searchDate.get("etime") != null) {
			Date etime = searchDate.get("etime");
			hqlwhere += " and " + fieldName + " <  '"
					+ dateFormat.format(etime).replace(" ", "") + "'";
		}

		if (searchString.get("name") != null
				&& !searchString.get("name").isEmpty()) {
			String name = searchString.get("name");
			hqlwhere += " and name like '%" + name + "%'";
		}
		if (ValidateUtil.isValid(searchString.get("submit"))) {
			String submit = searchString.get("submit");
			if (submit.equals("export")) {
				// 没有limit的限制读取
				list = meetUserService.findListByHql();
				if (list.size() > 0) {
					PoiExeclExportUtil<MeetUser> execlExportUtil = new PoiExeclExportUtil<>();
					execlExportUtil.setList(list);
					execlExportUtil.intoData();
					PathUtil pathUtil = new PathUtil();
					String path;
					try {
						path = pathUtil.getWebRoot();
						this.filePath = path + "data/人员采集名单.xlsx";
						execlExportUtil.write(this.filePath);
					} catch (IllegalAccessException | IOException e) {
						e.printStackTrace();
					}
					return "export";
				}

			}
		}

		meetUserService.setWhere(hqlwhere);
		meetUserService.setOrder("id desc");
		meetUserService.pageList(getListRow());
		pageHtml = meetUserService.getPageHtml();
		list = meetUserService.getLists();

		return SUCCESS;
	}

	/**
	 * 用户列表导出
	 * 
	 * @return
	 */
	@Actions({ @Action(value = "export") })
	public String export() {

		return SUCCESS;
	}

	@Action(value = "save", results = {
			@Result(name = "input", location = "save.jsp"),
			@Result(name = "success", location = "save.jsp") })
	public String save() {
		if (getMethod().equalsIgnoreCase("post")) {
			System.out.println(model);
			meetUserService.saveOrUpdateEntiry(model);
		}
		return SUCCESS;
	}

	@Action(value = "saveData", results = {
			@Result(name = "input", location = "save", type = "redirectAction"),
			@Result(name = "success", location = "save", type = "redirectAction") })
	public String saveData() {
		if (getMethod().equalsIgnoreCase("post")) {
			meetUserService.saveOrUpdateEntiry(model);
		}
		return SUCCESS;
	}

	@Action(value = "update", results = {
			@Result(name = "input", location = "save.jsp"),
			@Result(name = "success", location = "save.jsp") })
	@SkipValidation
	public String update() {

		if (model.getId() == 0) {
			return ERROR;
		}

		if (getMethod().equalsIgnoreCase("get")) {
			this.model = meetUserService.getEntity(this.model.getId());
		}

		if (getMethod().equalsIgnoreCase("post")) {
			System.out.println(model);
			meetUserService.saveOrUpdateEntiry(model);
		}
		return SUCCESS;
	}

	@Action(value = "updateData", results = {
			@Result(name = "input", location = "update", type = "redirectAction", params = {
					"id", "${id}" }),
			@Result(name = "success", location = "update", type = "redirectAction", params = {
					"id", "${id}" }) })
	public String updateData() {
		if (getMethod().equalsIgnoreCase("post")) {
			meetUserService.saveOrUpdateEntiry(model);
		}
		return SUCCESS;
	}

	@Action(value = "delete", results = { @Result(name = "success", type = "json", params = {
			"root", "ajaxResult" }) })
	@SkipValidation
	@Override
	public String delete() {
		// code 小于1表示有错误,大于0表示ok,==0表示未操作

		super.delete();

		switch (menuType) {
		case "delete":

			break;
		case "IsShow":

			break;

		default:
			break;
		}

		return SUCCESS;
	}

}
