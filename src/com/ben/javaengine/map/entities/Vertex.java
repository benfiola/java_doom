package com.ben.javaengine.map.entities;

import java.util.Vector;

import org.apache.log4j.Logger;

/*
 * This class represents a point in 3D space
 */
public class Vertex {
	private static final Logger LOG = Logger.getLogger(Vertex.class);

	private Double x;
	private Double y;
	private Double z;
	
	public Vertex(Double x, Double y, Double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vertex(Double x, Double y) {
		this.x = x;
		this.y = y;
		this.z = 0.0;
	}
	
	public Double getX() {
		return this.x;
	}
	
	public Double getY() {
		return this.y;
	}
	
	public Double getZ() {
		return this.z;
	}
	
	public boolean hasPositiveCoordinates() {
		return this.x >= 0.0;
	}
	
	public static Vertex crossProduct(Vertex a, Vertex b) {
		return new Vertex(a.getY() * b.getZ() - a.getZ() * b.getY(), a.getZ() * b.getX() - a.getX() * b.getZ(), a.getX() * b.getY() - a.getY() - b.getX());
	}
	
	public static Vertex normalize(Vertex a) {
		Double length = getLength(a);
		return new Vertex(a.getX()/length, a.getY()/length, a.getZ()/length);
	}
	
	public static Double getLength(Vertex a) {
		return Math.sqrt((a.getX() * a.getX()) + (a.getY() * a.getY()) + (a.getZ() * a.getZ()));
	}
	
	public static Double dotProduct(Vertex a, Vertex b) {
		return (a.getX() * b.getX() + a.getY() * b.getY() + a.getZ() * b.getZ());
	}
	
	@Override
	public String toString() {
		return "[" + this.x + ", " + this.y + ", " + this.z + "]";
	}
}
