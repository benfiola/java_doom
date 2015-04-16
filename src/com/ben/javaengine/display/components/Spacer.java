package com.ben.javaengine.display.components;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Spacer extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public Spacer() {
		super();
		setOpaque(false);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
