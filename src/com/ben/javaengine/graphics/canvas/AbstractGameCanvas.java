package com.ben.javaengine.graphics.canvas;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

import com.ben.javaengine.graphics.converter.AbstractLogicDataConverter;
import com.ben.javaengine.graphics.entitiies.AbstractGraphicData;
import com.ben.javaengine.logic.LogicMain;

abstract public class AbstractGameCanvas extends JPanel {
	private static final long serialVersionUID = 1L;
	protected List<AbstractGraphicData> data;
	protected AbstractLogicDataConverter converter;
	protected Color backgroundColor = Color.black;
	
	public AbstractGameCanvas() {
		super();
	}
	
	public void receiveData(LogicMain toConvert) {
		converter.process(toConvert);
		data = converter.convert(toConvert);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(backgroundColor);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		if(data != null) {
			for(AbstractGraphicData d : data) {
				d.draw(g);
			}
		}
	}
}
