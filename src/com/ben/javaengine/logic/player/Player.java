package com.ben.javaengine.logic.player;

import org.apache.log4j.Logger;

public class Player {
	private static Logger LOG = Logger.getLogger(Player.class);

	public static Double HEIGHT = 2.0;
	public static Double SIGHT_LINE = 3.0;
	
	private static Double ROTATE_SPEED = .6;

	private static Double MOVE_SPEED = .06;
	public static Double FIELD_OF_VIEW = 90.0;
	
	private Integer LEFT = 1;
	private Integer UP = 2;
	private Integer RIGHT = 4;
	private Integer DOWN = 8;
	private Integer movementBuffer = 0;
	
	private Double x = 20.0;
	private Double y = 20.0;
	private Double z = 20.0;
	private Double direction = 0.0;
	
	public Player(Double x, Double y, Double z, Double direction) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.direction = direction;
	}
	
	public Double getX() {
		return x;
	}
	
	public Double getY() {
		return y;
	}
	

	public Double getZ() {
		return z;
	}
	
	public Double getDirection() {
		return direction;
	}
	
	private void movePlayer(Integer keyBuffer, double timeSinceLastUpdate) {
		if((keyBuffer & LEFT) > 0) {
			rotateLeft(timeSinceLastUpdate);
		} 
		if((keyBuffer & UP) > 0) {
			moveForward(timeSinceLastUpdate);
		}
		if((keyBuffer & RIGHT) > 0) {
			rotateRight(timeSinceLastUpdate);
		} 
		if((keyBuffer & DOWN) > 0) {
			moveBackward(timeSinceLastUpdate);
		}
	}
	
	private void moveForward(double timeSinceLastUpdate) {
		this.y = y + getYMod(MOVE_SPEED * timeSinceLastUpdate);
		this.x = x + getXMod(MOVE_SPEED * timeSinceLastUpdate);
	}

	private Double getXMod(Double scalar) {
		Double xMod = Math.cos(Math.toRadians(direction)) * scalar;
		return xMod;
	}
	
	private Double getYMod(Double scalar) {
		Double yMod = Math.sin(Math.toRadians(direction)) * scalar;
		return yMod;
	}
	
	private void moveBackward(double timeSinceLastUpdate) {
		this.y = y - getYMod(MOVE_SPEED * timeSinceLastUpdate);
		this.x = x - getXMod(MOVE_SPEED * timeSinceLastUpdate);
	}
	
	private void rotateRight(double timeSinceLastUpdate) {
		this.direction = direction + (ROTATE_SPEED * timeSinceLastUpdate);
		if(this.direction > 360.0) {
			this.direction = this.direction - 360.0;
		}
	}
	
	private void rotateLeft(double timeSinceLastUpdate) {
		this.direction = this.direction - (ROTATE_SPEED * timeSinceLastUpdate);
		if(this.direction < 0.0) {
			this.direction = this.direction + 360.0;
		}
	}
	
	public void update(long timeSinceLastUpdate) {
		movePlayer(movementBuffer, ((double) timeSinceLastUpdate) / 2.0);
	}
	
	public synchronized void addToMovementBuffer(Integer key) {
		Integer bitMask = 1 << key;
		movementBuffer |= bitMask;
	}
	
	public synchronized void removeFromMovementBuffer(Integer key) {
		Integer bitMask = ~(1<<key);
		movementBuffer &= bitMask;
	}
}
