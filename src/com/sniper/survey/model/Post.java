package com.sniper.survey.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

//表明类的实体名称默认类名
@Entity
@Table(name = "mc_post")
public class Post {

	/*
	 * @GenericGenerator(name = "generator", strategy = "native", parameters = {
	 * 
	 * @Parameter(value = "hibernate_seq", name = "sequence")})
	 */
	@Id
	// @GenericGenerator(name = "generator", strategy = "native")
	// @GeneratedValue(generator = "generator")
	@GeneratedValue(strategy = GenerationType.AUTO)
	// 跨平台自适应
	@Column(name = "pt_id")
	private Integer id;
	@Column(name = "pt_name")
	private String name;
	@Column(name = "pt_uid")
	private Integer uid;
	@Column(name = "pt_tags")
	private String tags;
	@Column(name = "pt_status")
	private Integer status;
	@Column(name = "pt_last_edit_ip")
	private String lastEditIp;
	@Column(name = "pt_order")
	private Integer order;
	@Column(name = "pt_source")
	private String source;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "pt_letime")
	private Date letime;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "pt_stime")
	private Date stime;
	@Column(name = "pt_attachment")
	private String attachment;
	@Column(name = "pt_audit_uid")
	private Integer auditUid;
	@Column(name = "pt_audit_uid_ip")
	private String auditUidIp;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "pt_audit_uid_time")
	private Date auditUidTime;
	@Column(name = "pt_mv_uid")
	private Integer mvUid;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "pt_mv_time")
	private Date mvTime;
	@Column(name = "pt_mv_ip")
	private Date mvIp;
	@Column(name = "pt_url")
	private String url;
	@Column(name = "pt_comment_count")
	// int 初始化为0,integer初始化为null
	private int commentCount;
	@Column(name = "pt_language")
	private Short language;

	// onetoone
	// @Column(name = "pe_pid")
	// @JoinColumn(name = "pt_id")

	// optional是否允许该字段为null,该属性应该根据数据库表的外键约束来确定,默认为true
	// @OneToOne(cascade = CascadeType.ALL)
	//
	// @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE })
	// @PrimaryKeyJoinColumn
	// @JoinColumn(name = "pt_pe_id", unique = true)
	// 默认为延迟加载,由于这里是主键关联，在住表删除时，次表没变化，是个bug
	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@PrimaryKeyJoinColumn
	private PostValue postValue;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	// referencedColumnName为每个表关联列，主键不用写
	@JoinTable(name = "mc_post_node", joinColumns = @JoinColumn(name = "pn_pid"), inverseJoinColumns = @JoinColumn(name = "pn_cid"))
	private Set<Channel> channels = new HashSet<>();

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

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getLastEditIp() {
		return lastEditIp;
	}

	public void setLastEditIp(String lastEditIp) {
		this.lastEditIp = lastEditIp;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Date getLetime() {
		return letime;
	}

	public void setLetime(Date letime) {
		this.letime = letime;
	}

	public Date getStime() {
		return stime;
	}

	public void setStime(Date stime) {
		this.stime = stime;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public Integer getAuditUid() {
		return auditUid;
	}

	public void setAuditUid(Integer auditUid) {
		this.auditUid = auditUid;
	}

	public String getAuditUidIp() {
		return auditUidIp;
	}

	public void setAuditUidIp(String auditUidIp) {
		this.auditUidIp = auditUidIp;
	}

	public Date getAuditUidTime() {
		return auditUidTime;
	}

	public void setAuditUidTime(Date auditUidTime) {
		this.auditUidTime = auditUidTime;
	}

	public Integer getMvUid() {
		return mvUid;
	}

	public void setMvUid(Integer mvUid) {
		this.mvUid = mvUid;
	}

	public Date getMvTime() {
		return mvTime;
	}

	public void setMvTime(Date mvTime) {
		this.mvTime = mvTime;
	}

	public Date getMvIp() {
		return mvIp;
	}

	public void setMvIp(Date mvIp) {
		this.mvIp = mvIp;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public Short getLanguage() {
		return language;
	}

	public void setLanguage(Short language) {
		this.language = language;
	}

	public PostValue getPostValue() {
		return postValue;
	}

	public void setPostValue(PostValue postValue) {
		this.postValue = postValue;
	}

	public Set<Channel> getChannels() {
		return channels;
	}

	public void setChannels(Set<Channel> channels) {
		this.channels = channels;
	}

	/**
	 * 配合查询使用的构造器
	 * 
	 * @param id
	 * @param name
	 */
	public Post(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Post() {

	}

}
