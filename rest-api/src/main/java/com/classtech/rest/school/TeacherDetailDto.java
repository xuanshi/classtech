package com.classtech.rest.school;

import java.io.Serializable;
import java.util.List;

public class TeacherDetailDto implements Serializable {

	private static final long serialVersionUID = 1L;

	public PersonDto person;
	public Short teacherNo;
	public String schoolName;

	public SchoolClassDto managingClass;
	public List<CurriculumDto> curriculums;
	public List<ScheduleDto> schedules;
}
