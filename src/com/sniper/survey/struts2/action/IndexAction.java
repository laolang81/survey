package com.sniper.survey.struts2.action;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sniper.survey.model.AdminUser;
import com.sniper.survey.model.Post;
import com.sniper.survey.service.impl.AdminGroupService;
import com.sniper.survey.service.impl.AdminUserService;
import com.sniper.survey.service.impl.ChannelService;
import com.sniper.survey.service.impl.PostService;
import com.sniper.survey.service.impl.PostValueService;
import com.sniper.survey.service.impl.TagsService;

//加注解
@Controller
@Scope("prototype")
public class IndexAction extends BaseAction<AdminUser> {

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

	/**
	 * 用户列表
	 * @return
	 */
	public String index() {

		
		
		

		return "index";
	}

	// 跳过校验
	@SkipValidation
	public String add() {

		Post post = new Post();
		post.setId(1);
		postService.deleteEntiry(post);
		
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