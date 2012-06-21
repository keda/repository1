package com.msp.taobao.tracer.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.msp.core.util.Pager;
import com.msp.taobao.tracer.service.TaoTracerService;
import com.msp.taobao.tracer.vo.ClientInfo;
import com.msp.taobao.tracer.vo.TaoTracerMessage;

@Controller
@RequestMapping(value="/taobao/tracer/")
public class TaobaoTracerController {
	
	private Logger logger = Logger.getLogger(TaobaoTracerController.class);
	
	@Autowired
	private TaoTracerService taoTracerService;
	
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss S");
	
	@RequestMapping(value="clientInfo.do", method=RequestMethod.GET)
	public @ResponseBody ClientInfo queryClientInfo(HttpServletRequest req, HttpServletResponse res, Model model){
		
		logger.info("@@"+simpleDateFormat.format(new Date())+" : "+"获取用户基本信息");
		
		ClientInfo clientInfo = new ClientInfo();
		clientInfo.setClientIp(req.getLocalAddr());
		clientInfo.setClientPort(req.getLocalPort());
		
		return clientInfo;
	}
	
	//log info
	@RequestMapping(value="logTraceInfo.do", method=RequestMethod.POST)
	public void logTracerInfo(HttpServletRequest req, HttpServletResponse res, @ModelAttribute TaoTracerMessage tracerMsg, Model model) throws Exception{
		
		tracerMsg.setInfoDate(simpleDateFormat.format(new Date(tracerMsg.getTimestamp())));
		tracerMsg.setClientIp(req.getLocalAddr());
		tracerMsg.setClientPort(req.getLocalPort());
		
		taoTracerService.saveTracerMsg(tracerMsg);
		
	}
	
	@RequestMapping(value="query.do")
	public @ResponseBody Pager<TaoTracerMessage> queryTracerMsg(@ModelAttribute Pager<TaoTracerMessage> pager) {
		
		pager = taoTracerService.queryAllTracerMsg(pager);
		
		return pager;
		
	}
}
