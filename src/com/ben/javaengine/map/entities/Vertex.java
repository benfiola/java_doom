package com.ben.javaengine.map.entities;

import org.apache.log4j.Logger;

/*
 * This class represents a point in 3D space
 */
public class Vertex {
	private static final Logger LOG = Logger.getLogger(Vertex.class);

	private int x;
	private int y;
	
	public Vertex(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
}
