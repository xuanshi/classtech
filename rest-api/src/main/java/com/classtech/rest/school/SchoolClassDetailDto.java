package com.classtech.rest.school;

import java.io.Serializable;
import java.util.List;

public class SchoolClassDetailDto implements Serializable {

	private static final long serialVersionUID = 1L;

	public String name;
	public String year;
	public String schoolName;
	public TeacherDto manager;
	public List<StudentDto> students;
	public List<ScheduleDto> schedules;
}
