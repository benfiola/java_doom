package com.ben.javaengine.map.generator;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ben.javaengine.logic.map.Map;
import com.ben.javaengine.map.entities.Wall;
import com.ben.javaengine.map.entities.Sector;
import com.ben.javaengine.map.entities.Vertex;

public class StaticMapGenerator {
	private static final Logger LOG = Logger.getLogger(StaticMapGenerator.class);
	
	public static Map generateMap() {
		List<Wall> walls = new ArrayList<Wall>();
		walls.add(new Wall(new Vertex(20.0, 20.0), new Vertex(30.0, 20.0), 3.0, 0.0));
		walls.add(new Wall(new Vertex(0.0,0.0), new Vertex(40.0, 0.0), 8.0, 0.0));
		walls.add(new Wall(new Vertex(40.0, 0.0), new Vertex(40.0, 40.0), 8.0, 0.0));
		walls.add(new Wall(new Vertex(40.0, 40.0), new Vertex(0.0, 40.0), 8.0, 0.0));
		walls.add(new Wall(new Vertex(0.0, 40.0), new Vertex(0.0, 0.0), 8.0, 0.0));
		Sector newRoom = new Sector(50, 0, walls);
		List<Sector> roomList = new ArrayList<Sector>();
		roomList.add(newRoom);
		Map toReturn = new Map(roomList, 50.0, 50.0);
		return toReturn;
	}
}
