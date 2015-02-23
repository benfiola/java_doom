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
		Double mapWidth = m.getWidth();
		Double mapHeight = m.getHeight();
		Double windowHeight = (double) panel.getHeight();
		Double windowWidth = (double) panel.getWidth();
		Double playerX = p.getX();
		Double playerY = p.getY();
		Double xFactor = windowWidth / mapWidth;
		Double yFactor = windowHeight / mapHeight;
		
		//draw player
		int pX = Rounder.round(playerX * xFactor);
		int pY = Rounder.round(playerY * yFactor);
		PlayerGraphicData pGfx = new PlayerGraphicData(pX, pY, Color.BLUE);
		toReturn.add(pGfx);
		
		double transSlX = p.getX() + (Player.SIGHT_LINE * Math.cos(Math.toRadians(p.getDirection() + (Player.FIELD_OF_VIEW/2))));
		double transSlY = p.getY() + (Player.SIGHT_LINE * Math.sin(Math.toRadians(p.getDirection() + (Player.FIELD_OF_VIEW/2))));
		int slX = Rounder.round(transSlX * xFactor);
		int slY = Rounder.round(transSlY * yFactor);
		pGfx.addSightLine(slX, slY);
		transSlX = p.getX() + (Player.SIGHT_LINE * Math.cos(Math.toRadians(p.getDirection() - (Player.FIELD_OF_VIEW/2))));
		transSlY = p.getY() + (Player.SIGHT_LINE * Math.sin(Math.toRadians(p.getDirection() - (Player.FIELD_OF_VIEW/2))));
		slX = Rounder.round(transSlX * xFactor);
		slY = Rounder.round(transSlY * yFactor);
		pGfx.addSightLine(slX, slY);
		
		//here we draw the walls in our rooms
		for(Sector r : m.getSectors()) {
			for(Wall w : r.getWalls()) {
				List<LineGraphicData> lines = new ArrayList<LineGraphicData>();
				Vertex tl = w.getTopLeft();
				Vertex tr = w.getTopRight();
				Vertex bl = w.getBottomLeft();
				Vertex br = w.getBottomRight();
				
				Point p1 = transformVertex(tl, xFactor, yFactor);
				Point p2  = transformVertex(tr, xFactor, yFactor);
				lines.add(new LineGraphicData(p1.x, p1.y, p2.x, p2.y, Color.YELLOW));
				
				p1 = transformVertex(tr, xFactor, yFactor);
				p2  = transformVertex(br, xFactor, yFactor);
				lines.add(new LineGraphicData(p1.x, p1.y, p2.x, p2.y, Color.YELLOW));
				
				p1 = transformVertex(br, xFactor, yFactor);
				p2  = transformVertex(bl, xFactor, yFactor);
				lines.add(new LineGraphicData(p1.x, p1.y, p2.x, p2.y, Color.YELLOW));
				
				p1 = transformVertex(tl, xFactor, yFactor);
				p2  = transformVertex(bl, xFactor, yFactor);
				lines.add(new LineGraphicData(p1.x, p1.y, p2.x, p2.y, Color.YELLOW));
				toReturn.add(new WallGraphicData(lines, Color.RED));
			}
		}
		
		return toReturn;
	}
	
	private Point transformVertex(Vertex toTransform, Double xFactor, Double yFactor) {
		return new Point(Rounder.round(toTransform.getX() * xFactor), Rounder.round(toTransform.getY() * yFactor));
	}

}
