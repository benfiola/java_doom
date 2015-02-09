package com.ben.javaengine.graphics.frame;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import org.apache.log4j.Logger;

import com.ben.javaengine.event.events.AbstractEvent;
import com.ben.javaengine.event.events.WindowCloseEvent;
import com.ben.javaengine.event.managers.EventManager;
import com.ben.javaengine.graphics.canvas.EngineCanvas;

public class EngineFrame extends JFrame implements WindowListener {
	private static final Logger LOG = Logger.getLogger(EngineFrame.class);

	private static final long serialVersionUID = 1L;
	private static EngineCanvas mainCanvas;
	private static final Integer FRAME_WIDTH = 800;
	private static final Integer FRAME_HEIGHT = 800;

	public EngineFrame(String text) {
		super(text);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		mainCanvas = new EngineCanvas();
		this.getContentPane().add(mainCanvas);
		this.addWindowListener(this);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		new Thread(new Runnable() {
			public void run() {
				while (true) {
					mainCanvas.repaint();
				}
			}
		}).start();
	}

	public void windowClosed(WindowEvent e) {
		EventManager.publish(new WindowCloseEvent(this.getClass(),
				"Window closed by user.", AbstractEvent.STATUS_OK));
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
