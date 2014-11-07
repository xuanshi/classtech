package com.classtech.model;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@AttributeOverride(name = "id", column = @Column(name = "person_id"))
public class Student extends BaseEntity {

	private Person person;

	private Short studentNumber;

	private Short seatNumber;

	private SchoolClass schoolClass;

	private List<Log> logs;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "person_id")
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Column(name = "student_no")
	public Short getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(Short studentNumber) {
		this.studentNumber = studentNumber;
	}

	@Column(name = "seat_no")
	public Short getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(Short seatNumber) {
		this.seatNumber = seatNumber;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "class_id")
	public SchoolClass getSchoolClass() {
		return schoolClass;
	}

	public void setSchoolClass(SchoolClass schoolClass) {
		this.schoolClass = schoolClass;
	}

	@OneToMany(mappedBy = "loggee")
	public List<Log> getLogs() {
		return logs;
	}

	public void setLogs(List<Log> logs) {
		this.logs = logs;
	}
}
