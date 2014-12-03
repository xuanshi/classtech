package com.classtech.service;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.classtech.model.AwardType;
import com.classtech.model.Log;
import com.classtech.model.Student;
import com.classtech.model.Teacher;
import com.classtech.persistence.dao.AwardTypeDao;
import com.classtech.persistence.dao.LogDao;
import com.classtech.persistence.dao.StudentDao;
import com.classtech.persistence.dao.TeacherDao;

@Service
public class LogManagerImpl implements LogManager {

	@Autowired
	private TeacherDao teacherDao;
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private AwardTypeDao awardTypeDao;
	@Autowired
	private LogDao logDao;

	@Override
	public Log insert(String schoolName, Short teacherNo, String year,
			String schoolClassName, Short studentNo, String awardTypeName,
			String content) {
		Teacher logger = teacherDao.findByTeacherNo(schoolName, teacherNo);
		if (logger == null) {
			throw new ClassTechServiceException("No logger (teacher) found.");
		}
		Student loggee = studentDao.findByStudentNo(schoolName, year,
				schoolClassName, studentNo);
		if (loggee == null) {
			throw new ClassTechServiceException("No logee (student) found.");
		}
		AwardType awardType = awardTypeDao.findByName(awardTypeName);
		if (awardType == null) {
			throw new ClassTechServiceException("No such awardTypeName.");
		}

		Log log = new Log();
		log.setTimestamp(new DateTime());
		log.setLogger(logger);
		log.setLoggee(loggee);
		log.setAwardType(awardType);
		log.setContent(content);
		return logDao.save(log);
	}

}
