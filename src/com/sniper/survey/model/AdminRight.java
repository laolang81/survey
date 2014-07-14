package com.sniper.survey.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "mc_admin_right")
public class AdminRight extends BaseEntity {

	private static final long serialVersionUID = 6907715430593817715L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	@NotNull
	@Column(name = "name", nullable = false)
	private String name;
	@NotNull
	@Column(name = "url", nullable = false)
	private String url;
	private String note;
	// 权限吗
	@Column(name = "code", updatable = false)
	private long code;
	// 权限位
	@Column(name = "pos", updatable = false)
	private int pos;
	// 公共资源所有问都可以访问
	private boolean thePublic = false;
	// 显示为menu
	private boolean theMenu = false;

	private boolean theShow = false;

	private long sort;
	private Integer fid = 0;

	/* mappedBy写在那边那边不维护 */
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE }, mappedBy = "adminRight")
	private Set<AdminGroup> adminGroup = new HashSet<>();

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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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

	public boolean isThePublic() {
		return thePublic;
	}

	public void setThePublic(boolean thePublic) {
		this.thePublic = thePublic;
	}

	public boolean isTheMenu() {
		return theMenu;
	}

	public void setTheMenu(boolean theMenu) {
		this.theMenu = theMenu;
	}

	public boolean isTheShow() {
		return theShow;
	}

	public void setTheShow(boolean theShow) {
		this.theShow = theShow;
	}

	public long getSort() {
		return sort;
	}

	public void setSort(long sort) {
		this.sort = sort;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public Integer getFid() {
		return fid;
	}

	public Set<AdminGroup> getAdminGroup() {
		return adminGroup;
	}

	public void setAdminGroup(Set<AdminGroup> adminGroup) {
		this.adminGroup = adminGroup;
	}

}
