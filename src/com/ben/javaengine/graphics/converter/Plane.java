package com.ben.javaengine.graphics.converter;

import com.ben.javaengine.map.entities.Vector;
import com.ben.javaengine.map.entities.Vertex;

public class Plane {
	private Double distance;
	private Vector normal;

	public Plane(Vector normal, Double distance) {
		this.distance = distance;
		this.normal = normal;
	}

	public Double getDistance(Vertex point) {
		Vector v = new Vector(point);
		Double distance = Vector.dotProduct(v, normal) - this.distance;
		return distance;
	}

	public boolean hasIntersection(Vertex point1, Vertex point2) {
		return (getDistance(point1) < 0 || getDistance(point2) < 0) && (!((getDistance(point1) < 0) && (getDistance(point2) < 0)));
	}

	public Vertex getIntersectionPoint(Vertex point1, Vertex point2) {
		Double distanceP1 = getDistance(point1);
		Double distanceP2 = getDistance(point2);
		Double intersectionFactor = distanceP1 / (distanceP1 - distanceP2);
		return new Vertex(point1.getX() + intersectionFactor
				* (point2.getX() - point1.getX()), point1.getY() + intersectionFactor
				* (point2.getY() - point1.getY()), point1.getZ() + intersectionFactor
				* (point2.getZ() - point1.getZ()));
	}
}
