package com.ben.javaengine.display.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class CenteredTextLabel extends JPanel {
	private String value;
	private Color color;
	
	private static final long serialVersionUID = 1L;
	
	public CenteredTextLabel(String value, Color color) {
		super();
		this.value = value;
		this.color = color;
		setOpaque(false);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(color);
		Font font = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(font);
		FontMetrics fm = g.getFontMetrics();
		Rectangle2D stringBounds = fm.getStringBounds(value, g);
		double centerX = (getWidth() - stringBounds.getWidth())/2d;
		double centerY = (getHeight() - stringBounds.getHeight()/2d)/2d;
		
		g.drawString(value, (int) centerX, (int) centerY);
	}
}
