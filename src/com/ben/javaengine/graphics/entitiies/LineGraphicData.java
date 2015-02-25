package com.ben.javaengine.graphics.entitiies;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class LineGraphicData extends AbstractGraphicData {
	Point p1;
	Point p2;
	
	public LineGraphicData(Point p1, Point p2, Color color) {
		super(color);
		this.p1 = p1;
		this.p2 = p2;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.drawLine(p1.x, p1.y, p2.x, p2.y);
	}

}
