package com.ben.javaengine.graphics.converter;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

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
			List<LineGraphicData> lines = new ArrayList<LineGraphicData>();

			for (Wall w : r.getWalls()) {
				Vertex tl = w.getTopLeft();
				Vertex bl = w.getBottomLeft();
				Vertex tr = w.getTopRight();
				Vertex br = w.getBottomRight();
				
				Point p1 = transformVertex(tl, p, xFactor, yFactor);
				Point p2 = transformVertex(tr, p, xFactor, yFactor);
				lines.add(new LineGraphicData(p1.x, p1.y, p2.x,
						p2.y, Color.YELLOW));
				
				p1 = transformVertex(tr, p, xFactor, yFactor);
				p2 = transformVertex(br, p, xFactor, yFactor);
				lines.add(new LineGraphicData(p1.x, p1.y, p2.x,
						p2.y, Color.YELLOW));
				
				p1 = transformVertex(bl, p, xFactor, yFactor);
				p2 = transformVertex(br, p, xFactor, yFactor);
				lines.add(new LineGraphicData(p1.x, p1.y, p2.x,
						p2.y, Color.YELLOW));
				
				p1 = transformVertex(br, p, xFactor, yFactor);
				p2 = transformVertex(tr, p, xFactor, yFactor);
				lines.add(new LineGraphicData(p1.x, p1.y, p2.x,
						p2.y, Color.YELLOW));
				toReturn.add(new WallGraphicData(lines, Color.RED));
			}
		}
		return toReturn;
	}
	
	private Point transformVertex(Vertex toTransform, Player p, Double xFactor, Double yFactor){
		Vertex dV = translateAndRotate(toTransform, p);
		Vertex eV = new Vertex( (double) (panel.getWidth()/2), (double) (panel.getHeight()/2), 0.0);
		Integer pBx = Rounder.round(eV.getX() + (eV.getX() * dV.getX())/dV.getZ());
		Integer pBy = Rounder.round(eV.getY() + (eV.getY() * dV.getY())/dV.getZ());
		return new Point(pBx, pBy);
	}

}
