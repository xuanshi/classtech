package com.classtech.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Year extends BaseEntity {

	private String year;

	private School school;

	private List<SchoolClass> classes;

	@Column(name = "year")
	public String getEntranceYear() {
		return year;
	}

	public void setEntranceYear(String year) {
		this.year = year;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "school_id")
	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	@OneToMany(mappedBy = "year")
	public List<SchoolClass> getSchoolClasses() {
		return classes;
	}

	public void setSchoolClasses(List<SchoolClass> classes) {
		this.classes = classes;
	}

}
