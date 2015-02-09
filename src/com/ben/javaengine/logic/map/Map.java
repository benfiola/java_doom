package com.ben.javaengine.logic.map;

import java.util.List;

import org.apache.log4j.Logger;

import com.ben.javaengine.map.entities.Room;

public class Map {
	private static final Logger LOG = Logger.getLogger(Map.class);

	private List<Room> rooms;
	private Integer width;
	private Integer height;
	
	public Map(List<Room> rooms, Integer width, Integer height) {
		this.rooms = rooms;
		this.width = width;
		this.height = height;
	}
	
	public List<Room> getRooms() {
		return this.rooms;
	}
	
	public Integer getWidth() {
		return this.width;
	}
	
	public Integer getHeight() {
		return this.height;
	}
}
