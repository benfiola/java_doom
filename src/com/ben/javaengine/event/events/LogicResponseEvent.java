package com.ben.javaengine.event.events;

import org.apache.log4j.Logger;

import com.ben.javaengine.logic.LogicMain;

public class LogicResponseEvent extends AbstractEvent {
	private static Logger LOG = Logger.getLogger(LogicResponseEvent.class);
	
	private LogicMain data;
	
	public LogicResponseEvent(Class initiator, String message, Integer status, LogicMain instance) {
		super(initiator, message, status);
		this.data = instance;
	}
	
	public LogicMain getData() {
		return this.data;
	}
}
