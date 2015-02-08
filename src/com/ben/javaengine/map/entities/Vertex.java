package com.ben.javaengine.map.entities;

/*
 * This class represents a point in 3D space
 */
public class Vertex {
	private int x;
	private int y;
	private int z;
	
	public Vertex(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
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
	
	public int getZ() {
		return this.z;
	}
}
