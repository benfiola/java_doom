package com.ben.javaengine.graphics.frame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import org.apache.log4j.Logger;

import com.ben.javaengine.event.events.AbstractEvent;
import com.ben.javaengine.event.events.KeyPressEvent;
import com.ben.javaengine.event.events.WindowCloseEvent;
import com.ben.javaengine.event.managers.EventManager;
import com.ben.javaengine.graphics.canvas.EngineCanvas;

public class EngineFrame extends AbstractFrame implements KeyListener, WindowListener {
	private static final Logger LOG = Logger.getLogger(EngineFrame.class);

	private static final long serialVersionUID = 1L;
	private EngineCanvas mainCanvas;
	private static final Integer FRAME_WIDTH = 800;
	private static final Integer FRAME_HEIGHT = 800;

	public EngineFrame(String text) {
		super(text);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		mainCanvas = new EngineCanvas();
		this.getContentPane().add(mainCanvas);
		this.addWindowListener(this);
		this.addKeyListener(this);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	public EngineCanvas getCanvas() {
		return this.mainCanvas;
	}

	public void windowClosed(WindowEvent e) {
		//we're doing this because i don't like having non-AWT related code running on the
		//AWT Thread
		new Thread(new Runnable() {
			public void run() {
				EventManager.publish(new WindowCloseEvent(this.getClass(),
						"Window closed by user.", AbstractEvent.STATUS_OK));
			}
		}, "TempWindowCloseThread").start();;
	}
	
	public void keyPressed(final KeyEvent e) {
		new Thread(new Runnable() {
			public void run() {
				EventManager.publish(new KeyPressEvent(this.getClass(), "User has pressed a key.", AbstractEvent.STATUS_OK, e.getKeyCode()));
			}
		}, "TempKeyPressedThread").start();
	}
}
