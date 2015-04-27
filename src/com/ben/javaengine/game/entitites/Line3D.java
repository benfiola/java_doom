package com.ben.javaengine.game.entitites;

import javafx.geometry.Point3D;

public class Line3D {
	private Point3D point1;
	private Point3D point2;
	
	public Line3D(Point3D p1, Point3D p2) {
		this.point1 = p1;
		this.point2 = p2;
	}
	
	public Point3D getPoint1() {
		return this.point1;
	}
	
	public Point3D getPoint2() {
		return this.point2;
	}
}
