package com.sniper.survey.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mc_admin_resource")
public class AdminResource {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ar_id")
	private Integer id;
	@Column(name = "ar_name")
	private String name;
	@Column(name = "ar_url", unique = true)
	private String url;
	@Column(name = "ar_fid")
	private Integer fid;
	@Column(name = "ar_order")
	private Long order;
	@Column(name = "ar_is_url")
	private Short isUrl;
	@Column(name = "ar_note")
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public Long getOrder() {
		return order;
	}

	public void setOrder(Long order) {
		this.order = order;
	}

	public Short getIsUrl() {
		return isUrl;
	}

	public void setIsUrl(Short isUrl) {
		this.isUrl = isUrl;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
