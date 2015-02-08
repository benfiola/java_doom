package com.ben.javaengine.graphics.frame;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import com.ben.javaengine.event.events.AbstractEvent;
import com.ben.javaengine.event.events.WindowCloseEvent;
import com.ben.javaengine.event.managers.EventManager;

public class EngineFrame extends JFrame implements WindowListener {

	private static final long serialVersionUID = 1L;
	
	public EngineFrame(String text) {
		super(text);
		this.addWindowListener(this);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void windowClosed(WindowEvent e) {
		EventManager.publish(new WindowCloseEvent(this.getClass(), "Window closed by user.", AbstractEvent.STATUS_OK));
	}

	public void windowActivated(WindowEvent arg0) {
	}

	public void windowClosing(WindowEvent arg0) {
	}

	public void windowDeactivated(WindowEvent arg0) {
	}

	public void windowDeiconified(WindowEvent arg0) {
	}

	public void windowIconified(WindowEvent arg0) {
	}

	public void windowOpened(WindowEvent arg0) {
	}
}
