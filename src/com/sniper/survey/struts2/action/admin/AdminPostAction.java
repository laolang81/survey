package com.sniper.survey.struts2.action.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sniper.survey.model.Channel;
import com.sniper.survey.model.Post;
import com.sniper.survey.service.impl.ChannelService;
import com.sniper.survey.service.impl.PostService;
import com.sniper.survey.util.DataUtil;
import com.sniper.survey.util.PropertiesUtil;

//加注解
@Controller
@Scope("prototype")
@Namespace("/admin/admin-post")
@ParentPackage("default")
public class AdminPostAction extends BaseAction<Post> {

	private static final long serialVersionUID = 1L;

	@Resource
	private PostService postService;

	@Resource
	ChannelService channelService;
	/**
	 * 文章栏目选择
	 */
	Map<Integer, String> channelTop = new HashMap<>();
	// 状态列表
	Map<Integer, String> statusList = new HashMap<>();
	// 保存被选中的之的列表
	List<Integer> channelChecked = new ArrayList<>();
	// 栏目提交之后得到的结果
	private Integer[] channelsPost;

	public Map<Integer, String> getChannelTop() {

		if (channelTop.isEmpty()) {

			List<Channel> channels = channelService.findAllEntitles();
			for (Channel c : channels) {
				channelTop.put(c.getId(), c.getName());
			}
		}

		return channelTop;
	}

	public Map<Integer, String> getStatusList() {
		if (statusList.isEmpty()) {
			PropertiesUtil propertiesUtil = new PropertiesUtil(
					"properties/postStatus.properties");
			statusList = propertiesUtil.getAllIntegerValue();
		}
		return statusList;
	}

	public List<Integer> getChannelChecked() {
		return channelChecked;
	}

	public Integer[] getChannelsPost() {
		return channelsPost;
	}

	public void setChannelsPost(Integer[] channelsPost) {
		this.channelsPost = channelsPost;
	}

	@Actions({ @Action(value = "index") })
	@SkipValidation
	public String index() {

		super.sniperUrl = "/admin-channel/delete";

		Map<Boolean, String> show = new HashMap<>();
		show.put(false, "否");
		show.put(true, "是");
		sniperMenu.put("Audit", show);

		sniperMenuInt.put("Status", getStatusList());

		sniperMenuInt.put("Channel", getChannelTop());

		postService.setOrder("id desc");
		postService.pageList(getListRow());
		pageHtml = postService.getPageHtml();
		list = postService.getLists();

		return SUCCESS;
	}

	@Action(value = "save", results = {
			@Result(name = "input", location = "save.jsp"),
			@Result(name = "success", location = "save.jsp") })
	public String save() {
		if (getMethod().equalsIgnoreCase("post")) {
			if (channelsPost.length != 0) {

				model.setChannels(new HashSet<>(channelService
						.getChannelListById(channelsPost)));
			}

			postService.saveOrUpdateEntiry(model);
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

		if (getMethod().equalsIgnoreCase("post")) {

			if (channelsPost.length != 0) {
				model.getChannels().clear();
				model.setChannels(new HashSet<>(channelService
						.getChannelListById(channelsPost)));
			}
			
			postService.saveOrUpdateEntiry(model);
		}

		if (getMethod().equalsIgnoreCase("get")) {
			this.model = postService.getAllEntity(this.model.getId());

		}

		for (Channel c : model.getChannels()) {
			channelChecked.add(c.getId());
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
			if (postService.deleteBatchEntityById(delid)) {
				ajaxResult.put("code", 1);
				ajaxResult.put("msg", "success");
			} else {
				ajaxResult.put("code", -1);
				ajaxResult.put("msg", "删除失败");
			}
			break;
		case "MoveType":
			try {
				postService.batchFiledChange("showType",
						DataUtil.stringToInteger(getMenuValue()), delid);
				ajaxResult.put("code", 1);
				ajaxResult.put("msg", "success");
			} catch (Exception e) {
				ajaxResult.put("code", -1);
				ajaxResult.put("msg", e.getMessage());
			}
			break;
		case "Audit":
			try {
				postService.batchFiledChange("status",
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