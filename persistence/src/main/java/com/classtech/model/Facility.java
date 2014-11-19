package com.classtech.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Facility extends BaseEntity {

	private String name;

	private School school;

	private FacilityType facilityType;

	private SeatingChartType seatingChartType;

	private List<Schedule> schedules;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "school_id")
	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "facility_type_id")
	public FacilityType getFacilityType() {
		return facilityType;
	}

	public void setFacilityType(FacilityType facilityType) {
		this.facilityType = facilityType;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "seating_chart_type_id")
	public SeatingChartType getSeatingChartType() {
		return seatingChartType;
	}

	public void setSeatingChartType(SeatingChartType seatingChartType) {
		this.seatingChartType = seatingChartType;
	}

	@OneToMany(mappedBy = "facility")
	public List<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}
}
