package com.classtech.rest.school;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.classtech.model.School;
import com.classtech.model.SchoolClass;
import com.classtech.model.Year;

@Service
public class SchoolConverter {

	@Autowired
	private PersonConverter personConverter;
	@Autowired
	private SchedulesConverter schedulesConverter;

	public List<SchoolDto> toSchoolDtos(List<School> schools) {
		List<SchoolDto> schoolDtos = new ArrayList<SchoolDto>();
		for (School school : schools) {
			SchoolDto schoolDto = toSchoolDto(school);
			schoolDtos.add(schoolDto);
		}
		return schoolDtos;
	}

	public SchoolDto toSchoolDto(School school) {
		if (school == null) {
			return null;
		}
		SchoolDto dto = new SchoolDto();
		dto.name = school.getName();
		dto.years = new ArrayList<YearDto>();
		for (Year year : school.getYears()) {
			YearDto yearDto = toYearDto(year);
			dto.years.add(yearDto);
		}
		return dto;
	}

	public YearDto toYearDto(Year year) {
		if (year == null) {
			return null;
		}
		YearDto dto = new YearDto();
		dto.entranceYear = year.getEntranceYear();
		dto.classes = new ArrayList<SchoolClassDto>();
		for (SchoolClass schoolClass : year.getSchoolClasses()) {
			SchoolClassDto schoolClassDto = toSchoolClassDto(schoolClass);
			dto.classes.add(schoolClassDto);
		}
		return dto;
	}

	public SchoolClassDto toSchoolClassDto(SchoolClass schoolClass) {
		if (schoolClass == null) {
			return null;
		}
		SchoolClassDto dto = new SchoolClassDto();
		dto.name = schoolClass.getName();
		return dto;
	}

	public SchoolClassDetailDto toSchoolClassDetialDto(SchoolClass schoolClass) {
		if (schoolClass == null) {
			return null;
		}
		SchoolClassDetailDto dto = new SchoolClassDetailDto();
		dto.name = schoolClass.getName();
		dto.year = schoolClass.getYear().getEntranceYear();
		dto.schoolName = schoolClass.getYear().getSchool().getName();
		dto.manager = personConverter.toTeacherDto(schoolClass.getManager());
		dto.students = personConverter.toStudentDtos(schoolClass.getStudents());
		dto.schedules = schedulesConverter.toScheduleDtos(schoolClass.getSchedules());
		return dto;
	}

}
