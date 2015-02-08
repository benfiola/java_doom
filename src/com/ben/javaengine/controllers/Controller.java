package com.ben.javaengine.controllers;

import com.ben.javaengine.event.events.WindowCloseEvent;
import com.ben.javaengine.event.listeners.WindowCloseEventListener;
import com.ben.javaengine.event.managers.EventManager;
import com.ben.javaengine.graphics.GraphicsMain;
import com.ben.javaengine.map.Map;
import com.ben.javaengine.map.generator.StaticMapGenerator;

public class Controller implements WindowCloseEventListener {
	private GraphicsMain g;
	
	private boolean running;
	private int status;
	
	public Controller() {
		init();
	}
	
	public void init() {
		g = new GraphicsMain();
		Map toUse = StaticMapGenerator.generateMap();
		running = true;
		status = 0;
		EventManager.subscribe(WindowCloseEvent.class, this);
	}
	
	public boolean isRunning() {
		return this.running;
	}
	
	public int getStatus() {
		return this.status;
	}

	public void onWindowCloseEvent(WindowCloseEvent e) {
		this.running = false;
		this.status = e.getStatus();
	}
}
