package com.ben.javaengine.event.events;

import org.apache.log4j.Logger;

public class LogicRequestEvent extends AbstractEvent {
	private static Logger LOG = Logger.getLogger(LogicRequestEvent.class);
	
	public LogicRequestEvent(Class initiator, String message, Integer status) {
		super(initiator, message, status);
	}
}
