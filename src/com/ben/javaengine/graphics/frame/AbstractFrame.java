package com.ben.javaengine.graphics.frame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

/*
 * I use this class to basically hide-away all of the unimplemented methods that result from implementing 
 * all these listeners.
 */
public abstract class AbstractFrame extends JFrame implements WindowListener, KeyListener {

	public AbstractFrame(String text) {
		super(text);
	}

	abstract public void windowClosed(WindowEvent e);

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

	public void keyTyped(KeyEvent e) {
	}

	abstract public void keyPressed(KeyEvent e);

	public void keyReleased(KeyEvent e) {
	}
}
