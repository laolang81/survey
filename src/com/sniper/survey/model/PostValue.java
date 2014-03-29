package com.sniper.survey.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "mc_post_value")
public class PostValue {

	@Id
	//@GenericGenerator(name = "generator", strategy = "native", parameters = { @Parameter(name = "property", value = "post") })
	//@GenericGenerator(name = "generator", strategy = GenerationType.AUTO)
	//@GeneratedValue(generator = "generator")
	@GeneratedValue( strategy = GenerationType.AUTO)
	// 跨平台自适应
	@Column(name = "pe_id")
	private Integer id;
	@Column(name = "pe_value", columnDefinition = "text")
	private String value;

	// @Column(name = "pe_pid")
	// @Transient
	//@OneToOne(cascade = { CascadeType.ALL }, mappedBy = "postValue")
	@OneToOne
	@PrimaryKeyJoinColumn
	private Post post;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

}
