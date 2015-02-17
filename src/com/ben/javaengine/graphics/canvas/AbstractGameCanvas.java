package com.ben.javaengine.graphics.canvas;

import java.awt.Graphics;

import javax.swing.JPanel;

import com.ben.javaengine.logic.LogicMain;

abstract public class AbstractGameCanvas extends JPanel {
	private static final long serialVersionUID = 1L;
	protected LogicMain data;
		
	public AbstractGameCanvas() {
		super();
	}
	
	public void receiveData(LogicMain data) {
		this.data = data;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
