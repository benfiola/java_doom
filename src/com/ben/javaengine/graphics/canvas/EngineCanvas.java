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

public class EngineCanvas extends JPanel {
	private static final Logger LOG = Logger.getLogger(EngineCanvas.class);

	private static final long serialVersionUID = 1L;
	private LogicMain data;
	
	public EngineCanvas() {

	}
	
	public void receiveData(LogicMain data) {
		this.data = data;
		this.repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getParent().getWidth(), this.getParent().getHeight());
		if(data != null) {
			Player p = this.data.getPlayer();
			Vertex pSightLine = p.getSightPoint();
			
			Map m = this.data.getMap();
			Double mapWidth = m.getWidth();
			Double mapHeight = m.getHeight();
			Double windowHeight = (double) this.getHeight();
			Double windowWidth = (double) this.getWidth();
			Double playerX = p.getX();
			Double playerY = p.getY();
			
			
			Double xFactor = windowWidth / mapWidth;
			Double yFactor = windowHeight / mapHeight;
			
			//draw player
			g.setColor(Color.BLUE);
			g.fillOval(Rounder.round(playerX * xFactor - 5), Rounder.round(playerY * yFactor - 5), 10, 10);
			
			//draw sight line
			g.setColor(Color.WHITE);
			g.drawLine(Rounder.round(playerX * xFactor), Rounder.round(playerY * yFactor), Rounder.round(pSightLine.getX() * xFactor), Rounder.round(pSightLine.getY() * yFactor));
			
			g.setColor(Color.YELLOW);
			for(Room r : m.getRooms()) {
				for(Line w : r.getWalls()) {
					g.drawLine(Rounder.round(w.getPoint1().getX() * xFactor), Rounder.round(w.getPoint1().getY() * yFactor), Rounder.round(w.getPoint2().getX() * xFactor), Rounder.round(w.getPoint2().getY() * yFactor));
				}
			}
		}
	}
}
