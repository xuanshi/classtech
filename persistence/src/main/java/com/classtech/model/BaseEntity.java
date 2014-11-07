package com.classtech.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {

	protected Long id;

	// TODO use defined database sequence
	// @GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(insertable = false, updatable = false, unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
