package com.sniper.survey.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "mc_survey_result")
public class SurveyResult extends BaseEntity {

	private static final long serialVersionUID = -945541332137035362L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private int optionId = 0;
	// 被投票id或者人
	private int surveyId = 0;
	// 投票人可为空
	private int uid = 0;
	// 得票人数
	private int num = 0;

	@ManyToOne(cascade = { CascadeType.REMOVE })
	@JoinColumn(name = "survey_id")
	private Survey survey;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getOptionId() {
		return optionId;
	}

	public void setOptionId(int optionId) {
		this.optionId = optionId;
	}

	public int getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(int surveyId) {
		this.surveyId = surveyId;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

}
