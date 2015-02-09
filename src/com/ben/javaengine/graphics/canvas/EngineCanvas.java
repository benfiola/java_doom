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

public class EngineCanvas extends JPanel {
	private static final Logger LOG = Logger.getLogger(EngineCanvas.class);

	private static final long serialVersionUID = 1L;
	private LogicMain data;
	
	public EngineCanvas() {
		new Thread(new Runnable() {
			public void run() {
				while(true) {
					EventManager.publish(new LogicRequestEvent(this.getClass(), "Requesting logic data", AbstractEvent.STATUS_OK));
				}
			}
		}, "CanvasDataFetchThread").start();
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
			int mapWidth = m.getWidth();
			int mapHeight = m.getHeight();
			int windowHeight = this.getHeight();
			int windowWidth = this.getWidth();
			int playerX = (int) Math.round(p.getX());
			int playerY = (int) Math.round(p.getY());
			
			
			int xFactor = windowWidth / mapWidth;
			int yFactor = windowHeight / mapHeight;
			
			//draw player
			g.setColor(Color.BLUE);
			g.fillOval(playerX * xFactor - 5, playerY * yFactor - 5, 10, 10);
			
			//draw sight line
			g.setColor(Color.WHITE);
			g.drawLine(playerX * xFactor, playerY * yFactor, pSightLine.getX() * xFactor, pSightLine.getY() * yFactor);
			
			g.setColor(Color.YELLOW);
			for(Room r : m.getRooms()) {
				for(Line w : r.getWalls()) {
					g.drawLine(w.getPoint1().getX() * xFactor, w.getPoint1().getY() * yFactor, w.getPoint2().getX() * xFactor, w.getPoint2().getY() * yFactor);
				}
			}
		}
	}
}
