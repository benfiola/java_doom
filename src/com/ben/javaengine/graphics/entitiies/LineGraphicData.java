package com.ben.javaengine.graphics.entitiies;

import java.awt.Color;
import java.awt.Graphics;

public class LineGraphicData extends AbstractGraphicData {
	private Integer x1;
	private Integer y1;
	private Integer x2;
	private Integer y2;
	
	public LineGraphicData(Integer x1, Integer y1, Integer x2, Integer y2, Color color) {
		super(color);
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.drawLine(x1, y1, x2, y2);
	}

}
