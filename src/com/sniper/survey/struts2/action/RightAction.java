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
	private Integer eid;

	

	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
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

	public String list() {
		this.allRight = adminRightService.findAllEntitles();
		return SUCCESS;
	}

	/**
	 * 返回ajax获取的数据
	 * 
	 * @return
	 */
	public String doAjaxList() {
		this.allRight = adminRightService.findAllEntitles();
		result.put("aaData", allRight);
		return SUCCESS;
	}

	public String doAdd() {

		return INPUT;
	}

	public String doSaveUpdate() {
		// 添加100次
		adminRightService.saveOrUpdate(model);
		return "list";
	}

	/**
	 * 编辑
	 * 
	 * @return
	 */
	public String doUpdate() {
		System.out.println(eid);
		 this.model = adminRightService.getEntity(eid);
		
		 System.out.println(model);

		return INPUT;
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	public String doDelete() {
		AdminRight right = new AdminRight();
		right.setId(eid);
		adminRightService.deleteEntiry(right);
		return "list";
	}

}
