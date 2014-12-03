package com.classtech.persistence.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;

import com.classtech.persistence.dao.GenericDao;

public abstract class GenericDaoImpl<T> implements GenericDao<T> {

	private Class<T> persistentClass;

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public GenericDaoImpl() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	protected abstract class HibernateWrapper<S> {
		S execute() {
			S result = null;
			Session session = sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			try {
				result = runStatement(session);
				tx.commit();
			} catch (RuntimeException e) {
				if (tx != null && tx.isActive()) {
					tx.rollback();
				}
			}
			return result;
		}

		abstract S runStatement(Session session);
	}

	@Override
	public T findById(final Long id, final boolean lock) {
		return new HibernateWrapper<T>() {
			@SuppressWarnings("unchecked")
			@Override
			T runStatement(Session session) {
				T entity;
				if (lock) {
					entity = (T) session.load(getPersistentClass(), id,
							LockOptions.UPGRADE);
				} else {
					entity = (T) session.load(getPersistentClass(), id);
				}
				return entity;
			}
		}.execute();
	}

	@Override
	public List<T> findAll() {
		return findByCriteria();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findByExample(final T exampleInstance,
			final String[] excludeProperty) {
		return new HibernateWrapper<List<T>>() {
			List<T> runStatement(Session session) {
				Criteria crit = session.createCriteria(getPersistentClass());
				Example example = Example.create(exampleInstance);
				for (String exclude : excludeProperty) {
					example.excludeProperty(exclude);
				}
				crit.add(example);
				return crit.list();
			}
		}.execute();
	}

	@Override
	public T save(final T entity) {
		return new HibernateWrapper<T>() {
			@Override
			T runStatement(Session session) {
				session.save(entity);
				session.update(entity);
				return entity;
			}
		}.execute();
	}

	@Override
	public T saveOrUpdate(final T entity) {
		return new HibernateWrapper<T>() {
			@Override
			T runStatement(Session session) {
				session.saveOrUpdate(entity);
				return entity;
			}
		}.execute();
	}

	@Override
	public void delete(final T entity) {
		new HibernateWrapper<T>() {
			@Override
			T runStatement(Session session) {
				session.delete(entity);
				return entity;
			}
		}.execute();
	}

	@Override
	public void delete(final Long id) {
		new HibernateWrapper<T>() {
			@SuppressWarnings("unchecked")
			@Override
			T runStatement(Session session) {
				T entity = (T) session.load(getPersistentClass(), id);
				if (entity != null) {
					session.delete(entity);
				}
				return entity;
			}
		}.execute();
	}

	public void flush() {
		sessionFactory.getCurrentSession().flush();
	}

	public void clear() {
		sessionFactory.getCurrentSession().clear();
	}

	/**
	 * Use this inside subclasses as a convenience method.
	 */
	@SuppressWarnings("unchecked")
	protected List<T> findByCriteria(final Criterion... criterion) {
		return new HibernateWrapper<List<T>>() {
			@Override
			List<T> runStatement(Session session) {
				Criteria criteria = session
						.createCriteria(getPersistentClass());
				for (Criterion c : criterion) {
					criteria.add(c);
				}
				return criteria.list();
			}
		}.execute();
	}

	@SuppressWarnings("unchecked")
	protected T findUniqueByCriteria(final Criterion... criterion) {
		return new HibernateWrapper<T>() {
			@Override
			T runStatement(Session session) {
				Criteria criteria = session
						.createCriteria(getPersistentClass());
				for (Criterion c : criterion) {
					criteria.add(c);
				}
				return (T) criteria.uniqueResult();
			}
		}.execute();
	}

	protected List<T> findByCriteria(final DetachedCriteria criteria) {
		return new HibernateWrapper<List<T>>() {
			@SuppressWarnings("unchecked")
			@Override
			List<T> runStatement(Session session) {
				return criteria.getExecutableCriteria(session).list();
			}
		}.execute();
	}

	protected T findUniqueByCriteria(final DetachedCriteria criteria) {
		return new HibernateWrapper<T>() {
			@SuppressWarnings("unchecked")
			@Override
			T runStatement(Session session) {
				return (T) criteria.getExecutableCriteria(session).uniqueResult();
			}
		}.execute();
	}
}
