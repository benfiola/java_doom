package com.ben.javaengine.graphics;

import com.ben.javaengine.graphics.frame.EngineFrame;

public class GraphicsMain {
	private EngineFrame rootFrame;
	
	public GraphicsMain() {
		this.rootFrame = new EngineFrame("Test");
	}
	
	public boolean isDisposed() {
		return rootFrame.isDisplayable();
	}
}
