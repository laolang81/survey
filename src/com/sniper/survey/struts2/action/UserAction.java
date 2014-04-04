package com.sniper.survey.struts2.action;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sniper.survey.model.AdminUser;
import com.sniper.survey.model.Channel;
import com.sniper.survey.model.Post;
import com.sniper.survey.model.PostValue;
import com.sniper.survey.model.Tags;
import com.sniper.survey.service.impl.AdminGroupService;
import com.sniper.survey.service.impl.AdminUserService;
import com.sniper.survey.service.impl.ChannelService;
import com.sniper.survey.service.impl.PostService;
import com.sniper.survey.service.impl.PostValueService;
import com.sniper.survey.service.impl.TagsService;
import com.sniper.survey.util.VerifyCode;

//加注解
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<AdminUser> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;

	@Resource
	private AdminUserService adminUserService;

	@Resource
	private AdminGroupService adminGroupService;

	@Resource
	private PostService postService;

	@Resource
	private TagsService tagsService;
	@Resource
	private ChannelService channelService;

	@Resource
	private PostValueService postValueService;

	public String index() {
		
		String securityCode = VerifyCode.getSecurityCode(5,
				VerifyCode.SecurityCodeLevel.Hard, false).toLowerCase();
		
		Tags tags = new Tags();
		tags.setName("标签一" + securityCode);
		tags.setOrder(new Date().getTime());
		tags.setUid(1);
		tags.setCtime(new Date());
		tagsService.saveEntiry(tags);
		
		Channel channel = new Channel();
		channel.setName("栏目测试插入" + securityCode);
		channel.setOrder(new Date().getTime());
		channelService.saveEntiry(channel);
		
	
		
		Post post = new Post();
		post.setName("文章标题");
		post.setUid(1);
		//post.setPostValue(postValue);
		post.getChannels().add(channel);
		post.getTags().add(tags);
		
		postService.saveEntiry(post);
		
		PostValue postValue = new PostValue();
		postValue.setPost(post);
		postValue.setValue("文章内容");
		postValueService.saveEntiry(postValue);
		
		System.out.println(postService.getEntity(post.getId()));
		

		// AdminGroup adminGroup = adminGroupService.getEntity(1);

		// System.out.println(adminGroup.getName());
		System.out.println(model);
		/*
		 * adminUser.setAdminGroup(adminGroup); //model.setId(1);
		 * adminUser.setName("admin"); adminUser.setNickName("原始管理员");
		 * adminUser.setCtime(new Date()); String rand = "1456";
		 * adminUser.setRand(rand); adminUser.setPassword(DataUtil.md5("admin" +
		 * rand)); adminUser.setStatus(1); System.out.println(adminUser);
		 * adminUserService.saveEntiry(adminUser);
		 * 
		 * System.out.println(adminUserService.getEntity(1));
		 */

		return "index";
	}

	// 跳过校验
	@SkipValidation
	public String add() {

		// String methodName = request.getMethod();
		// System.out.println();
		return "add";

	}

	public String save() {
		return null;

	}

	public String edit() {
		return null;

	}

	public String update() {
		return null;

	}

	public String delete() {
		return null;

	}

	@Override
	public void validate() {
		// 非空

		if (hasErrors()) {
			return;
		}
	}

}
