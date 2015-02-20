package com.ben.javaengine.graphics.entitiies;

import java.awt.Color;
import java.awt.Graphics;

public class PlayerGraphicData extends AbstractGraphicData {
	private static final Integer DIAMETER = 10;
	private static final Color SIGHT_LINE_COLOR = Color.WHITE;
	private CircleGraphicData player;
	private LineGraphicData sightLine;
	
	
	public PlayerGraphicData(Integer x, Integer y, Integer sightLineX, Integer sightLineY, Color color) {
		super(color);
		this.player = new CircleGraphicData(x, y, DIAMETER, DIAMETER, color);
		this.sightLine = new LineGraphicData(x, y, sightLineX, sightLineY, SIGHT_LINE_COLOR);
	}
	
	@Override
	public void draw(Graphics g) {
		player.draw(g);
		sightLine.draw(g);
	}
	
}
