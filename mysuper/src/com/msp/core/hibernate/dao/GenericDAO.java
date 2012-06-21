package com.msp.core.hibernate.dao;

import java.io.Serializable;
import java.util.List;

import com.msp.core.util.Pager;

/**A super interface for common CRUD functionality
 * @author Administrator
 *
 * @param <T>
 * @param <ID>
 */
public interface GenericDAO<T, ID extends Serializable> {
	
	T findById(ID id, boolean lock);
	
	List<T> findAll();
	
	List<T> findByExample(T exampleInstance, String[] excludeProperty);
	
	List<T> findByExample(T exampleInstance);
	
	Pager<T> findByExample(T exampleInstance, Pager<T> pager);
	
	T save(T entity);
	
	void delete(T entity);

	long total();
}
