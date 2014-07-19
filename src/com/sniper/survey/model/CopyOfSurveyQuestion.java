package com.sniper.survey.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "mc_survey_question")
public class CopyOfSurveyQuestion extends BaseEntity {

	private static final long serialVersionUID = -2897085975644254403L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@NotNull
	private String title;
	
	// 题型 0-8
	@Column(name = "msq_question_type")
	private int questionType;
	// 选项
	@Column(name = "msq_options")
	private String options;
	// 去要拆分
	// private String[] optionsArr;
	// 其他项
	@Column(name = "msq_other")
	private boolean other;
	// 其他样式 0无，1文本，2下拉
	@Column(name = "msq_other_style")
	private int otherStyle;
	// 其他项下拉样式
	@Column(name = "msq_other_select_options")
	private String otherSelectOptions;
	// private String[] otherSelectOptionArr;
	// 矩阵式行标题集
	@Column(name = "msq_matrix_row_ritles")
	private String matrixRowTitles;
	// private String[] matrixRowTitleArr;
	// 矩阵式行标题集
	@Column(name = "msq_matrix_col_title")
	private String matrixColTitle;
	// private String[] matrixColTitleArr;
	// 矩阵式下拉选项集
	@Column(name = "msq_matrix_select_options")
	private String matrixSelectOptions;
	// private String[] matrixSelectOptionsArr;

	@ManyToOne
	@JoinColumn(name = "msq_survey_id")
	private SurveyPage page;

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

	public int getQuestionType() {
		return questionType;
	}

	public void setQuestionType(int questionType) {
		this.questionType = questionType;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public boolean isOther() {
		return other;
	}

	public void setOther(boolean other) {
		this.other = other;
	}

	public int getOtherStyle() {
		return otherStyle;
	}

	public void setOtherStyle(int otherStyle) {
		this.otherStyle = otherStyle;
	}

	public String getOtherSelectOptions() {
		return otherSelectOptions;
	}

	public void setOtherSelectOptions(String otherSelectOptions) {
		this.otherSelectOptions = otherSelectOptions;
	}

	public String getMatrixRowTitles() {
		return matrixRowTitles;
	}

	public void setMatrixRowTitles(String matrixRowTitles) {
		this.matrixRowTitles = matrixRowTitles;
	}

	public String getMatrixColTitle() {
		return matrixColTitle;
	}

	public void setMatrixColTitle(String matrixColTitle) {
		this.matrixColTitle = matrixColTitle;
	}

	public String getMatrixSelectOptions() {
		return matrixSelectOptions;
	}

	public void setMatrixSelectOptions(String matrixSelectOptions) {
		this.matrixSelectOptions = matrixSelectOptions;
	}

	public SurveyPage getPage() {
		return page;
	}

	public void setPage(SurveyPage page) {
		this.page = page;
	}

}
