package com.msp.core.hibernate.util;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class HibernateUtil {

	private Logger log = Logger.getLogger(HibernateUtil.class);
	
	private final SessionFactory sessionFactory;
	
	HibernateUtil(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public Session openSession() {
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();
		
		return session;
	}
	
	public void closeSession(Session session) {
		
		if(session == null) return;
		
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			if(tx.isActive()) {
				tx.commit();
			}
		}catch(Exception e) {
			tx.rollback();
			log.error("发生错误,事物已回滚......");
			e.printStackTrace();
		}finally{
			session.close();
		}
		
	}
	
	public void rollBack(Session session) {
		try{
			session.getTransaction().rollback();
		}catch(Exception e){
			log.error("事物回滚失败......");
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
}
