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
	
	private Frustrum frustrum;
	
	public ThreeDimensionalConverter(JPanel panel) {
		super(panel);
		frustrum = new Frustrum();
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

		for (Sector r : m.getSectors()) {
			for (Wall w : r.getWalls()) {
				List<LineGraphicData> lines = new ArrayList<LineGraphicData>();
				Vertex topLeft = translateAndRotate(w.getTopLeft(), p);
				Vertex topRight = translateAndRotate(w.getTopRight(), p);
				Vertex bottomLeft = translateAndRotate(w.getBottomLeft(), p);
				Vertex bottomRight = translateAndRotate(w.getBottomRight(), p);
				
				VertexPair line1 = frustrum.getIntersection(topLeft, topRight);
				VertexPair line2 = frustrum.getIntersection(topRight, bottomRight);
				VertexPair line3 = frustrum.getIntersection(bottomRight, bottomLeft);
				VertexPair line4 = frustrum.getIntersection(bottomLeft, topLeft);
				addLinesToDraw(line1.getV1(), line1.getV2(), lines);
				addLinesToDraw(line2.getV1(), line2.getV2(), lines);
				addLinesToDraw(line3.getV1(), line3.getV2(), lines);
				addLinesToDraw(line4.getV1(), line4.getV2(), lines);

				toReturn.add(new WallGraphicData(lines, Color.RED));
			}
		}
		return toReturn;
	}
	
	private void addLinesToDraw(Vertex v1, Vertex v2, List<LineGraphicData> lines) {
		Point p1 = transformVertex(v1);
		Point p2 = transformVertex(v2);
		lines.add(new LineGraphicData(p1.x, p1.y, p2.x, p2.y, Color.YELLOW));
	}
	
	private Point transformVertex(Vertex toTransform) {
		double transformation = toTransform.getX();
		double scaleX = toTransform.getY() / transformation;
		double scaleY = toTransform.getZ() / transformation;
		Double panelWidth = (double) this.panel.getWidth();
		Double panelHeight = (double) this.panel.getHeight();
		Double centerX = panelWidth / 2;
		Double centerY = panelHeight / 2;
		Integer xValue = Rounder.round(centerX
				+ (Player.HORIZONTAL_FIELD_OF_VIEW * (scaleX)));
		Integer yValue = Rounder.round(centerY
				+ (Player.HORIZONTAL_FIELD_OF_VIEW * (scaleY)));
		return new Point(xValue, yValue);
	}

	private class Plane {
		private Double distance;
		private Vertex normal;

		public Plane(Vertex normal, Double distance) {
			this.distance = distance;
			this.normal = normal;
		}

		public Double getDistance(Vertex v) {
			Double distance = Vertex.dotProduct(v, normal) - this.distance;
			return distance;
		}

		public boolean hasIntersection(Vertex v1, Vertex v2) {
			return (getDistance(v1) < 0 || getDistance(v2) < 0) && (!((getDistance(v1) < 0) && (getDistance(v2) < 0)));
		}

		public Vertex getIntersectionPoint(Vertex v1, Vertex v2) {
			Double distanceP1 = getDistance(v1);
			Double distanceP2 = getDistance(v2);
			Double intersectionFactor = distanceP1 / (distanceP1 - distanceP2);
			return new Vertex(v1.getX() + intersectionFactor
					* (v2.getX() - v1.getX()), v1.getY() + intersectionFactor
					* (v2.getY() - v1.getY()), v1.getZ() + intersectionFactor
					* (v2.getZ() - v1.getZ()));
		}
	}

	private class Frustrum {
		private Plane left;
		private Plane right;
		private Plane top;
		private Plane bottom;
		private Plane near;

		public Frustrum() {
			Double sinHorizontal = Math.sin(Math
					.toDegrees(Player.HORIZONTAL_FIELD_OF_VIEW));
			Double sinVertical = Math.sin(Math
					.toDegrees(Player.VERTICAL_FIELD_OF_VIEW));
			Double cosHorizontal = Math.cos(Math
					.toDegrees(Player.HORIZONTAL_FIELD_OF_VIEW));
			Double cosVertical = Math.cos(Math
					.toDegrees(Player.VERTICAL_FIELD_OF_VIEW));

			left = new Plane(new Vertex(sinHorizontal, cosHorizontal, 0.0), 0.0);
			right = new Plane(new Vertex(sinHorizontal, -cosHorizontal, 0.0),
					0.0);
			top = new Plane(new Vertex(sinVertical, 0.0, cosVertical), 0.0);
			bottom = new Plane(new Vertex(sinVertical, 0.0, -cosVertical), 0.0);
			near = new Plane(new Vertex(1.0, 0.0, 0.0), -1.0);
		}

		public VertexPair getIntersection(Vertex v1, Vertex v2) {
			if(left.hasIntersection(v1, v2)) {
				if(left.getDistance(v1) < 0) {
					return new VertexPair(left.getIntersectionPoint(v1, v2), v2);
				} else {
					return new VertexPair(v2, left.getIntersectionPoint(v1, v2));
				}
			}
			if(top.hasIntersection(v1, v2)) {
				if(top.getDistance(v1) < 0) {
					return new VertexPair(top.getIntersectionPoint(v1, v2), v2);
				} else {
					return new VertexPair(v2, top.getIntersectionPoint(v1, v2));
				}
			}
			if(bottom.hasIntersection(v1, v2)) {
				if(bottom.getDistance(v1) < 0) {
					return new VertexPair(bottom.getIntersectionPoint(v1, v2), v2);
				} else {
					return new VertexPair(v2, bottom.getIntersectionPoint(v1, v2));
				}
			}
			if(right.hasIntersection(v1, v2)) {
				if(right.getDistance(v1) < 0) {
					return new VertexPair(right.getIntersectionPoint(v1, v2), v2);
				} else {
					return new VertexPair(v2, right.getIntersectionPoint(v1, v2));
				}
			}
			if(near.hasIntersection(v1, v2)) {
				if(near.getDistance(v1) < 0) {
					return new VertexPair(near.getIntersectionPoint(v1, v2), v2);
				} else {
					return new VertexPair(v2, near.getIntersectionPoint(v1, v2));
				}
			}
			return new VertexPair(v1, v2);
		}
	}
	
	private class VertexPair{
		private Vertex v1;
		private Vertex v2;
		
		public VertexPair(Vertex v1, Vertex v2) {
			this.v1 = v1;
			this.v2 = v2;
		}
		
		public Vertex getV1() {
			return this.v1;
		}
		
		public Vertex getV2() {
			return this.v2;
		}
	}
}
