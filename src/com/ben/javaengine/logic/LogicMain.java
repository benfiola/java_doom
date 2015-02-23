package com.ben.javaengine.logic;

import java.util.Date;

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
		this.player = new Player(20.0, 20.0, Player.HEIGHT, 0.0);
		new Thread(new Runnable() {
			private long prevUpdate = new Date().getTime();
			public void run() {
				while(true) {
					long currTime = new Date().getTime();
					long timeSinceLastUpdate = currTime - prevUpdate;
					player.update(timeSinceLastUpdate);
					prevUpdate = currTime;
				}
			}
		}, "LogicMainThread").start();
	}
	
	public synchronized void handleKeyPress(Integer keyCode) {
		Integer diff = keyCode - 37;
		this.player.addToMovementBuffer(diff);
	}
	
	public synchronized void handleKeyRelease(Integer keyCode) {
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
