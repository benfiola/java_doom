package com.ben.javaengine.graphics.entitiies;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

public class WallGraphicData extends AbstractGraphicData {
	private List<LineGraphicData> lines;
	
	public WallGraphicData(List<LineGraphicData> lines, Color color) {
		super(color);
		this.lines = lines;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		for(LineGraphicData line : lines) {
			line.draw(g);
		}
	}
	
	
}
