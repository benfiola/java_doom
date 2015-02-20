package com.ben.javaengine.graphics.entitiies;

import java.awt.Color;
import java.awt.Graphics;

public class CircleGraphicData extends AbstractGraphicData {
	private Integer diameterHeight;
	private Integer diameterWidth;
	private Integer x;
	private Integer y;
	
	public CircleGraphicData(Integer x, Integer y, Integer diameterWidth, Integer diameterHeight, Color color) {
		super(color);
		this.x = x;
		this.y = y;
		this.diameterWidth = diameterWidth;
		this.diameterHeight = diameterHeight;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(x-(diameterWidth/2), y-(diameterHeight/2), diameterWidth, diameterHeight);
	}
	
	

}
