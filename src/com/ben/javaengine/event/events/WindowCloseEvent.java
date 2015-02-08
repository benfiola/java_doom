package com.ben.javaengine.event.events;

public class WindowCloseEvent extends AbstractEvent {

	public WindowCloseEvent(Class initiator, String message, Integer status) {
		super(initiator, message, status);
	}
	
}
