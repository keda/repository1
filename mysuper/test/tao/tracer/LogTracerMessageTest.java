package tao.tracer;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.msp.taobao.tracer.service.TaoTracerService;
import com.msp.taobao.tracer.vo.TaoTracerMessage;

public class LogTracerMessageTest {
	
	private static Logger log = Logger.getLogger(LogTracerMessageTest.class);
	
	public static void main(String[] args) {
		
		String configLocation = "applicationContext.xml";
		ApplicationContext ctx = new ClassPathXmlApplicationContext(configLocation);
		
		TaoTracerService taoTracerService = ctx.getBean(TaoTracerService.class);
		
//		List<TaoTracerMessage> l = taoTracerService.queryAllTracerMsg(1, 0, 1000);
		
//		System.out.println("#######################共有 " + l.size() + " 条消息");
		
		/*
		for (int i = 0, n = 2; i < n; i++) {
			System.out.println("<+++++++++++++启动一个线程["+i+"]++++++++++++>");
			
			MultiThread thread = new MultiThread();
			
			System.out.println("<+++++++++++++开始 ["+thread.getName()+"]++++++++++++>");
			
			thread.setService(ctx.getBean(TaoTracerService.class), i%2).start();
			
			
		}
		*/
//		List<TaoTracerMessage> l = taoTracerService.queryAllTracerMsg(); 
//		
//		System.out.println(l.size());
		/*
		log.debug("======================开启第一次保存动作=======================");
		TaoTracerMessage tracerMsg = new TaoTracerMessage();
		tracerMsg.setClientIp("127.0.0.1");
		tracerMsg.setClientPort(9090);
		tracerMsg.setInfoName("测试");
		tracerMsg.setTimestamp(System.currentTimeMillis());
		
		taoTracerService.saveTracerMsg(tracerMsg);
		log.debug("第一次保存成功: "+tracerMsg.getTimestamp());
		
		log.debug("======================开启第二次保存动作=======================");
		
		TaoTracerMessage tracerMsg2 = new TaoTracerMessage();
		BeanUtils.copyProperties(tracerMsg, tracerMsg2, new String[]{"id","timestamp"});
		tracerMsg2.setTimestamp(System.currentTimeMillis());
		
		taoTracerService.saveTracerMsg(tracerMsg2);
		log.debug("第二次保存成功: "+tracerMsg2.getTimestamp());
		
		log.debug("======================开启第三次保存并修改动作=======================");
		
		TaoTracerMessage tracerMsg3 = new TaoTracerMessage();
		BeanUtils.copyProperties(tracerMsg, tracerMsg3, new String[]{"id","timestamp"});
		tracerMsg3.setTimestamp(System.currentTimeMillis());
		
		taoTracerService.saveTracerMsg2(tracerMsg3);
		log.debug("第三次保存成功: "+tracerMsg3.getTimestamp());
		*/
	}

}
