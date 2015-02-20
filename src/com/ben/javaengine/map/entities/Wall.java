package com.ben.javaengine.map.entities;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/*
 * This class represents a 'wall' or a collection of four vertices in a 3D space.
 */
public class Wall {
	private static final Logger LOG = Logger.getLogger(Wall.class);

	private Vertex topLeft;
	private Vertex topRight;
	private Vertex bottomLeft;
	private Vertex bottomRight;
	
	public Wall(Vertex p1, Vertex p2, Double height, Double floor) {
		topLeft = new Vertex(p1.getX(), p1.getY(), height);
		bottomLeft = new Vertex(p1.getX(), p1.getY(), floor);
		topRight = new Vertex(p2.getX(), p2.getY(), height);
		bottomRight = new Vertex(p2.getX(), p2.getY(), floor);
	}
	
	public Vertex getTopLeft() {
		return topLeft;
	}
	
	public Vertex getTopRight() {
		return topRight;
	}
	
	public Vertex getBottomLeft() {
		return bottomLeft;
	}
	
	public Vertex getBottomRight() {
		return bottomRight;
	}
	
	
}
