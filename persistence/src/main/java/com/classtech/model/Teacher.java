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
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@AttributeOverride(name = "id", column = @Column(name = "person_id", insertable = true))
public class Teacher extends BaseEntity {

	private Person person;

	private Short teacherNumber;

	private School school;

	private SchoolClass managingClass;

	private List<Curriculum> curriculums;

	private List<Log> logs;

	@MapsId
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "school_id")
	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	@OneToOne(mappedBy = "manager")
	public SchoolClass getManagingClass() {
		return managingClass;
	}

	public void setManagingClass(SchoolClass managingClass) {
		this.managingClass = managingClass;
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinTable(name = "teacher_curriculum", joinColumns = { @JoinColumn(name = "teacher_id", referencedColumnName = "person_id") }, inverseJoinColumns = { @JoinColumn(name = "curriculum_id", referencedColumnName = "id") })
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
