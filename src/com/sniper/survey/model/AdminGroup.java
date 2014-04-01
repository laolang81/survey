package com.sniper.survey.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "mc_admin_group")
public class AdminGroup {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ag_id")
	private Integer id;
	@Column(name = "ag_name", unique = true)
	private String name;
	@Column(name = "ag_value")
	private String value;
	@Column(name = "ag_fid")
	private Integer fid;
	@Column(name = "ag_note")
	public String note;

	/*@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL})
	@JoinColumns(value = {@JoinColumn(name = "ag_values", referencedColumnName = "aa_groups")})
	//@JoinColumn(name = "ag_values", referencedColumnName = "aa_group")
	private List<AdminAccess> accesses = new ArrayList<AdminAccess>();*/
	
	
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	/*public List<AdminAccess> getAccesses() {
		return accesses;
	}

	public void setAccesses(List<AdminAccess> accesses) {
		this.accesses = accesses;
	}
	*/
}
