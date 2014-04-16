package com.sniper.survey.struts2.action;

import java.util.Date;

import javax.annotation.Resource;

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
public class PostAction extends BaseAction<AdminUser> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

	public String list()
	{
		return SUCCESS;
	}
	
	public String add() {

		String securityCode = VerifyCode.getSecurityCode(5,
				VerifyCode.SecurityCodeLevel.Hard, false).toLowerCase();

		Tags tags = new Tags();
		tags.setName("标签二" + securityCode);
		tags.setOrder(new Date().getTime());
		tags.setUid(1);
		tags.setCtime(new Date());
		// tagsService.saveEntiry(tags);

		Channel channel = new Channel();
		channel.setName("栏目测试插入二" + securityCode);
		channel.setOrder(new Date().getTime());
		// channelService.saveEntiry(channel);

		Post post = new Post();
		post.setName("文章标题二");
		post.setUid(1);
		post.getChannels().add(channel);
		post.getTags().add(tags);

		PostValue postValue = new PostValue();
		postValue.setValue("文章内容二");
		post.setPostValue(postValue);
		// postValue.setId(post.getId());
		// postValueService.savePersist(postValue);
		postService.savePersist(post);
		System.out.println(postService.getEntity(post.getId()));

		// AdminGroup adminGroup = adminGroupService.getEntity(1);

		// System.out.println(adminGroup.getName());

	

		return INPUT;
	}
	
	
	public String update()
	{
		return INPUT;
	}
	
	public String saveUpdate()
	{
		return "edit";
	}

	// 跳过校验
	public String delete() {

		Post post = new Post();
		post.setId(1);
		postService.deleteEntiry(post);

		return SUCCESS;

	}

}