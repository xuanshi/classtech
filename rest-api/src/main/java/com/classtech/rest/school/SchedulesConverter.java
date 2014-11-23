package com.classtech.rest.school;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.classtech.model.Curriculum;
import com.classtech.model.Facility;
import com.classtech.model.Schedule;

@Service
public class SchedulesConverter {

	@Autowired
	SchoolConverter schoolConverter;

	public List<ScheduleDto> toSchoolScheduleDtos(List<Schedule> schedules) {
		List<ScheduleDto> dtos = new ArrayList<ScheduleDto>();
		for (Schedule schedule : schedules) {
			dtos.add(toSchoolSchuedleDto(schedule));
		}
		return dtos;
	}

	public ScheduleDto toSchoolSchuedleDto(Schedule schedule) {
		ScheduleDto dto = new ScheduleDto();
		dto.schoolClass = schoolConverter.toSchoolClassDto(schedule
				.getSchoolClass());
		dto.day = schedule.getDay();
		dto.startTime = schedule.getStartTime().toString();
		dto.endTime = schedule.getEndTime().toString();
		dto.curriculum = toCurriculumDto(schedule.getCurriculum());
		dto.facility = toFacilityDto(schedule.getFacility());
		return dto;
	}

	public CurriculumDto toCurriculumDto(Curriculum curriculum) {
		CurriculumDto dto = new CurriculumDto();
		dto.name = curriculum.getName();
		dto.category = curriculum.getCategory().getName();
		dto.grade = curriculum.getGrade().getName();
		return dto;
	}

	public FacilityDto toFacilityDto(Facility facility) {
		FacilityDto dto = new FacilityDto();
		dto.name = facility.getName();
		dto.type = facility.getFacilityType().getName();
		dto.seatChartRow = facility.getSeatingChartType().getRow();
		dto.seatChartCol = facility.getSeatingChartType().getCol();
		return dto;
	}

}
