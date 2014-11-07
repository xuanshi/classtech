package com.classtech.model;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@AttributeOverride(name = "id", column = @Column(name = "person_id"))
public class Teacher extends BaseEntity {

	private Person person;

	private Short teacherNumber;

	private School school;

	private List<Curriculum> curriculums;

	private List<Log> logs;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "person_id")
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Column(name = "teacher_no")
	public Short getTeacherNumber() {
		return teacherNumber;
	}

	public void setTeacherNumber(Short teacherNumber) {
		this.teacherNumber = teacherNumber;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "school_id")
	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinTable(name = "teacher_curriculum", joinColumns = { @JoinColumn(name = "teacher_id", referencedColumnName = "person_id") }, inverseJoinColumns = { @JoinColumn(name = "curriculum_id") })
	public List<Curriculum> getCurriculums() {
		return curriculums;
	}

	public void setCurriculums(List<Curriculum> curriculums) {
		this.curriculums = curriculums;
	}

	@OneToMany(mappedBy = "logger")
	public List<Log> getLogs() {
		return logs;
	}

	public void setLogs(List<Log> logs) {
		this.logs = logs;
	}

}
