package com.classtech.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import com.classtech.model.Person;
import com.classtech.persistence.dao.PersonDao;

@Repository
public class PersonDaoImpl extends GenericDaoImpl<Person> implements PersonDao {

}
