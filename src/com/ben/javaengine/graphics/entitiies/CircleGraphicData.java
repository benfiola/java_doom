package com.ben.javaengine.graphics.entitiies;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class CircleGraphicData extends AbstractGraphicData {
	private Integer diameterHeight;
	private Integer diameterWidth;
	private Point p;
	
	public CircleGraphicData(Point p, Integer diameterWidth, Integer diameterHeight, Color color) {
		super(color);
		this.p = p;
		this.diameterWidth = diameterWidth;
		this.diameterHeight = diameterHeight;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(p.x-(diameterWidth/2), p.y-(diameterHeight/2), diameterWidth, diameterHeight);
	}
	
	

}
