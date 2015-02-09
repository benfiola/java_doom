package com.ben.javaengine.controllers;

import org.apache.log4j.Logger;

import com.ben.javaengine.event.events.AbstractEvent;
import com.ben.javaengine.event.events.LogicRequestEvent;
import com.ben.javaengine.event.events.LogicResponseEvent;
import com.ben.javaengine.event.events.WindowCloseEvent;
import com.ben.javaengine.event.listeners.LogicRequestEventListener;
import com.ben.javaengine.event.listeners.WindowCloseEventListener;
import com.ben.javaengine.event.managers.EventManager;
import com.ben.javaengine.graphics.GraphicsMain;
import com.ben.javaengine.logic.LogicMain;
import com.ben.javaengine.logic.map.Map;
import com.ben.javaengine.map.generator.StaticMapGenerator;

/*
 * This is the controller class of our application.  It should initialize
 * all the components of our application and facilitate communication between
 * the components.
 */
public class Controller implements WindowCloseEventListener, LogicRequestEventListener {
	private static Logger LOG = Logger.getLogger(Controller.class);

	private GraphicsMain g;
	private LogicMain l;
	
	public Controller() {
		init();
	}

	public void init() {
		LOG.info("Initializing Controller");
		g = new GraphicsMain();
		l = new LogicMain();
		Map toUse = StaticMapGenerator.generateMap();
		EventManager.subscribe(WindowCloseEvent.class, this);
	}
	
	public void onWindowCloseEvent(WindowCloseEvent e) {
		quit(e.getStatus());
	}
	
	public void onLogicRequestEvent(LogicRequestEvent e) {
		LOG.info("Requesting logic from LogicMain");
		EventManager.publish(new LogicResponseEvent(this.getClass(), "Responding to logic data request.", AbstractEvent.STATUS_OK, l));
	}
	
	public void onLogicResponseEvent(LogicResponseEvent e) {
		LOG.info("Sending logic to Graphics Main");
		g.sendDataToCanvas(e.getData());
	}
	
	public void quit(Integer status) {
		LOG.info("Exiting with status code : " + status);
		System.exit(status);
	}
}
