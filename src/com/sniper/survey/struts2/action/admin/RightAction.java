package com.sniper.survey.struts2.action.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sniper.survey.datasource.RightToken;
import com.sniper.survey.model.AdminRight;
import com.sniper.survey.service.impl.AdminRightService;

@Controller
@Scope("prototype")
public class RightAction extends BaseAction<AdminRight> {

	private static final long serialVersionUID = 2799348891231755561L;

	private List<AdminRight> allRight;
	/**
	 * ajax返回列表
	 */
	private Map<String, List<AdminRight>> result = new HashMap<>();

	@Resource
	private AdminRightService adminRightService;
	
	public List<AdminRight> getAllRight() {
		return allRight;
	}

	public void setAllRight(List<AdminRight> allRight) {
		this.allRight = allRight;
	}

	@JSON(serialize = false)
	public Map<String, List<AdminRight>> getResult() {
		return result;
	}

	public void setResult(Map<String, List<AdminRight>> result) {
		this.result = result;
	}
	/**
	 * 数据列表
	 * @return
	 */
	public String list() {
		String hql = "from Right";
		this.allRight = adminRightService.page(hql, 0, 2);
		return SUCCESS;
	}

	/**
	 * 返回ajax获取的数据
	 * 
	 * @return
	 */
	public String ajaxList() {
		this.allRight = adminRightService.findAllEntitles();
		result.put("aaData", allRight);
		return SUCCESS;
	}

	public String save() {

		return INPUT;
	}
	/**
	 * 更新操作
	 * @return
	 */
	public String saveOrUpdate() {
		
		setUpdateid(model.getId());
		//spring多库分布实例
		// 绑定token到当前线程
		RightToken token = new RightToken();
		token.setRight(getModel());
		//绑定令牌
		RightToken.bindToken(token);
		adminRightService.saveOrUpdate(model);
		
		return "update";
	}

	/**
	 * 编辑
	 * 
	 * @return
	 */
	public String update() {
		
		this.model = adminRightService.getEntity(getUpdateid());
		return INPUT;
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	public String delete() {
		AdminRight right = new AdminRight();
		right.setId(getUpdateid());
		adminRightService.deleteEntiry(right);
		return "list";
	}

	

}
