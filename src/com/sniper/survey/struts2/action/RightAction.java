package com.sniper.survey.struts2.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sniper.survey.model.AdminRight;
import com.sniper.survey.service.impl.AdminRightService;

@Controller
@Scope("prototype")
public class RightAction extends BaseAction<AdminRight> {

	private static final long serialVersionUID = 2799348891231755561L;

	private List<AdminRight> allRight;

	private Map<String, List<AdminRight>> result = new HashMap<>();

	@Resource
	private AdminRightService adminRightService;
	// 记录编辑id
	private Integer updateid;

	
	public Integer getUpdateid() {
		return updateid;
	}

	public void setUpdateid(Integer updateid) {
		this.updateid = updateid;
	}

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
		this.allRight = adminRightService.findAllEntitles();
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
		// 添加100次
		adminRightService.saveOrUpdate(model);
		return "list";
	}

	/**
	 * 编辑
	 * 
	 * @return
	 */
	public String update() {
		
		this.model = adminRightService.getEntity(updateid);
		
		System.out.println(model);

		return INPUT;
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	public String delete() {
		AdminRight right = new AdminRight();
		right.setId(updateid);
		adminRightService.deleteEntiry(right);
		return "list";
	}

}
