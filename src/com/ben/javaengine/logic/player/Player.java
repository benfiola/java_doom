package com.ben.javaengine.logic.player;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ben.javaengine.map.entities.Vertex;

public class Player {
	private static Logger LOG = Logger.getLogger(Player.class);

	public static Double HEIGHT = 2.0;
	public static Double SIGHT_LINE = 3.0;

	private static Double ROTATE_SPEED = .6;

	private static Double MOVE_SPEED = .06;

	private Integer LEFT = 1;
	private Integer UP = 2;
	private Integer RIGHT = 4;
	private Integer DOWN = 8;
	private Integer movementBuffer = 0;

	private Vertex position;
	private Double direction = 0.0;

	public Player(Double x, Double y, Double z, Double direction) {
		this.direction = direction;
		this.position = new Vertex(x, y, z);
	}

	public Vertex getPosition() {
		return this.position;
	}

	public List<Vertex> getConeOfVision() {
		List<Vertex> toReturn = new ArrayList<Vertex>();
		
		/*
		Vertex one = new Vertex(position.getX()
				+ (SIGHT_LINE * Math.cos(Math.toRadians(getDirection()
						- (HORIZONTAL_FIELD_OF_VIEW / 2)))), position.getY()
				+ (SIGHT_LINE * Math.sin(Math.toRadians(getDirection()
						- (HORIZONTAL_FIELD_OF_VIEW / 2)))), position.getZ());
		Vertex two = new Vertex(position.getX()
				+ (SIGHT_LINE * Math.cos(Math.toRadians(getDirection()
						+ (HORIZONTAL_FIELD_OF_VIEW / 2)))), position.getY()
				+ (SIGHT_LINE * Math.sin(Math.toRadians(getDirection()
						+ (HORIZONTAL_FIELD_OF_VIEW / 2)))), position.getZ());
		toReturn.add(one);
		toReturn.add(two);
		*/
		return toReturn;
	}

	public Double getDirection() {
		return direction;
	}

	private void movePlayer(Integer keyBuffer, double timeSinceLastUpdate) {
		if ((keyBuffer & LEFT) > 0) {
			rotateLeft(timeSinceLastUpdate);
		}
		if ((keyBuffer & UP) > 0) {
			moveForward(timeSinceLastUpdate);
		}
		if ((keyBuffer & RIGHT) > 0) {
			rotateRight(timeSinceLastUpdate);
		}
		if ((keyBuffer & DOWN) > 0) {
			moveBackward(timeSinceLastUpdate);
		}
	}

	private void moveForward(double timeSinceLastUpdate) {
		this.position.updateVertex(position.getX()
				+ getXMod(MOVE_SPEED * timeSinceLastUpdate), position.getY()
				+ getYMod(MOVE_SPEED * timeSinceLastUpdate), position.getZ());
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
		this.position.updateVertex(position.getX()
				- getXMod(MOVE_SPEED * timeSinceLastUpdate), position.getY()
				- getYMod(MOVE_SPEED * timeSinceLastUpdate), position.getZ());

	}

	private void rotateRight(double timeSinceLastUpdate) {
		this.direction = direction + (ROTATE_SPEED * timeSinceLastUpdate);
		if (this.direction > 360.0) {
			this.direction = this.direction - 360.0;
		}
	}

	private void rotateLeft(double timeSinceLastUpdate) {
		this.direction = this.direction - (ROTATE_SPEED * timeSinceLastUpdate);
		if (this.direction < 0.0) {
			this.direction = this.direction + 360.0;
		}
	}

	public void update(long timeSinceLastUpdate) {
		movePlayer(movementBuffer, ((double) timeSinceLastUpdate));
	}

	public void addToMovementBuffer(Integer key) {
		Integer bitMask = 1 << key;
		movementBuffer |= bitMask;
	}

	public void removeFromMovementBuffer(Integer key) {
		Integer bitMask = ~(1 << key);
		movementBuffer &= bitMask;
	}
}
