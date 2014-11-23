package com.classtech.rest.school;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.classtech.model.Person;
import com.classtech.model.Student;
import com.classtech.model.Teacher;

@Service
public class PersonConverter {

	public PersonDto toPersonDto(Person person) {
		PersonDto dto = new PersonDto();
		dto.firstName = person.getFirstName();
		dto.lastName = person.getLastName();
		dto.email = person.getEmail();
		dto.mobile = person.getMobile();
		return dto;
	}

	public TeacherDto toTeacherDto(Teacher teacher) {
		TeacherDto dto = new TeacherDto();
		dto.person = toPersonDto(teacher.getPerson());
		dto.teacherNo = teacher.getTeacherNumber();
		return dto;
	}

	public StudentDto toStudentDto(Student student) {
		StudentDto dto = new StudentDto();
		dto.person = toPersonDto(student.getPerson());
		dto.studentNo = student.getStudentNumber();
		dto.seatNo = student.getSeatNumber();
		return dto;
	}

	public List<StudentDto> toStudentDtos(List<Student> students) {
		List<StudentDto> dtos = new ArrayList<StudentDto>();
		for (Student student : students) {
			dtos.add(toStudentDto(student));
		}
		return dtos;
	}
}
