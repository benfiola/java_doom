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
	
	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
	}
}
