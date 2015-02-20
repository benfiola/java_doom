package com.ben.javaengine.graphics.frame;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ben.javaengine.event.events.AbstractEvent;
import com.ben.javaengine.event.events.KeyPressEvent;
import com.ben.javaengine.event.events.KeyReleaseEvent;
import com.ben.javaengine.event.events.WindowCloseEvent;
import com.ben.javaengine.event.managers.EventManager;
import com.ben.javaengine.graphics.canvas.AbstractGameCanvas;
import com.ben.javaengine.graphics.canvas.ThreeDimensionalCanvas;
import com.ben.javaengine.graphics.canvas.AbsoluteTopDownCanvas;
import com.ben.javaengine.graphics.canvas.RelativeTopDownCanvas;

public class EngineFrame extends AbstractFrame implements KeyListener, WindowListener {
	private static final Logger LOG = Logger.getLogger(EngineFrame.class);

	private static final long serialVersionUID = 1L;
	private List<AbstractGameCanvas> canvases;
	private static final Integer FRAME_WIDTH = 800;
	private static final Integer FRAME_HEIGHT = 800;

	public EngineFrame(String text) {
		super(text);
		canvases = new ArrayList<AbstractGameCanvas>();
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}
	
	public void finishInitializing() {
		AbstractGameCanvas engineCanvas = new ThreeDimensionalCanvas();
		AbstractGameCanvas topDownAbsoluteCanvas = new AbsoluteTopDownCanvas();
		AbstractGameCanvas topDownRelativeCanvas = new RelativeTopDownCanvas();
		canvases.add(topDownAbsoluteCanvas);
		canvases.add(topDownRelativeCanvas);
		canvases.add(engineCanvas);
		
		GridLayout layout = new GridLayout(canvases.size(), 1);
		this.setLayout(layout);
		getContentPane().add(topDownAbsoluteCanvas);
		getContentPane().add(topDownRelativeCanvas);
		getContentPane().add(engineCanvas);

		this.addWindowListener(this);
		this.addKeyListener(this);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	public List<AbstractGameCanvas> getCanvases() {
		return this.canvases;
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
