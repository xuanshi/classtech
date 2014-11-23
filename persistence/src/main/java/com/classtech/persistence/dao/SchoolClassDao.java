package com.classtech.persistence.dao;

import com.classtech.model.SchoolClass;

public interface SchoolClassDao extends GenericDao<SchoolClass> {

	SchoolClass findByName(String schoolName, String year,
			String schoolClassName);
}
