package com.ben.javaengine.map.entities;

import java.util.List;

import org.apache.log4j.Logger;

public class Sector {
	private static Logger LOG = Logger.getLogger(Sector.class);
	
	private Integer height;
	private Integer floor;
	private List<Wall> walls;
	
	public Sector(Integer height, Integer floor, List<Wall> walls) {
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
	
	public List<Wall> getWalls() {
		return this.walls;
	}
}
