package com.sniper.survey.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 附件表通过union连接点和unionType连接类型区分
 * @author laolang
 *
 */
@Entity
@Table(name = "mc_attachment")
public class Attachment extends BaseEntity {

	private static final long serialVersionUID = -5602389531649784603L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ma_id")
	private Integer id;
	@Column(name = "ma_original_name")
	private String originalName;
	@Column(name = "ma_new_name")
	private String newName;
	@Column(name = "ma_uid")
	private int uid;
	@Column(name = "ma_create_time")
	private Date createTime = new Date();
	@Column(name = "ma_size")
	private int size;
	@Column(name = "ma_file_type")
	private String fileType;
	@Column(name = "ma_download")
	private int download = 0;
	// 连接点
	@Column(name = "ma_union")
	private int union;
	// 连接类型
	@Column(name = "ma_union_type")
	private String unionType;
	@Column(name = "ma_header")
	private boolean header = false;

	@Column(name = "ma_note")
	private String note;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;

	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public int getDownload() {
		return download;
	}

	public void setDownload(int download) {
		this.download = download;
	}

	

	public boolean isHeader() {
		return header;
	}

	public void setHeader(boolean header) {
		this.header = header;
	}

	

	public int getUnion() {
		return union;
	}

	public void setUnion(int union) {
		this.union = union;
	}

	public String getUnionType() {
		return unionType;
	}

	public void setUnionType(String unionType) {
		this.unionType = unionType;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
