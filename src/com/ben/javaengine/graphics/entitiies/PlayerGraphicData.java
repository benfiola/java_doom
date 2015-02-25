package com.ben.javaengine.graphics.entitiies;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class PlayerGraphicData extends AbstractGraphicData {
	private static final Integer DIAMETER = 10;
	private static final Color SIGHT_LINE_COLOR = Color.GRAY;
	private CircleGraphicData player;
	private List<LineGraphicData> sightLines;
	private Point p;
	
	public PlayerGraphicData(Point p, List<Point> slPoints, Color color) {
		super(color);
		this.p = p;
		this.player = new CircleGraphicData(p, DIAMETER, DIAMETER, color);
		this.sightLines = new ArrayList<LineGraphicData>();
		for(Point slPoint : slPoints) {
			sightLines.add(new LineGraphicData(p, slPoint, SIGHT_LINE_COLOR));
		}
	}
	
	@Override
	public void draw(Graphics g) {
		for(LineGraphicData sl : sightLines) {
			sl.draw(g);
		}
		player.draw(g);
	}
	
}
