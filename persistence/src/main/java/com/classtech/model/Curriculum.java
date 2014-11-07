package com.classtech.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Curriculum extends BaseEntity {

	private String name;

	private String shortName;

	private CurriculumCategory cateogry;

	private Grade grade;

	private List<Schedule> schedules;

	private List<Teacher> teachers;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "short_name")
	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "curriculum_category_id")
	public CurriculumCategory getCateogry() {
		return cateogry;
	}

	public void setCateogry(CurriculumCategory cateogry) {
		this.cateogry = cateogry;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "grade_id")
	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	@OneToMany(mappedBy = "curriculum")
	public List<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "curriculums")
	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

}
