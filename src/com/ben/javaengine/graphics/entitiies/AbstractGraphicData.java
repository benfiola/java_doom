package com.ben.javaengine.graphics.entitiies;

import java.awt.Color;
import java.awt.Graphics;

abstract public class AbstractGraphicData {
	protected Color color;
	public AbstractGraphicData(Color color) {
		this.color = color;
	}
	
	abstract public void draw(Graphics g);
}
