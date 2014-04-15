package com.sniper.survey.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mc_admin_right")
public class AdminRight {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ar_id")
	private Integer id;
	@Column(name = "ar_name")
	private String name;
	@Column(name = "ar_url")
	private String url;
	@Column(name = "ar_desc")
	private String desc;
	@Column(name = "ar_code", updatable = false)
	private long code;
	@Column(name = "ar_pos", updatable = false)
	private int pos;
	
}
