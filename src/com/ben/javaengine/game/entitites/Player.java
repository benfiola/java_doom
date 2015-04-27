package com.ben.javaengine.game.entitites;

import javafx.geometry.Point3D;

public class Player {
	private Point3D location;
	private static final Double HEIGHT = 5.0;

	public Player(Point3D location) {
		this.location = location;
	}

	public Player() {
		this(new Point3D(0.0, 0.0, HEIGHT));
	}

	public Point3D getLocation() {
		return this.location;
	}

	public void updateLocation(Point3D movementVector) {
		location = new Point3D(location.getX() + movementVector.getX(),
				location.getY() + movementVector.getY(), location.getX()
						+ movementVector.getZ());
	}
}
