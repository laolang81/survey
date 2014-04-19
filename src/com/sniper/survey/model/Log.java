package com.sniper.survey.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mc_log")
public class Log extends BaseEntity {

	private static final long serialVersionUID = 8136382862893547457L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ml_id")
	private Integer id;
	// 操作人
	@Column(name = "ml_user")
	private String user;
	// 操作方法
	@Column(name = "ml_name")
	private String name;
	// 操作参数
	@Column(name = "ml_param", columnDefinition = "text")
	private String params;
	// 操作结果
	@Column(name = "ml_result")
	private String result;
	// 操作结果消息
	@Column(name = "ml_result_msg")
	private String resultMsg;

	@Column(name = "ml_time")
	private Date time = new Date();

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;

	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
