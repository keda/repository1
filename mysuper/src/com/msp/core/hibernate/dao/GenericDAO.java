package com.msp.core.hibernate.dao;

import java.io.Serializable;
import java.util.List;

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
	
	T save(T entity);
	
	void delete(T entity);
}
