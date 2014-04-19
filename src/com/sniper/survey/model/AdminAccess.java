package com.sniper.survey.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mc_admin_access")
public class AdminAccess extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "aa_id")
	private Integer id;
	@Column(name = "aa_resource")
	private String resource;
	@Column(name = "aa_action", columnDefinition = "text")
	private String action;
	@Column(name = "aa_act")
	private String act;

	@Column(name = "aa_note")
	private String note;

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "aa_group", referencedColumnName = "ag_note") private
	 * AdminGroup adminGroup;
	 */

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getAct() {
		return act;
	}

	public void setAct(String act) {
		this.act = act;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	/*
	 * public AdminGroup getAdminGroup() { return adminGroup; }
	 * 
	 * public void setAdminGroup(AdminGroup adminGroup) { this.adminGroup =
	 * adminGroup; }
	 */

}