package com.ben.javaengine.graphics.entitiies;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class PlayerGraphicData extends AbstractGraphicData {
	private static final Integer DIAMETER = 10;
	private static final Color SIGHT_LINE_COLOR = Color.GRAY;
	private CircleGraphicData player;
	private List<LineGraphicData> sightLines;
	private Integer x;
	private Integer y;
	
	public PlayerGraphicData(Integer x, Integer y, Color color) {
		super(color);
		this.x = x;
		this.y = y;
		this.player = new CircleGraphicData(x, y, DIAMETER, DIAMETER, color);
		sightLines = new ArrayList<LineGraphicData>();
	}
	
	public void addSightLine(Integer x, Integer y) {
		sightLines.add(new LineGraphicData(this.x, this.y, x, y, SIGHT_LINE_COLOR));
	}
	
	@Override
	public void draw(Graphics g) {
		for(LineGraphicData sl : sightLines) {
			sl.draw(g);
		}
		player.draw(g);
	}
	
}
