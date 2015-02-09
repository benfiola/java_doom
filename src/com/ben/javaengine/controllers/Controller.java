package com.ben.javaengine.controllers;

import org.apache.log4j.Logger;

import com.ben.javaengine.event.events.AbstractEvent;
import com.ben.javaengine.event.events.KeyPressEvent;
import com.ben.javaengine.event.events.KeyReleaseEvent;
import com.ben.javaengine.event.events.LogicRequestEvent;
import com.ben.javaengine.event.events.LogicResponseEvent;
import com.ben.javaengine.event.events.WindowCloseEvent;
import com.ben.javaengine.event.listeners.KeyPressEventListener;
import com.ben.javaengine.event.listeners.KeyReleaseEventListener;
import com.ben.javaengine.event.listeners.LogicRequestEventListener;
import com.ben.javaengine.event.listeners.LogicResponseEventListener;
import com.ben.javaengine.event.listeners.WindowCloseEventListener;
import com.ben.javaengine.event.managers.EventManager;
import com.ben.javaengine.graphics.GraphicsMain;
import com.ben.javaengine.logic.LogicMain;

/*
 * This is the controller class of our application.  It should initialize
 * all the components of our application and facilitate communication between
 * the components.
 */
public class Controller implements WindowCloseEventListener, LogicRequestEventListener, LogicResponseEventListener, KeyPressEventListener, KeyReleaseEventListener {
	private static Logger LOG = Logger.getLogger(Controller.class);

	private GraphicsMain g;
	private LogicMain l;
	
	public Controller() {
		init();
	}

	public void init() {
		LOG.info("Initializing");
		EventManager.subscribe(WindowCloseEvent.class, this);
		EventManager.subscribe(LogicRequestEvent.class, this);
		EventManager.subscribe(LogicResponseEvent.class, this);
		EventManager.subscribe(KeyPressEvent.class, this);
		EventManager.subscribe(KeyReleaseEvent.class, this);
		
		l = new LogicMain();
		g = new GraphicsMain();
	}
	
	public void onWindowCloseEvent(WindowCloseEvent e) {
		quit(e.getStatus());
	}
	
	public void onLogicRequestEvent(LogicRequestEvent e) {
		EventManager.publish(new LogicResponseEvent(this.getClass(), "Responding to logic data request.", AbstractEvent.STATUS_OK, l));
	}
	
	public void onLogicResponseEvent(LogicResponseEvent e) {
		if(g != null) {
			g.sendDataToCanvas(e.getData());
		}
	}
	
	public void quit(Integer status) {
		LOG.info("Exiting with status code : " + status);
		System.exit(status);
	}

	public void onKeyPressEvent(KeyPressEvent e) {
		if(l != null) {
			l.handleKeyPress(e.getKeyPressed());
		}
	}
	
	public void onKeyReleaseEvent(KeyReleaseEvent e) {
		if(l != null) {
			l.handleKeyRelease(e.getKeyReleased());
		}
	}
}
