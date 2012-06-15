package com.msp.taobao.tracer.dao;

import java.util.List;

import com.msp.core.hibernate.dao.GenericDAO;
import com.msp.taobao.tracer.vo.TaoTracerMessage;

public interface TaoTracerMessageDAO extends GenericDAO<TaoTracerMessage, Long>{
	
	public List<TaoTracerMessage> queryTracerMsg(int page, int start, int limit);
	
}
