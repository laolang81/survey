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
@Table(name = "mc_channel")
public class Channel extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cl_id")
	public Integer id;
	@Column(name = "cl_name", unique = true)
	private String name;
	@Column(name = "cl_fid")
	private String fid;
	@Column(name = "cl_order")
	private Long order;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "cl_stime", updatable = false)
	private Date stime;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "cl_letime")
	private Date letime;
	@Column(name = "cl_uid", updatable = false)
	private Integer uid;
	@Column(name = "cl_status")
	private Short status;
	@Column(name = "cl_url")
	private String url;
	// 页面显示方式
	@Column(name = "cl_show_type")
	private Short showType;
	@Column(name = "cl_attachement")
	private String attachement;
	@Column(name = "cl_note")
	private String note;

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

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public Long getOrder() {
		return order;
	}

	public void setOrder(Long order) {
		this.order = order;
	}

	public Date getStime() {
		return stime;
	}

	public void setStime(Date stime) {
		this.stime = stime;
	}

	public Date getLetime() {
		return letime;
	}

	public void setLetime(Date letime) {
		this.letime = letime;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Short getShowType() {
		return showType;
	}

	public void setShowType(Short showType) {
		this.showType = showType;
	}

	public String getAttachement() {
		return attachement;
	}

	public void setAttachement(String attachement) {
		this.attachement = attachement;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}