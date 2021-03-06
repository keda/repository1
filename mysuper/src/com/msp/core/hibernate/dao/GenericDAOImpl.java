package com.msp.core.hibernate.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;

import com.msp.core.util.Pager;

public abstract class GenericDAOImpl<T, ID extends Serializable> implements GenericDAO<T, ID> {

	private Class<T> entity;

	private Session session;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Logger log = Logger.getLogger(GenericDAOImpl.class);
	
	@SuppressWarnings("unchecked")
	public GenericDAOImpl() {
		this.entity = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public Session getSession() {
		
		if(session == null || !session.isOpen()) {
			session = sessionFactory.getCurrentSession();
			log.debug("session来自 ... [sessionFactory.getCurrentSession()]");
		}else{
			log.debug("session来自 ... [session]");
		}
	
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
	
	public Class<T> getEntity() {
		return entity;
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public T findById(ID id, boolean lock) {
		T entity;
		if(lock)
			entity = (T) getSession().get(getEntity(), id, LockMode.UPGRADE);
		else
			entity = (T) getSession().get(getEntity(), id);
		
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		
		return getCriteria().list();
	}
	
	@Override
	public long total() {
		
		return ((Long) getCriteria()
				.setProjection(Projections.rowCount()).list().get(0))
				.longValue();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByExample(T exampleInstance, String[] excludeProperty) {
		
		Criteria crit = getCriteria();
		Example example = Example.create(exampleInstance);
		
		for (String name : excludeProperty) {
			example.excludeProperty(name);
		}
		
		return crit.add(example).list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByExample(T exampleInstance) {
		
		Criteria crit = getCriteria();
		Example example = Example.create(exampleInstance);
		
		return crit.add(example).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findByCriteria(Criterion... criterion) {
		
		Criteria crit = getCriteria();
		
		for (Criterion c : criterion) {
			crit.add(c);
		}
		
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Pager<T> findByExample(T exampleInstance, Pager<T> pager) {
		
		pager.setTotal(total());
		
		Criteria crit = getCriteria();
		
		if(null != exampleInstance)
			crit.add(Example.create(exampleInstance));
		
		crit.setFirstResult(pager.getStart())
			.setMaxResults(pager.getLimit());
		
		pager.setData(crit.list());
		
		return pager;
	}
	
	protected Criteria getCriteria() {
		log.debug("###创建一个Criteria ["+getEntity().getName()+"]");
		return getSession().createCriteria(getEntity());
	}

	@Override
	public T save(T entity) {
		
		getSession().saveOrUpdate(entity);
		
		return entity;
	}

	@Override
	public void delete(T entity) {
		
		getSession().delete(entity);
	}
	
	public void flush() {
		getSession().flush();
	}
	
	public void clear() {
		getSession().clear();
	}
	
}
