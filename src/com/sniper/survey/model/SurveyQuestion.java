package com.sniper.survey.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "mc_survey_question")
public class SurveyQuestion extends BaseEntity {

	private static final long serialVersionUID = -2897085975644254403L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@NotNull
	private String title;

	private int type;
	private String sort;
	private String validRules;

	@ManyToOne
	@JoinColumn(name = "page_id")
	private SurveyPage surveyPage;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "surveyQuestion", cascade = { CascadeType.REMOVE })
	private List<SurveyQuestionOption> options = new ArrayList<>();

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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getValidRules() {
		return validRules;
	}

	public void setValidRules(String validRules) {
		this.validRules = validRules;
	}

	public SurveyPage getSurveyPage() {
		return surveyPage;
	}

	public void setSurveyPage(SurveyPage surveyPage) {
		this.surveyPage = surveyPage;
	}

	public List<SurveyQuestionOption> getOptions() {
		return options;
	}

	public void setOptions(List<SurveyQuestionOption> options) {
		this.options = options;
	}

}
