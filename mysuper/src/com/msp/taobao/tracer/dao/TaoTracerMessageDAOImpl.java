package com.msp.taobao.tracer.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.msp.core.hibernate.dao.GenericDAOImpl;
import com.msp.taobao.tracer.vo.TaoTracerMessage;

@Repository("taoTracerMsgDao")
@Scope("prototype")
public class TaoTracerMessageDAOImpl extends GenericDAOImpl<TaoTracerMessage, Long> implements
		TaoTracerMessageDAO {


}
