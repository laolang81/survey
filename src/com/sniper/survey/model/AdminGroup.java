package com.sniper.survey.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mc_admin_groups")
public class AdminGroup {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ag_id")
	private Integer id;
	@Column(name = "ag_name", unique = true)
	private String name;
	@Column(name = "ag_value")
	private String value;
	@Column(name = "ag_fid")
	private Integer fid;

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

}
