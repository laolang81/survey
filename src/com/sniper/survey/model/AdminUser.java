package com.sniper.survey.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "mc_admin_users")
public class AdminUser {

	@Id
	@Column(name = "au_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(name = "au_name")
	private String name;
	@Column(name = "au_password")
	private String password;
	@Column(name = "au_nickName")
	private String nickName;
	// 状态
	@Column(name = "au_status")
	private Short status = 1;
	@Column(name = "au_group")
	private Short group;
	// 密码加密随机字符串
	@Column(name = "au_rand")
	private String rand;
	// 创建时间
	@Column(name = "au_ctime")
	private Date ctime;
	// 对应用户组
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "au_group", nullable = false)
	private AdminGroup adminGroup;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Short getGroup() {
		return group;
	}

	public void setGroup(Short group) {
		this.group = group;
	}

	public String getRand() {
		return rand;
	}

	public void setRand(String rand) {
		this.rand = rand;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public AdminGroup getAdminGroup() {
		return adminGroup;
	}

	public void setAdminGroup(AdminGroup adminGroup) {
		this.adminGroup = adminGroup;
	}

}
