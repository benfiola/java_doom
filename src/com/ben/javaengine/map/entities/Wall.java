package com.ben.javaengine.map.entities;

/*
 * This class represents a 'wall' or a collection of four vertices in a 3D space.
 */
public class Wall {
	private Vertex topLeft;
	private Vertex topRight;
	private Vertex bottomLeft;
	private Vertex bottomRight;
	
	public Wall(Vertex topLeft, Vertex topRight, Vertex bottomLeft, Vertex bottomRight) {
		this.topLeft = topLeft;
		this.topRight = topRight;
		this.bottomLeft = bottomLeft;
		this.bottomRight = bottomRight;
	}
	
	public Vertex getTopLeft() {
		return this.topLeft;
	}
	
	public Vertex getTopRight() {
		return this.topRight;
	}
	
	public Vertex getBottomLeft() {
		return this.bottomLeft;
	}
	
	public Vertex getBottomRight() {
		return this.bottomRight;
	}
}
