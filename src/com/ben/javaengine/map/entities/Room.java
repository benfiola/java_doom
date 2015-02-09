package com.ben.javaengine.map.entities;

import java.util.List;

import org.apache.log4j.Logger;

public class Room {
	private static Logger LOG = Logger.getLogger(Room.class);
	
	private Integer height;
	private Integer floor;
	private List<Line> walls;
	
	public Room(Integer height, Integer floor, List<Line> walls) {
		this.height = height;
		this.floor = floor;
		this.walls = walls;
	}
	
	public Integer getHeight() {
		return this.height;
	}
	
	public Integer getFloor() {
		return this.floor;
	}
	
	public List<Line> getWalls() {
		return this.walls;
	}
}
