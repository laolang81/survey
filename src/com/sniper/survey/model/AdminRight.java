package com.sniper.survey.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mc_admin_right")
public class AdminRight extends BaseEntity{

	private static final long serialVersionUID = 6907715430593817715L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ar_id")
	private Integer id;
	@Column(name = "ar_name")
	private String name;
	@Column(name = "ar_url")
	private String url;
	@Column(name = "ar_desc")
	private String desc;
	// 权限吗
	@Column(name = "ar_code", updatable = false)
	private long code;
	// 权限位
	@Column(name = "ar_pos", updatable = false)
	private int pos;
	// 公共资源所有问都可以访问
	@Column(name = "ar_is_public")
	private boolean isPublic;
	// 显示为menu
	@Column(name = "ar_is_menu")
	private boolean isMenu;
	
	@Column(name = "ar_sort")
	private int sort;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public boolean isMenu() {
		return isMenu;
	}

	public void setMenu(boolean isMenu) {
		this.isMenu = isMenu;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

}
