package com.ben.javaengine.graphics.converter;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.ben.javaengine.graphics.entitiies.AbstractGraphicData;
import com.ben.javaengine.logic.LogicMain;
import com.ben.javaengine.logic.map.Map;
import com.ben.javaengine.logic.player.Player;

abstract public class AbstractLogicDataConverter {
	protected JPanel panel;
	protected Double zoomX;
	protected Double zoomY;
	protected Point screenCenter;
	
	public AbstractLogicDataConverter(JPanel panel) {
		this.panel = panel;
	}
	
	public void process(LogicMain data) {
		Map m = data.getMap();
		Double mapWidth = m.getWidth();
		Double mapHeight = m.getHeight();
		Double windowHeight = (double) panel.getHeight();
		Double windowWidth = (double) panel.getWidth();
		zoomX = windowWidth / mapWidth;
		zoomY = windowHeight / mapHeight;
		screenCenter = new Point(panel.getWidth()/2, panel.getHeight()/2);
	}
	
	abstract public List<AbstractGraphicData> convert(LogicMain data);
}
