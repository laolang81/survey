package com.sniper.survey.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 前台用户表用户外来用户提交问卷
 * @author laolang
 *
 */
@Entity
@Table(name = "mc_web_user")
public class WebUser extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "wu_id")
	private Integer id;
	@Column(name = "wu_nick_name", unique = true)	
	private String nickName;
	@Column(name = "wu_password")
	private String password;
	@Column(name = "wu_email")
	private String email;
	@Column(name = "wu_ctime", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date ctime = new Date();
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCtime() {
		return ctime;
	}
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	
	
	
}
