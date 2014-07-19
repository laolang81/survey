package com.sniper.survey.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 调查页面多个页面组成一个完整调查
 * 
 * @author laolang
 * 
 */
@Entity
@Table(name = "mc_survey_page")
public class SurveyPage extends BaseEntity {

	private static final long serialVersionUID = -4119371995632333205L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String title;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date ctime = new Date();
	private double sort = 1d;
	private String note;
	// 设置问卷到用户的关系
	@ManyToOne
	@JoinColumn(name = "survey_id")
	private Survey survey;

	// 问题列表
	@OneToMany(mappedBy = "page", fetch = FetchType.EAGER, cascade = { CascadeType.REMOVE })
	private List<SurveyQuestion> sq = new ArrayList<>();

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

	public double getSort() {
		return sort;
	}

	public void setSort(double sort) {
		this.sort = sort;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	public List<SurveyQuestion> getSq() {
		return sq;
	}

	public void setSq(List<SurveyQuestion> sq) {
		this.sq = sq;
	}

}
