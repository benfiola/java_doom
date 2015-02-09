package com.ben.javaengine.event.events;

import org.apache.log4j.Logger;

/*
 * This event signifies that a user is attempting to close the application window, 
 * allowing us to gracefully kill all running threads.
 */
public class KeyReleaseEvent extends AbstractEvent {
	private static Logger LOG = Logger.getLogger(KeyReleaseEvent.class);
	
	private Integer keyReleased = -1;
	
	public KeyReleaseEvent(Class initiator, String message, Integer status, Integer keyCode) {
		super(initiator, message, status);
		this.keyReleased = keyCode;
	}
	
	public Integer getKeyReleased() {
		return this.keyReleased;
	}
}
