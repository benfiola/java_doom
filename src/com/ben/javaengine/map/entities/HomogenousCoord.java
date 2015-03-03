package com.ben.javaengine.map.entities;

public class HomogenousCoord extends Vertex {
	protected Double w;
	
	public HomogenousCoord(Double x, Double y, Double z, Double w) {
		super(x, y, z);
		this.w = w;
	}
	
	public Double getW() {
		return this.w;
	}
	
	public void setW(Double w) {
		this.w = w;
	}
	
	@Override
	public void updateVertex(Double x, Double y, Double z) {
		super.updateVertex(x, y, z);
		setW(w);
	}
	
	@Override
	public String toString() {
		return "[" + this.x + ", " + this.y + ", " + this.z + ", " + this.w + "]";
	}
}
