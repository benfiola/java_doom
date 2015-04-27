package com.ben.javaengine.game.entitites;

import java.util.List;

public class Entity {
	private List<Line3D> lines;
	
	public Entity(List<Line3D> lines) {
		this.lines = lines;
	}
	
	public List<Line3D> getLines() {
		return this.lines;
	}
}
