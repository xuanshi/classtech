package com.classtech.persistence.dao;

public interface TypeDao<T> extends GenericDao<T> {

	T findByName(String name);
}
