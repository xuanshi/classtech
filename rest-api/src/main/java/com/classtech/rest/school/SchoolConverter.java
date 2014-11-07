package com.classtech.rest.school;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.classtech.model.School;
import com.classtech.model.SchoolClass;
import com.classtech.model.Year;

@Service
public class SchoolConverter {

	public List<SchoolDto> toSchoolDtos(List<School> schools) {
		List<SchoolDto> schoolDtos = new ArrayList<SchoolDto>();
		for (School school : schools) {
			SchoolDto schoolDto = toSchoolDto(school);
			schoolDtos.add(schoolDto);
		}
		return schoolDtos;
	}

	public SchoolDto toSchoolDto(School school) {
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
		SchoolClassDto dto = new SchoolClassDto();
		dto.name = schoolClass.getName();
		return dto;
	}
}
