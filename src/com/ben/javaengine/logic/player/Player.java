package com.ben.javaengine.logic.player;

import java.awt.event.KeyEvent;

import org.apache.log4j.Logger;

import com.ben.javaengine.map.entities.Vertex;

public class Player {
	private static Logger LOG = Logger.getLogger(Player.class);

	public static Integer HEIGHT = 20;
	private static Double ROTATE_SPEED = 5.0;
	private static Double MOVE_SPEED = 3.0;
	
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
	
	public void movePlayer(Integer keyCode) {
		if(keyCode == KeyEvent.VK_UP) {
			moveForward();
		} else if(keyCode == KeyEvent.VK_DOWN) {
			moveBackward();
		} else if(keyCode == KeyEvent.VK_LEFT) {
			rotateLeft();
		} else if(keyCode == KeyEvent.VK_RIGHT) {
			rotateRight();
		}
	}
	
	private void moveForward() {
		this.y = y + getYMod();
		this.x = x + getXMod();
	}
	
	public Vertex getSightPoint() {
		int newY = (int) Math.round(y + getYMod());
		int newX = (int) Math.round(x + getXMod());
		return new Vertex(newX, newY);
	}
	
	private Double getXMod() {
		Double xMod = Math.cos(Math.toRadians(direction)) * MOVE_SPEED;
		return xMod;
	}
	
	private Double getYMod() {
		Double yMod = Math.sin(Math.toRadians(direction)) * MOVE_SPEED;
		return yMod;
	}
	
	private void moveBackward() {
		this.y = y - getYMod();
		this.x = x - getXMod();
	}
	
	private void rotateRight() {
		this.direction = direction + ROTATE_SPEED;
		if(this.direction > 360.0) {
			this.direction = this.direction - 360.0;
		}
	}
	
	private void rotateLeft() {
		this.direction = this.direction - ROTATE_SPEED;
		if(this.direction < 0.0) {
			this.direction = this.direction + 360.0;
		}
	}
}
