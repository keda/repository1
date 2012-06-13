package com.msp.taobao.tracer.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.msp.taobao.tracer.dao.TaoTracerMessageDAO;
import com.msp.taobao.tracer.vo.TaoTracerMessage;

@Service
@Scope("prototype")
public class TaoTracerService {
	
	private Logger log = Logger.getLogger(TaoTracerService.class);
	
	@Autowired
	private TaoTracerMessageDAO taoTracerMsgDao;
	
	public List<TaoTracerMessage> queryAllTracerMsg() {
		return taoTracerMsgDao.findAll();
	}
	
	public void saveTracerMsg(TaoTracerMessage tracerMsg) {
	
		taoTracerMsgDao.save(tracerMsg);
		
		log.debug("保存一条记录[tracerMsg = "+tracerMsg.getId()+", timestamp = "+tracerMsg.getTimestamp()+"]");
	}
}
