package com.ben.javaengine.event.events;

public abstract class AbstractEvent {
	public static final Integer STATUS_OK = 0;
	public static final Integer STATUS_ERROR = 1;
	
	private Class initiator;
	private String message;
	private Integer status;
	
	public AbstractEvent(Class initiator, String message, Integer status) {
		this.initiator = initiator;
		this.message = message;
		this.status = status;
	}
	
	public Class getInitiator() {
		return this.initiator;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public Integer getStatus() {
		return this.status;
	}
}
