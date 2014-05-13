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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "mc_admin_group")
public class AdminGroup extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ag_id")
	private Integer id;
	@Column(name = "ag_name", unique = true)
	private String name;
	@Column(name = "ag_value", unique = true)
	private String value;
	@Column(name = "ag_fid")
	private Integer fid;
	@Column(name = "ag_note")
	public String note;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "mc_admin_group_right", joinColumns = @JoinColumn(name = "gid"), inverseJoinColumns = @JoinColumn(name = "rid"))
	private Set<AdminRight> adminRight = new HashSet<>();


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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Set<AdminRight> getAdminRight() {
		return adminRight;
	}

	public void setAdminRight(Set<AdminRight> adminRight) {
		this.adminRight = adminRight;
	}

	public AdminGroup(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	public AdminGroup() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	
}
