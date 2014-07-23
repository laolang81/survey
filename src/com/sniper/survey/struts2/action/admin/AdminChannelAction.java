package com.sniper.survey.struts2.action.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.sniper.survey.model.Channel;
import com.sniper.survey.service.impl.ChannelService;
import com.sniper.survey.util.DataUtil;
import com.sniper.survey.util.PropertiesUtil;

@Namespace("/admin/admin-channel")
public class AdminChannelAction extends BaseAction<Channel> {

	private static final long serialVersionUID = 1L;

	@Resource
	ChannelService channelService;

	Map<Integer, String> channelTop = new HashMap<>();

	static Map<Integer, String> channelType = new HashMap<>();

	public Map<Integer, String> getChannelTop() {

		if (channelTop.isEmpty()) {
			channelTop.put(0, "顶级");
			List<Channel> channels = channelService.findAllEntitles();
			for (Channel c : channels) {
				channelTop.put(c.getId(), c.getName());
			}
		}

		return channelTop;
	}

	public static Map<Integer, String> getChannelType() {
		if (channelType.isEmpty()) {

			PropertiesUtil propertiesUtil = new PropertiesUtil(
					"properties/channelType.properties");
			channelType = propertiesUtil.getAllIntegerValue();

		}
		return channelType;
	}

	@Actions({ @Action(value = "index") })
	@SkipValidation
	public String index() {

		super.sniperUrl = "/admin-channel/delete";

		sniperMenuInt.put("MoveType", getChannelType());

		channelService.setOrder("id desc");
		channelService.pageList(getListRow());
		pageHtml = channelService.getPageHtml();
		list = channelService.getLists();

		return SUCCESS;
	}

	@Action(value = "save", results = {
			@Result(name = "input", location = "save.jsp"),
			@Result(name = "success", location = "save.jsp") })
	public String save() {
		if (getMethod().equalsIgnoreCase("post")) {
			channelService.saveOrUpdateEntiry(model);
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
			this.model = channelService.getEntity(this.model.getId());
		}

		if (getMethod().equalsIgnoreCase("post")) {

			channelService.saveOrUpdateEntiry(model);
		}
		return SUCCESS;
	}

	@Action(value = "delete", results = { @Result(name = "success", type = "json", params = {
			"root", "ajaxResult" }) })
	@SkipValidation
	public String delete() {
		// code 小于1表示有错误,大于0表示ok,==0表示未操作

		switch (menuType) {
		case "delete":
			if (channelService.deleteBatchEntityById(delid)) {
				ajaxResult.put("code", 1);
				ajaxResult.put("msg", "success");
			} else {
				ajaxResult.put("code", -1);
				ajaxResult.put("msg", "删除失败");
			}
			break;
		case "MoveType":
			try {
				channelService.batchFiledChange("showType",
						DataUtil.stringToInteger(getMenuValue()), delid);
				ajaxResult.put("code", 1);
				ajaxResult.put("msg", "success");
			} catch (Exception e) {
				ajaxResult.put("code", -1);
				ajaxResult.put("msg", e.getMessage());
			}
			break;

		default:
			break;
		}

		return SUCCESS;
	}

	/**
	 * delete公共调用的类
	 * 
	 * @return
	 */
	public void prepareDoDelete() {
		super.ajaxResultDelete();
	}

}
