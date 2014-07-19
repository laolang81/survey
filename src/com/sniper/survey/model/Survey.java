package com.sniper.survey.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
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
@Table(name = "mc_survey", indexes = { @Index(columnList = "title", name = "title") })
public class Survey extends BaseEntity {

	private static final long serialVersionUID = -7334373665483843888L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String title;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date cTime = new Date();
	private int peopleNum = 0;
	private boolean locked = false;
	private boolean publiced = false;
	private int template = 0;
	private String note;
	// 设置问卷到用户的关系
	@ManyToOne
	@JoinColumn(name = "uid")
	private AdminUser adminUser;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE }, mappedBy = "survey")
	@OrderBy("sort asc")
	private Set<SurveyPage> surveyPages = new HashSet<>();

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

	public Date getcTime() {
		return cTime;
	}

	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}

	public int getPeopleNum() {
		return peopleNum;
	}

	public void setPeopleNum(int peopleNum) {
		this.peopleNum = peopleNum;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public boolean isPubliced() {
		return publiced;
	}

	public void setPubliced(boolean publiced) {
		this.publiced = publiced;
	}

	public int getTemplate() {
		return template;
	}

	public void setTemplate(int template) {
		this.template = template;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public AdminUser getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

	public Set<SurveyPage> getSurveyPages() {
		return surveyPages;
	}

	public void setSurveyPages(Set<SurveyPage> surveyPages) {
		this.surveyPages = surveyPages;
	}

}
