package com.ben.javaengine.graphics.converter;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;

import com.ben.javaengine.graphics.entitiies.AbstractGraphicData;
import com.ben.javaengine.graphics.entitiies.LineGraphicData;
import com.ben.javaengine.graphics.entitiies.WallGraphicData;
import com.ben.javaengine.logic.LogicMain;
import com.ben.javaengine.logic.map.Map;
import com.ben.javaengine.logic.player.Player;
import com.ben.javaengine.map.entities.Sector;
import com.ben.javaengine.map.entities.Vertex;
import com.ben.javaengine.map.entities.Wall;
import com.ben.javaengine.utils.Rounder;

public class ThreeDimensionalConverter extends RelativeTopDownConverter {

	public ThreeDimensionalConverter(JPanel panel) {
		super(panel);
	}

	@Override
	public List<AbstractGraphicData> convert(LogicMain data) {
		List<AbstractGraphicData> toReturn = new ArrayList<AbstractGraphicData>();
		Map m = data.getMap();
		Player p = data.getPlayer();
		Double mapWidth = m.getWidth();
		Double mapHeight = m.getHeight();
		Double windowHeight = (double) panel.getHeight();
		Double windowWidth = (double) panel.getWidth();
		Double xFactor = windowWidth / mapWidth;
		Double yFactor = windowHeight / mapHeight;

		for (Sector r : m.getSectors()) {
			for (Wall w : r.getWalls()) {
				List<LineGraphicData> lines = new ArrayList<LineGraphicData>();
				Vertex topLeft = translateAndRotate(w.getTopLeft(), p);
				Vertex topRight = translateAndRotate(w.getTopRight(), p);
				Vertex bottomLeft = translateAndRotate(w.getBottomLeft(), p);
				Vertex bottomRight = translateAndRotate(w.getBottomRight(), p);

				Point topLeftPoint = transformVertex(topLeft, p, xFactor, yFactor);
				Point topRightPoint = transformVertex(topRight, p, xFactor, yFactor);
				Point bottomLeftPoint = transformVertex(bottomLeft, p, xFactor, yFactor);
				Point bottomRightPoint = transformVertex(bottomRight, p, xFactor, yFactor);
				
				if(topLeft.getX() > 0 || topRight.getX() > 0) {
					lines.add(new LineGraphicData(topLeftPoint.x, topLeftPoint.y, topRightPoint.x, topRightPoint.y, Color.YELLOW));
				}
				if(topRight.getX() > 0 || bottomRight.getX() > 0) {
					lines.add(new LineGraphicData(topRightPoint.x, topRightPoint.y, bottomRightPoint.x, bottomRightPoint.y, Color.BLUE));
				}
				if(bottomRight.getX() > 0 || bottomLeft.getX() > 0) {
					lines.add(new LineGraphicData(bottomRightPoint.x, bottomRightPoint.y, bottomLeftPoint.x, bottomLeftPoint.y, Color.GREEN));
				}
				if(bottomLeft.getX() > 0 || topLeft.getX() > 0) {
					lines.add(new LineGraphicData(bottomLeftPoint.x, bottomLeftPoint.y, topLeftPoint.x, topLeftPoint.y, Color.RED));
				}
				toReturn.add(new WallGraphicData(lines, Color.RED));
			}
		}
		return toReturn;
	}

	private Point transformVertex(Vertex toTransform, Player p, Double xFactor,
			Double yFactor) {
		double transformation = toTransform.getX();
		
		/*
		 * negative transformations are killing me - need to read more about this.
		 */
		if(toTransform.getX() <= 0.0) {
			transformation = 0.1;
		}
		double scaleX = toTransform.getY() / transformation;
		double scaleY = toTransform.getZ() /transformation;
		Double panelWidth = (double) this.panel.getWidth();
		Double panelHeight = (double) this.panel.getHeight();
		Double centerX = panelWidth/2;
		Double centerY = panelHeight/2;
		Integer xValue = Rounder.round(centerX + (Player.FIELD_OF_VIEW * (scaleX)));
		Integer yValue = Rounder.round(centerY + (Player.FIELD_OF_VIEW * (scaleY)));
		return new Point(xValue, yValue);
	}
}
