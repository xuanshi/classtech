package com.classtech.rest.school;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.classtech.model.Teacher;

@Service
public class TeacherDetailConverter {

	@Autowired
	private PersonConverter personConverter;
	@Autowired
	private SchoolConverter schoolConverter;
	@Autowired
	private SchedulesConverter schedulesConverter;

	public TeacherDetailDto toTeacherDetailDto(Teacher teacher) {
		if (teacher == null) {
			return null;
		}
		TeacherDetailDto dto = new TeacherDetailDto();
		dto.person = personConverter.toPersonDto(teacher.getPerson());
		dto.teacherNo = teacher.getTeacherNumber();
		dto.schoolName = teacher.getSchool().getName();

		dto.managingClass = schoolConverter.toSchoolClassDto(teacher.getManagingClass());
		dto.curriculums = schedulesConverter.toCurriculumDtos(teacher.getCurriculums());
		dto.schedules = schedulesConverter.toScheduleDtos(teacher.getSchedules());
		return dto;
	}
}
