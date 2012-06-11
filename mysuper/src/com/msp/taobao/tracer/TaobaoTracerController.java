package com.msp.taobao.tracer;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.msp.taobao.tracer.vo.ClientInfo;
import com.msp.taobao.tracer.vo.TracerMessage;

@Controller
@RequestMapping(value="/taobao/tracer/")
public class TaobaoTracerController {
	
	private Logger logger = Logger.getLogger(TaobaoTracerController.class);
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss S");
	
	@RequestMapping(value="clientInfo.do", method=RequestMethod.GET)
	public @ResponseBody ClientInfo queryClientInfo(HttpServletRequest req, HttpServletResponse res, Model model){
		
//		simpleDateFormat.set
		
		logger.info("@@"+simpleDateFormat.format(new Date())+" : "+"获取用户基本信息");
		
		/*ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("clientIp", req.getLocalAddr());
		modelAndView.addObject("clientPort", req.getLocalPort());
		*/
		ClientInfo clientInfo = new ClientInfo();
		clientInfo.setClientIp(req.getLocalAddr());
		clientInfo.setClientPort(req.getLocalPort());
		
		return clientInfo;
	}
	
	//log info
	@RequestMapping(value="logTraceInfo.do", method=RequestMethod.GET)
	public void logTracerInfo(HttpServletRequest req, HttpServletResponse res, @ModelAttribute TracerMessage tracerMsg, Model model) throws Exception{
		req.setCharacterEncoding("UTF-8");
		
		logger.info("@@"+simpleDateFormat.format(new Date())+" : ");
		logger.info("@@infoName="+tracerMsg.getInfoName());
		logger.info("@@infoUrl="+tracerMsg.getInfoUrl());
		logger.info("@@timestamp="+tracerMsg.getTimestamp());
		logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	}
}
