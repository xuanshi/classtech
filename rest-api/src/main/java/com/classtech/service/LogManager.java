package com.classtech.service;

import com.classtech.model.Log;

public interface LogManager {

	Log insert(String schoolName, Short teacherNo, String year,
			String schoolClassName, Short studentNo, String awardType,
			String content);
}
