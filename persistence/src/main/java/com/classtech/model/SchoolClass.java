package com.classtech.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "class")
public class SchoolClass extends BaseEntity {

	private String name;

	private Year year;

	private Teacher manager;

	private List<Schedule> schedules;

	private List<Student> students;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "manager_id")
	public Teacher getManager() {
		return manager;
	}

	public void setManager(Teacher manager) {
		this.manager = manager;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "year_id")
	public Year getYear() {
		return year;
	}

	public void setYear(Year year) {
		this.year = year;
	}

	@OneToMany(mappedBy = "schoolClass")
	public List<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}

	@OneToMany(mappedBy = "schoolClass")
	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

}
