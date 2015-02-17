package com.ben.javaengine.graphics.canvas;

import java.awt.Color;
import java.awt.Graphics;

import org.apache.log4j.Logger;

import com.ben.javaengine.logic.LogicMain;

public class EngineCanvas extends AbstractGameCanvas {
	private static final Logger LOG = Logger.getLogger(EngineCanvas.class);

	private static final long serialVersionUID = 1L;
	
	public EngineCanvas() {
		super();
	}
	
	private Double getCameraTransformX() {
		return 0.0;
	}
	
	private Double getCameraTransformY() {
		return 0.0;
	}
	
	private Double getCameraTransformZ() {
		return 0.0;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
	}
}
