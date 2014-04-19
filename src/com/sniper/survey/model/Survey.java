package com.sniper.survey.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 调查属性
 * 
 * @author laolang
 * 
 */
@Entity
@Table(name = "mc_survey")
public class Survey extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ms_id")
	private Integer id;
	@Column(name = "ms_title")
	private String title;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ms_ctime")
	private Date ctime = new Date();
	@Column(name = "ms_description")
	private String description;
	// 设置问卷到用户的关系
	@ManyToOne
	@JoinColumn(name = "ms_uid")
	private AdminUser adminUser;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getCtime() {
		return ctime;
	}
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public AdminUser getAdminUser() {
		return adminUser;
	}
	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}
	
	
}
