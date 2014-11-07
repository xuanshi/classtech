package com.classtech.persistence.dao;

import java.util.List;

public interface GenericDao<T> {

	T findById(Long id, boolean lock);

	List<T> findAll();

	List<T> findByExample(T exampleInstance, String[] excludeProperty);

	T save(T entity);

	T saveOrUpdate(T entity);

	void delete(T entity);
}
