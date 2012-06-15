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
	
	public List<TaoTracerMessage> queryAllTracerMsg(int page, int start, int limit) {
//		return taoTracerMsgDao.findAll();
		return taoTracerMsgDao.queryTracerMsg(page, start, limit);

	}
	
	public void saveTracerMsg(TaoTracerMessage tracerMsg) {
	
		taoTracerMsgDao.save(tracerMsg);
		
		log.debug("保存一条记录[tracerMsg = "+tracerMsg.getId()+", timestamp = "+tracerMsg.getTimestamp()+"]");
	}
	
	public void saveTracerMsg2(TaoTracerMessage tracerMsg) {
		
		taoTracerMsgDao.save(tracerMsg);
		
		try {
			Thread.sleep(50000L);
			log.debug("正在沉睡5秒......");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		log.debug("醒来了,继续工作......");
		
		taoTracerMsgDao.findById(tracerMsg.getId(), false);
		
		tracerMsg.setInfoName("被修改");
		
		taoTracerMsgDao.save(tracerMsg);
	}
}
