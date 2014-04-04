package com.sniper.survey.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "mc_tags")
public class Tags {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "mt_id")
	private Integer id;
	@Column(name = "mt_name", unique = true)
	private String name;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "mt_ctime")
	private Date ctime;
	@Column(name = "mt_uid")
	private Integer uid;
	@Column(name = "mt_order")
	private long order;

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

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public long getOrder() {
		return order;
	}

	public void setOrder(long order) {
		this.order = order;
	}

}