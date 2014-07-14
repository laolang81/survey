package com.sniper.survey.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "mc_admin_user")
public class AdminUser extends BaseEntity {

	private static final long serialVersionUID = -1749860151352757711L;
	
	@Id
	@Column(name = "au_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@NotNull
	@Column(name = "name", unique = true, updatable = false)
	private String name;
	@Column(name = "password", nullable = true)
	private String password;
	@Column(name = "nickName")
	private String nickName;
	
	private String email;
	// 用户是否启用
	@Column(name = "ENABLES")
	private boolean enables;
	// 用户过期时间戳
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "USERNAME_EXPIRED")
	private Date usernameExpired;
	// 密码过期时间
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PASSWORD_EXPIRED")
	private Date passwordExpired;
	// 用户是否锁定
	@Column(name = "LOCKED")
	private boolean locked;

	// 密码加密随机字符串
	@Column(name = "rand")
	private String rand;
	// 创建时间
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ctime", updatable = false)
	private Date ctime = new Date();

	// 对应用户组
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "mc_admin_user_group", joinColumns = @JoinColumn(name = "uid"), inverseJoinColumns = @JoinColumn(name = "gid"))
	private List<AdminGroup> adminGroup = new ArrayList<>();

	// 权限总和
	@Transient
	private long[] rightSum;
	// 设置为超级管理员
	@Transient
	private boolean superAdmin = false;

	@Transient
	private boolean auth;

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
		if (null == nickName || "".equals(nickName)) {
			return name;
		}
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnables() {
		return enables;
	}

	public void setEnables(boolean enables) {
		this.enables = enables;
	}

	public Date getUsernameExpired() {
		return usernameExpired;
	}

	public void setUsernameExpired(Date usernameExpired) {
		this.usernameExpired = usernameExpired;
	}

	public Date getPasswordExpired() {
		return passwordExpired;
	}

	public void setPasswordExpired(Date passwordExpired) {
		this.passwordExpired = passwordExpired;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
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

	public long[] getRightSum() {
		return rightSum;
	}

	public void setRightSum(long[] rightSum) {
		this.rightSum = rightSum;
	}

	public boolean isSuperAdmin() {
		return superAdmin;
	}

	public void setSuperAdmin(boolean superAdmin) {
		this.superAdmin = superAdmin;
	}

	public List<AdminGroup> getAdminGroup() {
		return adminGroup;
	}

	public void setAdminGroup(List<AdminGroup> adminGroup) {
		this.adminGroup = adminGroup;
	}

	public boolean isAuth() {
		return auth;
	}

	public void setAuth(boolean auth) {
		this.auth = auth;
	}

	/**
	 * 计算用户权限总和
	 */
	public void calucateRightSum() {
		int pos = 0;
		long code = 0;
		for (AdminGroup g : adminGroup) {
			// 判断超级管理员
			if ("administrator".equals(g.getValue())) {
				this.superAdmin = true;
				adminGroup = null;
				return;
			}
			for (AdminRight r : g.getAdminRight()) {
				pos = r.getPos();
				code = r.getCode();
				rightSum[pos] = rightSum[pos] | code;
			}
		}
		// 释放起源在计算权限总和之后,adminGroup就是没用了
		adminGroup = null;
	}

	/**
	 * 判断用户是否具有指定权限
	 * 
	 * @param right
	 * @return
	 */
	public boolean hasRight(AdminRight right) {
		int pos = right.getPos();
		long code = right.getCode();

		return !((rightSum[pos] & code) == 0);
	}

}
