package com.ben.javaengine.graphics;

import org.apache.log4j.Logger;

import com.ben.javaengine.event.events.AbstractEvent;
import com.ben.javaengine.event.events.LogicRequestEvent;
import com.ben.javaengine.event.managers.EventManager;
import com.ben.javaengine.graphics.canvas.AbstractGameCanvas;
import com.ben.javaengine.graphics.frame.EngineFrame;
import com.ben.javaengine.logic.LogicMain;

public class GraphicsMain {
	private static final Logger LOG = Logger.getLogger(GraphicsMain.class);
	private EngineFrame rootFrame;
	
	public GraphicsMain() {
		LOG.info("Initializing");
		this.rootFrame = new EngineFrame("Test");
		this.rootFrame.finishInitializing();
		new Thread(new Runnable() {
			public void run() {
				while(true) {
					EventManager.publish(new LogicRequestEvent(this.getClass(), "Requesting logic data", AbstractEvent.STATUS_OK));
					try{Thread.sleep(30);}catch(Exception e) {}
				}
			}
		}, "GraphicsMainThread").start();
	}
	
	public void sendDataToCanvas(LogicMain data) {
		for(AbstractGameCanvas canvas : rootFrame.getCanvases()) {
			canvas.receiveData(data);
		}
		this.rootFrame.getContentPane().repaint();
	}
}
