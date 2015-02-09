package com.ben.javaengine.event.events;

import org.apache.log4j.Logger;

/*
 * This event signifies that a user is attempting to close the application window, 
 * allowing us to gracefully kill all running threads.
 */
public class KeyPressEvent extends AbstractEvent {
	private static Logger LOG = Logger.getLogger(KeyPressEvent.class);
	
	private Integer keyPressed = -1;
	
	public KeyPressEvent(Class initiator, String message, Integer status, Integer keyCode) {
		super(initiator, message, status);
		this.keyPressed = keyCode;
	}
	
	public Integer getKeyPressed() {
		return this.keyPressed;
	}
}
