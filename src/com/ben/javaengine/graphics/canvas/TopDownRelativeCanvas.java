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

public class TopDownRelativeCanvas extends AbstractGameCanvas {
	private static final Logger LOG = Logger.getLogger(TopDownRelativeCanvas.class);

	private static final long serialVersionUID = 1L;
	
	public TopDownRelativeCanvas() {
		super();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(getX(), getY(), this.getWidth(), this.getHeight());
		if(data != null) {
			//we still want to scale the display to the viewport (just for parity with the other top down display - we can remove this later).
			Player p = this.data.getPlayer();
			Map m = this.data.getMap();
			Double mapWidth = m.getWidth();
			Double mapHeight = m.getHeight();
			Double windowHeight = (double) getHeight();
			Double windowWidth = (double) getWidth();			
			Double xFactor = windowWidth / mapWidth;
			Double yFactor = windowHeight / mapHeight;
			
			//we're translating the player so that he's at the origin and his line of sight is constant
			//we want to rotate everything the 'opposite' of the players current rotation (if player rotates left, world rotates right - etc.)
			Double angTranslate = -p.getDirection();
			
			//draw player - player will be at center of viewport - easy enough.
			g.setColor(Color.BLUE);
			g.fillOval( (getWidth()/2) - 5, 
						(getHeight()/2) - 5, 
						10, 
						10);
			
			//draw sight line - should be easy since it will always be a horizontal line starting from the center of the viewport.
			g.setColor(Color.WHITE);
			g.drawLine( (getWidth()/2),
						(getHeight()/2),
						(getWidth()/2) + (Rounder.round( (Player.SIGHT_LINE) * xFactor)),
						(getHeight()/2));
						
			
			g.setColor(Color.YELLOW);
			for(Sector r : m.getRooms()) {
				for(Wall w : r.getWalls()) {
					//first we normalize our points before we rotate them (as our Player is the 'origin' here).
					//therefore these four values represent points if the player were (0,0).
					double normPt1X = w.getPoint1().getX() - p.getX();
					double normPt2X = w.getPoint2().getX() - p.getX();
					double normPt1Y = w.getPoint1().getY() - p.getY();
					double normPt2Y = w.getPoint2().getY() - p.getY();
					
					//i had to look this up on stackoverflow - i wish i could have derived this myself.
					//this just rotates our points around (0,0).
					double rotPt1X = (normPt1X) * Math.cos(Math.toRadians(angTranslate)) - (normPt1Y) * Math.sin(Math.toRadians(angTranslate));
					double rotPt1Y = (normPt1X) * Math.sin(Math.toRadians(angTranslate)) + (normPt1Y) * Math.cos(Math.toRadians(angTranslate));
					double rotPt2X = (normPt2X) * Math.cos(Math.toRadians(angTranslate)) - (normPt2Y) * Math.sin(Math.toRadians(angTranslate));
					double rotPt2Y = (normPt2X) * Math.sin(Math.toRadians(angTranslate)) + (normPt2Y) * Math.cos(Math.toRadians(angTranslate));
					
					//since graphically, the center of the viewport is our 'origin', we then add the center point of the viewport to our calculated points.
					int newPt1X = (getWidth()/2) + Rounder.round(rotPt1X * xFactor);
					int newPt1Y = (getHeight()/2) + Rounder.round(rotPt1Y * yFactor);
					int newPt2X = (getWidth()/2) + Rounder.round(rotPt2X * xFactor);
					int newPt2Y = (getHeight()/2) + Rounder.round(rotPt2Y * yFactor);
					
					g.drawLine(newPt1X, newPt1Y, newPt2X, newPt2Y);
				}
			}
		}
	}
}
