package com.classtech.rest.school;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.classtech.persistence.dao.TeacherDao;

@RestController
@RequestMapping(value = "/teachers")
public class TeacherController {

	@Autowired
	private TeacherDao teacherDao;
	@Autowired
	private TeacherDetailConverter converter;

	@RequestMapping(value = "{schoolName}/{teacherNo}", method = RequestMethod.GET)
	public TeacherDetailDto getTeacher(@PathVariable String schoolName,
			@PathVariable Short teacherNo) {
		return converter.toTeacherDetailDto(teacherDao.findByTeacherNo(
				schoolName, teacherNo));
	}
}
