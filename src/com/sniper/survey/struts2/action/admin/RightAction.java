package com.sniper.survey.struts2.action.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sniper.survey.datasource.RightToken;
import com.sniper.survey.model.AdminRight;
import com.sniper.survey.service.impl.AdminRightService;

@Controller
@Scope("prototype")
@Namespace("/admin/right")
@ParentPackage("default")
//@InterceptorRef("loginInterceptor")
@Results({
	@Result(name="error", location="%{htmlPath}/error/error.jsp"),
	@Result(name="login", location="login", params={"namespace","/admin"}, type="redirectAction")
})
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
	
	@Action(value = "index", results = { @Result(name = "success", location = "list.jsp") })
	public String index()
	{
		System.out.println("list");
		String hql = "from AdminRight";
		this.allRight = adminRightService.page(hql, 0, 200);
		System.out.println(allRight);
		return SUCCESS;
	}
	/**
	 * 数据列表
	 * 
	 * @return
	 */
	@Action(value = "list", results = { @Result(name = "success", location = "list.jsp") })
	public String list() {
		System.out.println("list");
		String hql = "from AdminRight";
		this.allRight = adminRightService.page(hql, 0, 200);
		System.out.println(allRight);
		return SUCCESS;
	}

	/**
	 * 返回ajax获取的数据
	 * 
	 * @return
	 */
	@Action(value = "ajaxlist", results = { @Result(name = "success", type = "json") })
	public String ajaxList() {
		this.allRight = adminRightService.findAllEntitles();
		result.put("aaData", allRight);
		return SUCCESS;
	}

	@Action(value= "save")
	public String save() {
		System.out.println("save");
		return SUCCESS;
	}
	@Action(value= "savedata")
	public String saveData() {
		return SUCCESS;
	}
	/**
	 * 更新操作
	 * redirectAction
	 * @return
	 */
	@Action(value = "saveorupdate", results = { 
			@Result(name="add",location = "save", type = "redirectAction"),
			@Result(name="edit",location="update", type = "redirectAction")
			}
	)
	
	public String saveOrUpdate() {

		setUpdateid(model.getId());
		// spring多库分布实例
		// 绑定token到当前线程
		RightToken token = new RightToken();
		token.setRight(getModel());
		// 绑定令牌
		RightToken.bindToken(token);
		adminRightService.saveOrUpdate(model);

		return "add";
	}

	/**
	 * 编辑
	 * 
	 * @return
	 */
	@Action("update")
	public String update() {

		this.model = adminRightService.getEntity(this.model.getId());
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
