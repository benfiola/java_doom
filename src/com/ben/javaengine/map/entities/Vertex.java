package com.ben.javaengine.map.entities;

import org.apache.log4j.Logger;

import com.ben.javaengine.logic.player.Player;

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
	
	public void setX(Double x) {
		this.x = x;
	}
	
	public void setY(Double y) {
		this.y = y;
	}
	
	public void setZ(Double z) {
		this.z = z;
	}
	
	public void updateVertex(Double x, Double y, Double z) {
		setX(x);
		setY(y);
		setZ(z);
	}
	
	@Override
	public String toString() {
		return "[" + this.x + ", " + this.y + ", " + this.z + "]";
	}
	
	public Vertex toCameraCoordinate(Player p) {
		double translatedPtX = getX() - p.getPosition().getX();
		double translatedPtY = getY() - p.getPosition().getY();
		double translatedPtZ = p.getPosition().getZ() - getZ();
		double angleTranslation = -p.getDirection();
		
		double rotatedPtX = (translatedPtX)
				* Math.cos(Math.toRadians(angleTranslation)) - (translatedPtY)
				* Math.sin(Math.toRadians(angleTranslation));
		double rotatedPtY = (translatedPtX)
				* Math.sin(Math.toRadians(angleTranslation)) + (translatedPtY)
				* Math.cos(Math.toRadians(angleTranslation));
		return new Vertex(rotatedPtX, rotatedPtY, translatedPtZ);
	}
}
