package com.classtech.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "award_type")
public class AwardType extends BaseType {

	private Boolean positive;

	public Boolean getPositive() {
		return positive;
	}

	public void setPositive(Boolean positive) {
		this.positive = positive;
	}

}
