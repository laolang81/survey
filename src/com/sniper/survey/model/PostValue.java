package com.sniper.survey.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "mc_post_value")
public class PostValue extends BaseEntity{

	@Id
	// @GenericGenerator(name = "generator", strategy = "native", parameters = {
	// @Parameter(name = "property", value = "post") })
	// @GenericGenerator(name = "generator", strategy = GenerationType.AUTO)
	// @GeneratedValue(generator = "generator")
	@GeneratedValue(strategy = GenerationType.AUTO)
	// 不能使用子曾
	@Column(name = "pe_id")
	private Integer id;
	@Column(name = "pe_value", columnDefinition = "text")
	private String value;
	
	

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

	

	
	// 维护这post表这是个反向属性,一般被拥有方被定义,文章内容一般不执行什么操作,属于被拥有放
	@OneToOne(mappedBy = "postValue")
	private Post post;

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

}
