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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "mc_link_groups")
public class LinkGroups extends BaseEntity {

	private static final long serialVersionUID = 8755939090888352186L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@NotNull
	private String name;
	@Column(columnDefinition = "text")
	private String note;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL }, mappedBy = "groups")
	private List<Links> links = new ArrayList<>();

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
