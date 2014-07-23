package com.sniper.survey.struts2.action.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.sniper.survey.model.Channel;
import com.sniper.survey.model.Links;
import com.sniper.survey.service.impl.ChannelService;
import com.sniper.survey.service.impl.LinkGroupsService;
import com.sniper.survey.service.impl.LinksService;
import com.sniper.survey.util.DataUtil;

@Namespace("/admin/links")
public class LinksAction extends BaseAction<Links> {

	private static final long serialVersionUID = 1L;

	@Resource
	LinkGroupsService groupsService;

	@Resource
	ChannelService channelService;

	@Resource
	LinksService linksService;

	// 用户组列表
	private List<Channel> channels = new ArrayList<>();

	public List<Channel> getChannels() {
		if (channels.size() == 0) {
			channels = channelService.getChannelListByType(new Integer[] { 1 },
					true);
		}
		return channels;
	}

	@Actions({ @Action(value = "index") })
	public String index() {

		super.sniperUrl = "/links/delete";

		Map<Boolean, String> menu = new HashMap<>();
		menu.put(false, "否");
		menu.put(true, "是");
		sniperMenu.put("enabled", menu);

		linksService.setOrder("id desc");
		linksService.pageList(getListRow());
		pageHtml = linksService.getPageHtml();
		list = linksService.getLists();

		return SUCCESS;
	}

	@Action(value = "save")
	public String save() {
		if (getMethod().equalsIgnoreCase("post")) {

			linksService.saveOrUpdateEntiry(this.model);
		}
		return SUCCESS;
	}

	@Action(value = "update", results = {
			@Result(name = "success", location = "save.jsp"),
			@Result(name = "input", location = "save-input.jsp") })
	public String update() {

		if (null == this.model.getId()) {
			return ERROR;
		}

		if (getMethod().equalsIgnoreCase("post")) {
			linksService.saveOrUpdateEntiry(this.model);
		}
		if (getMethod().equalsIgnoreCase("get")) {
			this.model = linksService.getEntity(this.model.getId());
		}

		return SUCCESS;
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@Action(value = "delete", results = { @Result(name = "success", type = "json", params = {
			"root", "ajaxResult" }) })
	public String delete() {
		// code 小于1表示有错误,大于0表示ok,==0表示未操作

		//super.delete();

		switch (menuType) {
		case "delete":

			try {
				linksService.deleteBatchEntityById(delid);
				ajaxResult.put("code", 1);
				ajaxResult.put("msg", "success");
			} catch (Exception e) {
				ajaxResult.put("code", -1);
				ajaxResult.put("msg", "删除失败");
			}

			break;
		case "enabled":

			try {
				linksService.batchFiledChange("enabled",
						DataUtil.stringToBoolean(getMenuValue()), delid);
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
}
