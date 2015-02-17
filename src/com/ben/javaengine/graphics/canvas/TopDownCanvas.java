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
import com.ben.javaengine.map.entities.Line;
import com.ben.javaengine.map.entities.Room;
import com.ben.javaengine.map.entities.Vertex;
import com.ben.javaengine.utils.Rounder;

public class TopDownCanvas extends AbstractGameCanvas {
	private static final Logger LOG = Logger.getLogger(TopDownCanvas.class);

	private static final long serialVersionUID = 1L;
	
	public TopDownCanvas() {
		super();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(getX(), getY(), this.getWidth(), this.getHeight());
		if(data != null) {
			Player p = this.data.getPlayer();
			Vertex pSightLine = p.getSightPoint();
			
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
			g.fillOval(getX() + Rounder.round(playerX * xFactor - 5), getY() + Rounder.round(playerY * yFactor - 5), 10, 10);
			
			//draw sight line
			g.setColor(Color.WHITE);
			g.drawLine(Rounder.round(getX() + playerX * xFactor), getY() + Rounder.round(playerY * yFactor), getX() + Rounder.round(pSightLine.getX() * xFactor), getY() + Rounder.round(pSightLine.getY() * yFactor));
			
			g.setColor(Color.YELLOW);
			for(Room r : m.getRooms()) {
				for(Line w : r.getWalls()) {
					g.drawLine(getX() + Rounder.round(w.getPoint1().getX() * xFactor), getY() + Rounder.round(w.getPoint1().getY() * yFactor), getX() + Rounder.round(w.getPoint2().getX() * xFactor), getY() + Rounder.round(w.getPoint2().getY() * yFactor));
				}
			}
		}
	}
}
