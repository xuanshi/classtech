package com.classtech.rest.school;

import java.io.Serializable;

public class ScheduleDto implements Serializable {

	private static final long serialVersionUID = 1L;

	public SchoolClassDto schoolClass;
	public Character day;
	public String startTime;
	public String endTime;
	public CurriculumDto curriculum;
	public FacilityDto facility;
}
