package com.ben.javaengine.map.entities;

import org.apache.log4j.Logger;

/*
 * This class represents a 'wall' or a collection of four vertices in a 3D space.
 */
public class Wall {
	private static final Logger LOG = Logger.getLogger(Wall.class);

	private Vertex p1;
	private Vertex p2;
	
	public Wall(Vertex p1, Vertex p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public Vertex getPoint1() {
		return this.p1;
	}
	
	public Vertex getPoint2() {
		return this.p2;
	}
}
