package com.msp.taobao.tracer.dao;

import java.util.List;

import org.hibernate.criterion.Example;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.msp.core.hibernate.dao.GenericDAOImpl;
import com.msp.taobao.tracer.vo.TaoTracerMessage;

@Repository("taoTracerMsgDao")
@Scope("prototype")
public class TaoTracerMessageDAOImpl extends GenericDAOImpl<TaoTracerMessage, Long> implements
		TaoTracerMessageDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<TaoTracerMessage> queryTracerMsg(int page, int start, int limit) {
		
		return (List<TaoTracerMessage>) getSession()
				.createQuery("from TaoTracerMessage")
				.setFirstResult((page - 1) * limit)
				.setMaxResults(limit)
				.list();
	}
	

}
