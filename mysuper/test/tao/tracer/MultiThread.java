package tao.tracer;

import com.msp.taobao.tracer.service.TaoTracerService;
import com.msp.taobao.tracer.vo.TaoTracerMessage;

public class MultiThread extends Thread {
	
	private TaoTracerService service;
	private int x;
	
	protected TaoTracerMessage getTracerMsg(int clientPort, String infoName) {
		TaoTracerMessage msg = new TaoTracerMessage();
		
		msg.setClientIp("127.0.0.1");
		msg.setClientPort(clientPort);
		msg.setInfoName(infoName);
		msg.setTimestamp(System.currentTimeMillis());
		
		return msg;
	}
	
	@Override
	public void run() {
		
		if(x == 0){
			System.out.println("##################"+this.getName()+"开始执行[saveTracerMsg]##################");
			
			getService().saveTracerMsg(getTracerMsg(9090, this.getName()));
			System.out.println("##################"+this.getName()+"结束执行[saveTracerMsg]##################");
		}
		if(x == 1){
			System.out.println("##################"+this.getName()+"开始执行[saveTracerMsg2]##################");
			getService().saveTracerMsg2(getTracerMsg(9090, this.getName()));
			System.out.println("##################"+this.getName()+"结束执行[saveTracerMsg2]##################");
		}
	}

	public TaoTracerService getService() {
		return service;
	}

	public MultiThread setService(TaoTracerService service, int x) {
		this.service = service;
		this.x = x;
		
		return this;
	}
	
}
