package com.ben.javaengine.graphics.canvas;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import org.apache.log4j.Logger;

import com.ben.javaengine.event.events.AbstractEvent;
import com.ben.javaengine.event.events.LogicRequestEvent;
import com.ben.javaengine.event.managers.EventManager;
import com.ben.javaengine.logic.LogicMain;
import com.ben.javaengine.logic.map.Map;
import com.ben.javaengine.logic.player.Player;
import com.ben.javaengine.map.entities.Wall;
import com.ben.javaengine.map.entities.Sector;
import com.ben.javaengine.map.entities.Vertex;
import com.ben.javaengine.utils.Rounder;

public class TopDownAbsoluteCanvas extends AbstractGameCanvas {
	private static final Logger LOG = Logger.getLogger(TopDownAbsoluteCanvas.class);

	private static final long serialVersionUID = 1L;
	
	public TopDownAbsoluteCanvas() {
		super();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		if(data != null) {
			Player p = this.data.getPlayer();
			Map m = this.data.getMap();
			Double mapWidth = m.getWidth();
			Double mapHeight = m.getHeight();
			Double windowHeight = (double) getHeight();
			Double windowWidth = (double) getWidth();
			Double playerX = p.getX();
			Double playerY = p.getY();
			Double xFactor = windowWidth / mapWidth;
			Double yFactor = windowHeight / mapHeight;
			
			//draw player
			g.setColor(Color.BLUE);
			int circleDiameter = 10;
			int circleX = Rounder.round(playerX * xFactor - circleDiameter/2);
			int circleY = Rounder.round(playerY * yFactor - circleDiameter/2);
			g.fillOval(circleX, circleY, circleDiameter, circleDiameter);
			
			//draw sight line
			g.setColor(Color.WHITE);
			double transSlX = p.getX() + (Player.SIGHT_LINE * Math.cos(Math.toRadians(p.getDirection())));
			double transSlY = p.getY() + (Player.SIGHT_LINE * Math.sin(Math.toRadians(p.getDirection())));
			int slX = Rounder.round(transSlX * xFactor);
			int slY = Rounder.round(transSlY * yFactor);
			
			//here we draw the walls in our rooms
			g.drawLine(Rounder.round(playerX * xFactor), Rounder.round(playerY * yFactor), slX, slY);
			g.setColor(Color.YELLOW);
			for(Sector r : m.getRooms()) {
				for(Wall w : r.getWalls()) {
					int pt1X = Rounder.round(w.getPoint1().getX() * xFactor);
					int pt1Y = Rounder.round(w.getPoint1().getY() * yFactor);
					int pt2X = Rounder.round(w.getPoint2().getX() * xFactor);
					int pt2Y = Rounder.round(w.getPoint2().getY() * yFactor);
					g.drawLine(pt1X, pt1Y, pt2X, pt2Y);
				}
			}
		}
	}
}
