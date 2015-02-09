package com.ben.javaengine.logic.map;

import java.util.List;

import org.apache.log4j.Logger;

import com.ben.javaengine.map.entities.Line;
import com.ben.javaengine.map.entities.Room;

public class Map {
	private static final Logger LOG = Logger.getLogger(Map.class);

	public List<Room> rooms;
	
	public Map(List<Room> rooms) {
		this.rooms = rooms;
	}
	
	public List<Room> getRooms() {
		return this.rooms;
	}
}
