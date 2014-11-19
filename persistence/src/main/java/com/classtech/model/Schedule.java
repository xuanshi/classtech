package com.classtech.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;
import org.joda.time.LocalTime;

@Entity
public class Schedule extends BaseEntity {

	private Character day;

	private LocalTime startTime;

	private LocalTime endTime;

	private SchoolClass schoolClass;

	private Curriculum curriculum;

	private Facility facility;

	public Character getDay() {
		return day;
	}

	public void setDay(Character day) {
		this.day = day;
	}

	@Column(name = "start_time")
	@Type(type = "org.joda.time.contrib.hibernate.PersistentLocalTimeAsTime")
	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	@Column(name = "end_time")
	@Type(type = "org.joda.time.contrib.hibernate.PersistentLocalTimeAsTime")
	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "class_id")
	public SchoolClass getSchoolClass() {
		return schoolClass;
	}

	public void setSchoolClass(SchoolClass schoolClass) {
		this.schoolClass = schoolClass;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "curriculum_id")
	public Curriculum getCurriculum() {
		return curriculum;
	}

	public void setCurriculum(Curriculum curriculum) {
		this.curriculum = curriculum;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "facility_id")
	public Facility getFacility() {
		return facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}

}
