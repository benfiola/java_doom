package com.ben.javaengine.map.entities;

import org.apache.log4j.Logger;

/*
 * This class represents a point in 3D space
 */
public class Vertex {
	private static final Logger LOG = Logger.getLogger(Vertex.class);

	private Double x;
	private Double y;
	
	public Vertex(Double x, Double y) {
		this.x = x;
		this.y = y;
	}
	
	public Double getX() {
		return this.x;
	}
	
	public Double getY() {
		return this.y;
	}
}
