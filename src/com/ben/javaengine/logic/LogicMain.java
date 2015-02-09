package com.ben.javaengine.logic;

import java.awt.event.KeyEvent;

import org.apache.log4j.Logger;

import com.ben.javaengine.logic.map.Map;
import com.ben.javaengine.logic.player.Player;
import com.ben.javaengine.map.generator.StaticMapGenerator;

public class LogicMain {
	public static final Logger LOG = Logger.getLogger(LogicMain.class);
	public Map map;
	public Player player;
	
	public LogicMain() {
		LOG.info("Initializing");
		this.map = StaticMapGenerator.generateMap();
		this.player = new Player(20.0, 20.0, 0.0);
	}
	
	public void handleKeyPress(Integer keyCode) {
		player.movePlayer(keyCode);
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public Map getMap() {
		return this.map;
	}
}
