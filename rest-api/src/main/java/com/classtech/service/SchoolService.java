package com.classtech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.classtech.model.School;
import com.classtech.persistence.dao.SchoolDao;

@Service
public class SchoolService {

	@Autowired
	private SchoolDao schoolDao;

	public List<School> getAllSchools() {
		return schoolDao.findAll();
	}
}
