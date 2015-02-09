package com.ben.javaengine.graphics;

import org.apache.log4j.Logger;

import com.ben.javaengine.graphics.canvas.EngineCanvas;
import com.ben.javaengine.graphics.frame.EngineFrame;
import com.ben.javaengine.logic.LogicMain;

public class GraphicsMain {
	private static final Logger LOG = Logger.getLogger(GraphicsMain.class);
	private EngineFrame rootFrame;
	
	public GraphicsMain() {
		this.rootFrame = new EngineFrame("Test");
	}
	
	public boolean isDisposed() {
		return rootFrame.isDisplayable();
	}
	
	public void sendDataToCanvas(LogicMain data) {
		((EngineCanvas) this.rootFrame.getComponent(0)).receiveData(data);
	}
}
