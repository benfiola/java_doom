package com.ben.javaengine.graphics.converter;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.ben.javaengine.graphics.entitiies.AbstractGraphicData;
import com.ben.javaengine.graphics.entitiies.LineGraphicData;
import com.ben.javaengine.graphics.entitiies.PlayerGraphicData;
import com.ben.javaengine.graphics.entitiies.WallGraphicData;
import com.ben.javaengine.logic.LogicMain;
import com.ben.javaengine.logic.map.Map;
import com.ben.javaengine.logic.player.Player;
import com.ben.javaengine.map.entities.Sector;
import com.ben.javaengine.map.entities.Vertex;
import com.ben.javaengine.map.entities.Wall;
import com.ben.javaengine.utils.Rounder;

public class AbsoluteTopDownConverter extends AbstractLogicDataConverter {
	
	public AbsoluteTopDownConverter(JPanel panel) {
		super(panel);
	}
	
	@Override
	public List<AbstractGraphicData> convert(LogicMain data) {
		List<AbstractGraphicData> toReturn = new ArrayList<AbstractGraphicData>();
		Player p = data.getPlayer();
		Map m = data.getMap();
		
		//draw player
		Point playerPoint = transformVertex(p.getPosition());
		List<Point> playerSightLinePoints = new ArrayList<Point>();
		for(Vertex sl : p.getConeOfVision()) {
			Point transform = transformVertex(sl);
			playerSightLinePoints.add(transform);
		}
		toReturn.add(new PlayerGraphicData(playerPoint, playerSightLinePoints, Color.BLUE));
		
		//here we draw the walls in our rooms
		for(Sector r : m.getSectors()) {
			for(Wall w : r.getWalls()) {
				List<LineGraphicData> lines = new ArrayList<LineGraphicData>();
				Vertex tl = w.getTopLeft();
				Vertex tr = w.getTopRight();
				Vertex bl = w.getBottomLeft();
				Vertex br = w.getBottomRight();
				
				Point p1 = transformVertex(tl);
				Point p2  = transformVertex(tr);
				lines.add(new LineGraphicData(p1, p2, Color.YELLOW));
				
				p1 = transformVertex(tr);
				p2  = transformVertex(br);
				lines.add(new LineGraphicData(p1, p2, Color.YELLOW));
				
				p1 = transformVertex(br);
				p2  = transformVertex(bl);
				lines.add(new LineGraphicData(p1, p2, Color.YELLOW));
				
				p1 = transformVertex(tl);
				p2  = transformVertex(bl);
				lines.add(new LineGraphicData(p1, p2, Color.YELLOW));
				toReturn.add(new WallGraphicData(lines, Color.RED));
			}
		}
		
		return toReturn;
	}
	
	private Point transformVertex(Vertex toTransform) {
		return new Point(Rounder.round(toTransform.getX() * zoomX), Rounder.round(toTransform.getY() * zoomY));
	}

}
