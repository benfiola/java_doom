package com.ben.javaengine.graphics.frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BoxLayout;

import org.apache.log4j.Logger;

import com.ben.javaengine.event.events.AbstractEvent;
import com.ben.javaengine.event.events.KeyPressEvent;
import com.ben.javaengine.event.events.KeyReleaseEvent;
import com.ben.javaengine.event.events.WindowCloseEvent;
import com.ben.javaengine.event.managers.EventManager;
import com.ben.javaengine.graphics.canvas.AbstractGameCanvas;
import com.ben.javaengine.graphics.canvas.EngineCanvas;
import com.ben.javaengine.graphics.canvas.TopDownCanvas;

public class EngineFrame extends AbstractFrame implements KeyListener, WindowListener {
	private static final Logger LOG = Logger.getLogger(EngineFrame.class);

	private static final long serialVersionUID = 1L;
	private AbstractGameCanvas engineCanvas;
	private AbstractGameCanvas topDownCanvas;
	private static final Integer FRAME_WIDTH = 800;
	private static final Integer FRAME_HEIGHT = 800;

	public EngineFrame(String text) {
		super(text);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}
	
	public void finishInitializing() {
		GridLayout layout = new GridLayout(2, 1);
		this.setLayout(layout);
		engineCanvas = new EngineCanvas();
		topDownCanvas = new TopDownCanvas();
		getContentPane().add(topDownCanvas);
		getContentPane().add(engineCanvas);
		this.addWindowListener(this);
		this.addKeyListener(this);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	public AbstractGameCanvas getEngineCanvas() {
		return this.engineCanvas;
	}

	public AbstractGameCanvas getTopDownCanvas() {
		return this.topDownCanvas;
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
	
	public void keyReleased(final KeyEvent e) {
		new Thread(new Runnable() {
			public void run() {
				EventManager.publish(new KeyReleaseEvent(this.getClass(), "User has released a key.", AbstractEvent.STATUS_OK, e.getKeyCode()));
			}
		}, "TempKeyPressedThread").start();
	}
}
