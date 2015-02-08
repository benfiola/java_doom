package com.ben.javaengine.map;

import java.util.List;

import com.ben.javaengine.map.entities.Wall;

public class Map {
	public List<Wall> walls;
	
	public Map(List<Wall> walls) {
		this.walls = walls;
	}
	
	public List<Wall> getWalls() {
		return this.walls;
	}
}
