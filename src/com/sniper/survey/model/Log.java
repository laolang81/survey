package com.sniper.survey.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 大数据处理 1、分表 动态表：每个月生成一个表 logs_2014_4,logs_2014_5用调度 create table logs_2014_4
 * if not exists like logs 一般都是提前一两个月生成表 2、分库
 * 
 * @author laolang
 * 
 */
@Entity
@Table(name = "mc_log")
public class Log {

	private static final long serialVersionUID = 8136382862893547457L;

	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	@Column(name = "ml_id")
	private String id;
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
	@Column(name = "ml_result_msg", columnDefinition = "text")
	private String resultMsg;

	@Column(name = "ml_time")
	private Date time = new Date();

	public String getId() {
		return id;
	}

	public void setId(String id) {
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
