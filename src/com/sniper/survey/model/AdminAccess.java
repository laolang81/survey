package com.sniper.survey.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "mc_admin_accesss")
public class AdminAccess {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "aa_id")
	private Integer id;
	@Column(name = "aa_resource")
	private String resource;
	@Column(name = "aa_action")
	private String action;
	@Column(name = "aa_act")
	private String act;
	@Column(name = "aa_group")
	private Short group;
	@Column(name = "aa_note")
	private String note;

	// 对应用户组
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "au_group", nullable = false)
	private AdminGroup adminGroup;
	//mappedBy表示有那边维护，就写那边的类名
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "adminAccess")
	private Set<AdminResource> adminResources = new HashSet<AdminResource>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getAct() {
		return act;
	}

	public void setAct(String act) {
		this.act = act;
	}

	public Short getGroup() {
		return group;
	}

	public void setGroup(Short group) {
		this.group = group;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public AdminGroup getAdminGroup() {
		return adminGroup;
	}

	public void setAdminGroup(AdminGroup adminGroup) {
		this.adminGroup = adminGroup;
	}

	public Set<AdminResource> getAdminResources() {
		return adminResources;
	}

	public void setAdminResources(Set<AdminResource> adminResources) {
		this.adminResources = adminResources;
	}

}