package com.ben.javaengine.logic.player;

import org.apache.log4j.Logger;

import com.ben.javaengine.map.entities.Vertex;

public class Player {
	private static Logger LOG = Logger.getLogger(Player.class);

	public static Double HEIGHT = 20.0;
	public static Double SIGHT_LINE = 3.0;
	
	private static Double ROTATE_SPEED = 10.0;

	private static Double MOVE_SPEED = 1.0;
	private static Double FIELD_OF_VIEW = 75.0;
	
	private Integer LEFT = 1;
	private Integer UP = 2;
	private Integer RIGHT = 4;
	private Integer DOWN = 8;
	private Integer movementBuffer = 0;
	
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
	
	private void movePlayer(Integer keyBuffer) {
		if((keyBuffer & LEFT) > 0) {
			rotateLeft();
		} 
		if((keyBuffer & UP) > 0) {
			moveForward();
		}
		if((keyBuffer & RIGHT) > 0) {
			rotateRight();
		} 
		if((keyBuffer & DOWN) > 0) {
			moveBackward();
		}
	}
	
	private void moveForward() {
		this.y = y + getYMod(MOVE_SPEED);
		this.x = x + getXMod(MOVE_SPEED);
	}

	private Double getXMod(Double scalar) {
		Double xMod = Math.cos(Math.toRadians(direction)) * scalar;
		return xMod;
	}
	
	private Double getYMod(Double scalar) {
		Double yMod = Math.sin(Math.toRadians(direction)) * scalar;
		return yMod;
	}
	
	private void moveBackward() {
		this.y = y - getYMod(MOVE_SPEED);
		this.x = x - getXMod(MOVE_SPEED);
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
	
	public void update() {
		movePlayer(movementBuffer);
	}
	
	public void addToMovementBuffer(Integer key) {
		Integer bitMask = 1 << key;
		movementBuffer |= bitMask;
	}
	
	public void removeFromMovementBuffer(Integer key) {
		Integer bitMask = ~(1<<key);
		movementBuffer &= bitMask;
	}
}
