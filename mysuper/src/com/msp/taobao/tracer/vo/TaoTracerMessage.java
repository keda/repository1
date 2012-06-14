package com.msp.taobao.tracer.vo;

import java.io.Serializable;

public class TaoTracerMessage implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long   id;
	private String infoName;
	private String infoUrl;
	private String infoDate;
	private String clientIp;
	private int    clientPort;
	private Long   timestamp;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getInfoName() {
		return infoName;
	}
	public void setInfoName(String infoName) {
		this.infoName = infoName;
	}
	public String getInfoUrl() {
		return infoUrl;
	}
	public void setInfoUrl(String infoUrl) {
		this.infoUrl = infoUrl;
	}
	public String getInfoDate() {
		return infoDate;
	}
	public void setInfoDate(String infoDate) {
		this.infoDate = infoDate;
	}
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public int getClientPort() {
		return clientPort;
	}
	public void setClientPort(int clientPort) {
		this.clientPort = clientPort;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
}
