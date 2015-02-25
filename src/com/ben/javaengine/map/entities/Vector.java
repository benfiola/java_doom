package com.ben.javaengine.map.entities;

public class Vector {
	private Double x;
	private Double y;
	private Double z;
	
	public Vector(Double x, Double y, Double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector(Vertex end) {
		this.x = end.getX();
		this.y = end.getY();
		this.z = end.getZ();
	}
	
	public Vector(Vertex start, Vertex end) {
		this(start.getX() - end.getX(), start.getY() - end.getY(), start.getZ() - end.getZ());
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
	
	public static Vector crossProduct(Vector a, Vector b) {
		return new Vector(a.getY() * b.getZ() - a.getZ() * b.getY(), a.getZ() * b.getX() - a.getX() * b.getZ(), a.getX() * b.getY() - a.getY() - b.getX());
	}
	
	public Vector normalize() {
		Double length = getLength();
		return new Vector(getX()/length, getY()/length, getZ()/length);
	}
	
	public Double getLength() {
		return Math.sqrt((getX() * getX()) + (getY() * getY()) + (getZ() * getZ()));
	}
	
	public static Double dotProduct(Vector a, Vector b) {
		return (a.getX() * b.getX() + a.getY() * b.getY() + a.getZ() * b.getZ());
	}
	
	@Override
	public String toString() {
		return "[" + this.x + ", " + this.y + ", " + this.z + "]";
	}
}
