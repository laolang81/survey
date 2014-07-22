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
import org.apache.struts2.convention.annotation.Result;

import com.sniper.survey.model.Channel;
import com.sniper.survey.model.Post;
import com.sniper.survey.service.impl.ChannelService;
import com.sniper.survey.service.impl.PostService;
import com.sniper.survey.util.DataUtil;
import com.sniper.survey.util.PropertiesUtil;
import com.sniper.survey.util.ValidateUtil;

@Namespace("/admin/admin-post")
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
			List<Channel> channels = channelService.getChannelListByType(
					new Integer[] { 0 }, true);
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
	public String index() {

		super.sniperUrl = "/admin-channel/delete";

		Map<Boolean, String> show = new HashMap<>();
		show.put(false, "否");
		show.put(true, "是");
		sniperMenu.put("Audit", show);

		sniperMenuInt.put("Status", getStatusList());
		sniperMenuInt.put("Channel", getChannelTop());

		String ean = postService.getEntityAsName();
		String hqlwhere = "1=1";
		if (ValidateUtil.isValid(searchString.get("name"))) {
			hqlwhere += " and  " + ean + ".name  like '%"
					+ searchString.get("name") + "%'";
		}
		if (ValidateUtil.isValid(searchInteger.get("status"))) {
			hqlwhere += " and  " + ean + ".status  ="
					+ searchInteger.get("status");
		}
		if (ValidateUtil.isValid(searchInteger.get("channel"))) {
			hqlwhere += " and  " + "c.id =" + searchInteger.get("channel");
		}
		postService.setJoin(" LEFT JOIN " + ean + ".channels as c");
		postService.setDistinct(true);
		postService.setWhere(hqlwhere);
		postService.setOrder(ean + ".id desc");
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
			
			model.setLastEditIp(getIp());
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