package com.sniper.survey.model;

import java.io.Serializable;

public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = -2788075811594663611L;

	public abstract Integer getId();

	public abstract void setId(Integer id);

}
