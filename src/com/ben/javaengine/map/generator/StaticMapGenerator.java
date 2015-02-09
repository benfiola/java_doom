package com.ben.javaengine.map.generator;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ben.javaengine.logic.map.Map;
import com.ben.javaengine.map.entities.Line;
import com.ben.javaengine.map.entities.Room;
import com.ben.javaengine.map.entities.Vertex;

public class StaticMapGenerator {
	private static final Logger LOG = Logger.getLogger(StaticMapGenerator.class);
	
	public static Map generateMap() {
		List<Line> walls = new ArrayList<Line>();
		walls.add(new Line(new Vertex(20, 20), new Vertex(40, 20)));
		walls.add(new Line(new Vertex(0,0), new Vertex(100, 0)));
		walls.add(new Line(new Vertex(100, 0), new Vertex(100, 100)));
		walls.add(new Line(new Vertex(100, 100), new Vertex(0, 100)));
		walls.add(new Line(new Vertex(0, 100), new Vertex(0, 0)));
		Room newRoom = new Room(100, 0, walls);
		List<Room> roomList = new ArrayList<Room>();
		roomList.add(newRoom);
		Map toReturn = new Map(roomList, 150, 150);
		return toReturn;
	}
}
