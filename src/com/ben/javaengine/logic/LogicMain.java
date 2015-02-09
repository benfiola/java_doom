package com.ben.javaengine.logic;

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
		new Thread(new Runnable() {
			public void run() {
				while(true) {
					player.update();
					try { Thread.sleep(30); } catch(Exception e) {}
				}
			}
		}, "LogicMainThread").start();
	}
	
	public void handleKeyPress(Integer keyCode) {
		Integer diff = keyCode - 37;
		this.player.addToMovementBuffer(diff);
	}
	
	public void handleKeyRelease(Integer keyCode) {
		Integer diff = keyCode - 37;
		this.player.removeFromMovementBuffer(diff);
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public Map getMap() {
		return this.map;
	}
}
