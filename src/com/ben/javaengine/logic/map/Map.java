package com.ben.javaengine.logic.map;

import java.util.List;

import org.apache.log4j.Logger;

import com.ben.javaengine.map.entities.Sector;

public class Map {
	private static final Logger LOG = Logger.getLogger(Map.class);

	private List<Sector> rooms;
	private Double width;
	private Double height;
	
	public Map(List<Sector> rooms, Double width, Double height) {
		this.rooms = rooms;
		this.width = width;
		this.height = height;
	}
	
	public List<Sector> getRooms() {
		return this.rooms;
	}
	
	public Double getWidth() {
		return this.width;
	}
	
	public Double getHeight() {
		return this.height;
	}
}
