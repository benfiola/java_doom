package com.ben.javaengine.graphics.canvas;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import org.apache.log4j.Logger;

import com.ben.javaengine.event.events.AbstractEvent;
import com.ben.javaengine.event.events.LogicRequestEvent;
import com.ben.javaengine.event.managers.EventManager;
import com.ben.javaengine.logic.LogicMain;

public class EngineCanvas extends JPanel {
	private static final Logger LOG = Logger.getLogger(EngineCanvas.class);

	private static final long serialVersionUID = 1L;
	private LogicMain data;
	
	public void receiveData(LogicMain data) {
		this.data = data;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		EventManager.publish(new LogicRequestEvent(this.getClass(), "Requesting logic data", AbstractEvent.STATUS_OK));
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getParent().getWidth(), this.getParent().getHeight());
		if(data != null) {
			
		}
	}
}
