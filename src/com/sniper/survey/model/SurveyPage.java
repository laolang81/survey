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
public class SurveyPage extends BaseEntity{

	private static final long serialVersionUID = -4119371995632333205L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ms_id")
	private Integer id;
	@Column(name = "ms_title")
	private String title;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ms_ctime", updatable = false)
	private Date ctime = new Date();
	@Column(name = "ms_description")
	private String description;
	// 设置问卷到用户的关系
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REMOVE })
	@JoinColumn(name = "ms_survey_id")
	private Survey survey;

	// 问题列表
	@OneToMany(mappedBy = "page")
	private List<SurveyQuestion> sq = new ArrayList<SurveyQuestion>();

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
