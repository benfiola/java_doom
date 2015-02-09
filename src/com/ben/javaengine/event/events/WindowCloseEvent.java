package com.ben.javaengine.event.events;

import org.apache.log4j.Logger;

/*
 * This event signifies that a user is attempting to close the application window, 
 * allowing us to gracefully kill all running threads.
 */
public class WindowCloseEvent extends AbstractEvent {
	private static Logger LOG = Logger.getLogger(WindowCloseEvent.class);

	public WindowCloseEvent(Class initiator, String message, Integer status) {
		super(initiator, message, status);
	}
	
}
