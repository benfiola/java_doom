package com.ben.javaengine.graphics.converter;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.Line;
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

public class ThreeDimensionalConverter extends AbstractLogicDataConverter {
	
	private Double fovY;
	private Double fovX;
	private Double zNear;
	private Double zFar;
	private Double f;
	private Frustrum frustrum;
	
	public ThreeDimensionalConverter(JPanel panel) {
		super(panel);
		zNear = 1.0;
		zFar = 1000.0;
		frustrum = new Frustrum();
	}
	
	@Override
	public void process(LogicMain data) {
		super.process(data);
		fovY = 45.0;
		fovX =  aspectRatio * fovY;
		f = 1/Math.tan(Math.toRadians(fovY));
	}

	@Override
	public List<AbstractGraphicData> convert(LogicMain data) {
		List<AbstractGraphicData> toReturn = new ArrayList<AbstractGraphicData>();
		Map m = data.getMap();
		Player p = data.getPlayer();

		for (Sector r : m.getSectors()) {
			for (Wall w : r.getWalls()) {
				List<LineGraphicData> lines = new ArrayList<LineGraphicData>();
				Vertex tl = w.getTopLeft().toCameraCoordinate(p);
				Vertex bl = w.getBottomLeft().toCameraCoordinate(p);
				Vertex tr = w.getTopRight().toCameraCoordinate(p);
				Vertex br = w.getBottomRight().toCameraCoordinate(p);
				
				Point p1 = transformVertex(tl);
				Point p2 = transformVertex(tr);
				lines.add(new LineGraphicData(p1, p2, Color.YELLOW));
				
				p1 = transformVertex(tr);
				p2 = transformVertex(br);
				lines.add(new LineGraphicData(p1, p2, Color.YELLOW));
				
				p1 = transformVertex(bl);
				p2 = transformVertex(br);
				lines.add(new LineGraphicData(p1, p2, Color.YELLOW));
				
				p1 = transformVertex(br);
				p2 = transformVertex(tr);
				lines.add(new LineGraphicData(p1, p2, Color.YELLOW));
				toReturn.add(new WallGraphicData(lines, Color.RED));
			}
		}
		return toReturn;
	}
	
	private Point transformVertex(Vertex toTransform) {
		Double x = toTransform.getY();
		Double y = toTransform.getZ();
		Double z = toTransform.getX();
		Double xClip = (x*f)/aspectRatio;
		Double yClip = f*y;
		Double zClip = (((zFar+zNear)/(zNear-zFar))*-z)+((2*zFar*zNear)/(zNear-zFar));
		Double wClip = 1.0;
		Double xViewport = xClip/wClip;
		Double yViewport = yClip/wClip;
		Double zViewport = zClip/wClip;
		Integer pointX = Rounder.round(screenCenter.getX() + (100*(xViewport/zViewport)));
		Integer pointY = Rounder.round(screenCenter.getY() + (100*(yViewport/zViewport)));
		return new Point(pointX, pointY);
	}
}
