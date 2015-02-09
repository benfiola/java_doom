package com.ben.javaengine.logic;

import com.ben.javaengine.logic.map.Map;
import com.ben.javaengine.logic.player.Player;
import com.ben.javaengine.map.generator.StaticMapGenerator;

public class LogicMain {
	public Map map;
	public Player player;
	
	public LogicMain() {
		this.map = StaticMapGenerator.generateMap();
		this.player = new Player(20.0, 20.0, 0.0);
	}
}
