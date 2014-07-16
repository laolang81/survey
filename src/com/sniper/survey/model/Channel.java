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
public class Channel extends BaseEntity {

	private static final long serialVersionUID = -331296954351916696L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer id;
	@Column(name = "name", unique = true)
	private String name;
	private Integer fid;
	private long sort;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "stime", updatable = false)
	private Date stime = new Date();
	@Temporal(TemporalType.TIMESTAMP)
	private Date letime = new Date();
	private Integer uid = 0;
	private boolean status = true;
	private String url;
	// 页面显示方式
	private int showType;
	private String attachement;
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

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public long getSort() {
		return sort;
	}
	
	public void setSort(long sort) {
		this.sort = sort;
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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getShowType() {
		return showType;
	}

	public void setShowType(int showType) {
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