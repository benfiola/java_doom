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

public class RelativeTopDownConverter extends AbstractLogicDataConverter {

	public RelativeTopDownConverter(JPanel panel) {
		super(panel);
	}

	@Override
	public List<AbstractGraphicData> convert(LogicMain data) {
		List<AbstractGraphicData> toReturn = new ArrayList<AbstractGraphicData>();
		// we still want to scale the display to the viewport (just for parity
		// with the other top down display - we can remove this later).
		Player p = data.getPlayer();
		Map m = data.getMap();
		Double mapWidth = m.getWidth();
		Double mapHeight = m.getHeight();
		Double windowHeight = (double) panel.getHeight();
		Double windowWidth = (double) panel.getWidth();
		Double xFactor = windowWidth / mapWidth;
		Double yFactor = windowHeight / mapHeight;

		// draw player - player will be at center of viewport - easy enough.
		Integer pX = Rounder.round((windowWidth / 2));
		Integer pY = Rounder.round((windowHeight / 2));
		PlayerGraphicData pGfx = new PlayerGraphicData(pX, pY, Color.BLUE);
		toReturn.add(pGfx);
		
		double transSlX = (windowWidth/2) + ((Player.SIGHT_LINE * Math.cos(Math.toRadians(Player.FIELD_OF_VIEW/2))) * xFactor);
		double transSlY = (windowHeight/2) + ((Player.SIGHT_LINE * Math.sin(Math.toRadians(Player.FIELD_OF_VIEW/2))) * yFactor);
		int slX = Rounder.round(transSlX);
		int slY = Rounder.round(transSlY);
		pGfx.addSightLine(slX, slY);
		
		transSlX = (windowWidth/2) + ((Player.SIGHT_LINE * Math.cos(Math.toRadians(-Player.FIELD_OF_VIEW/2))) * xFactor);
		transSlY = (windowHeight/2) + ((Player.SIGHT_LINE * Math.sin(Math.toRadians(-Player.FIELD_OF_VIEW/2))) * yFactor);
		slX = Rounder.round(transSlX);
		slY = Rounder.round(transSlY);
		pGfx.addSightLine(slX, slY);
		
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
	
	private Point transformVertex(Vertex toTransform, Player p, Double xFactor, Double yFactor) {
		int offsetX = panel.getWidth() / 2;
		int offsetY = panel.getHeight() / 2;
		
		Vertex transRotVertex = translateAndRotate(toTransform, p);
		
		int ptX = Rounder.round(offsetX
				+ (transRotVertex.getX() * xFactor));
		int ptY = Rounder.round(offsetY
				+ (transRotVertex.getY() * yFactor));
		return new Point(ptX, ptY);	
	}
	
	protected Vertex translateAndRotate(Vertex toTransform, Player p) {
		double normalizedPtX = toTransform.getX() - p.getX();
		double normalizedPtY = toTransform.getY() - p.getY();
		double normalizedPtZ = p.getZ() - toTransform.getZ();
		double angleTranslation = -p.getDirection();
		
		double rotatedPtX = (normalizedPtX)
				* Math.cos(Math.toRadians(angleTranslation)) - (normalizedPtY)
				* Math.sin(Math.toRadians(angleTranslation));
		double rotatedPtY = (normalizedPtX)
				* Math.sin(Math.toRadians(angleTranslation)) + (normalizedPtY)
				* Math.cos(Math.toRadians(angleTranslation));
		return new Vertex(rotatedPtX, rotatedPtY, normalizedPtZ);
	}

}
