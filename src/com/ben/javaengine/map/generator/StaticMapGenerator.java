package com.ben.javaengine.map.generator;

import java.util.ArrayList;
import java.util.List;

import com.ben.javaengine.map.Map;
import com.ben.javaengine.map.entities.Vertex;
import com.ben.javaengine.map.entities.Wall;

public class StaticMapGenerator {
	private static final Integer WALL_HEIGHT = 100;
	
	public static Map generateMap() {
		Vertex bl = new Vertex(20, 20, 0);
		Vertex tl = new Vertex(20, 20, WALL_HEIGHT);
		Vertex tr = new Vertex(40, 20, WALL_HEIGHT);
		Vertex br = new Vertex(40, 20, 0);
		Wall newWall = new Wall(tl, tr, bl, br);
		List<Wall> wallList = new ArrayList<Wall>();
		wallList.add(newWall);
		Map toReturn = new Map(wallList);
		return toReturn;
	}
}
