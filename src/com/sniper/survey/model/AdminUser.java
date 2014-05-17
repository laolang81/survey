package com.sniper.survey.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

@Entity
@Table(name = "mc_admin_user")
public class AdminUser extends BaseEntity{

	private static final long serialVersionUID = -1749860151352757711L;
	@Id
	@Column(name = "au_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(name = "au_name", unique = true, updatable = false)
	private String name;
	@Column(name = "au_password")
	private String password;
	@Column(name = "au_nickName")
	private String nickName;
	@Column(name = "au_email")
	private String email;
	// 状态
	@Column(name = "au_status")
	private int status = 1;

	// 密码加密随机字符串
	@Column(name = "au_rand")
	private String rand;
	// 创建时间
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "au_ctime", updatable = false)
	private Date ctime = new Date();

	
	// 对应用户组
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "mc_admin_user_group", joinColumns = @JoinColumn(name = "uid"), inverseJoinColumns = @JoinColumn(name = "gid"))
	private Set<AdminGroup> adminGroup = new HashSet<>();

	// 权限总和
	@Transient
	private long[] rightSum;
	// 设置为超级管理员
	@Transient
	private boolean superAdmin = false;
	

		
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int i) {
		this.status = i;
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


	public Set<AdminGroup> getAdminGroup() {
		return adminGroup;
	}

	public void setAdminGroup(Set<AdminGroup> adminGroup) {
		this.adminGroup = adminGroup;
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
		
		//return true;
		return !((rightSum[pos] & code) == 0);

	}

}
