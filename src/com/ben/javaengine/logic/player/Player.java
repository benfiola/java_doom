package com.ben.javaengine.logic.player;

import org.apache.log4j.Logger;

public class Player {
	private static Logger LOG = Logger.getLogger(Player.class);

	public static Integer HEIGHT = 20;
	private Double x = 20.0;
	private Double y = 20.0;
	private Double direction = 0.0;
	
	public Player(Double x, Double y, Double direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
	
	public Double getX() {
		return x;
	}
	
	public Double getY() {
		return y;
	}
	
	public Double getDirection() {
		return direction;
	}
}
