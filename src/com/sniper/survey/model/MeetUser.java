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

@Entity
@Table(name = "mc_meet_user")
public class MeetUser extends BaseEntity {

	static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String name;
	private int sex;
	private String nation;
	private String mobilePhone;
	private String shopInfo;
	private String moneyType;
	@Temporal(TemporalType.TIMESTAMP)
	private Date reportTime;
	private String carNum;
	private boolean carPeople;
	@Temporal(TemporalType.TIMESTAMP)
	private Date leaveTime;
	private boolean carLeavePeople;
	private String carLeaveNum;
	@Column(name = "other",columnDefinition = "text")
	private String other;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	private int bindUid;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getShopInfo() {
		return shopInfo;
	}

	public void setShopInfo(String shopInfo) {
		this.shopInfo = shopInfo;
	}

	public String getMoneyType() {
		return moneyType;
	}

	public void setMoneyType(String moneyType) {
		this.moneyType = moneyType;
	}

	public Date getReportTime() {
		return reportTime;
	}

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}

	public String getCarNum() {
		return carNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	public boolean isCarPeople() {
		return carPeople;
	}

	public void setCarPeople(boolean carPeople) {
		this.carPeople = carPeople;
	}

	public Date getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(Date leaveTime) {
		this.leaveTime = leaveTime;
	}

	public boolean isCarLeavePeople() {
		return carLeavePeople;
	}

	public void setCarLeavePeople(boolean carLeavePeople) {
		this.carLeavePeople = carLeavePeople;
	}

	public String getCarLeaveNum() {
		return carLeaveNum;
	}

	public void setCarLeaveNum(String carLeaveNum) {
		this.carLeaveNum = carLeaveNum;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getBindUid() {
		return bindUid;
	}

	public void setBindUid(int bindUid) {
		this.bindUid = bindUid;
	}

	@Override
	public Integer getId() {
		
		return this.id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;

	}

}
