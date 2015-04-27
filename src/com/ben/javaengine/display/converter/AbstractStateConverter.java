package com.ben.javaengine.display.converter;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import com.ben.javaengine.game.state.AbstractState;
import com.ben.javaengine.game.state.MainMenuState;
import com.ben.javaengine.game.state.OptionsMenuState;
import com.ben.javaengine.game.state.RendererState;

abstract public class AbstractStateConverter implements KeyListener {
	protected JPanel contentPane;
	private static AbstractStateConverter previous;
	
	public AbstractStateConverter(JPanel contentPane) {
		this.contentPane = contentPane;
	}
	
	public static void convert(AbstractState state, JPanel contentPane) {
		if(previous != null) {
			previous.tearDown();
		}
		AbstractStateConverter converter = null;
		if(state instanceof MainMenuState) {
			converter = new MainMenuStateConverter((MainMenuState) state, contentPane);
		}
		if(state instanceof RendererState) {
			converter = new RendererStateConverter((RendererState) state, contentPane);
		}
		if(state instanceof OptionsMenuState) {
			converter = new OptionsMenuStateConverter((OptionsMenuState) state, contentPane);
		}
		converter.convert();
		previous = converter;
	}
	
	protected void convert() {
		prepareLayout();
		contentPane.setVisible(true);
		registerKeyListeners();
	}
	
	protected void tearDown() {
		clearKeyListeners();
		contentPane.setVisible(false);
		contentPane.removeAll();
	}
	
	protected void registerKeyListeners() {
		contentPane.getTopLevelAncestor().addKeyListener(this);
	}
	
	protected void clearKeyListeners() {
		contentPane.getTopLevelAncestor().removeKeyListener(this);
	}
	
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	
	abstract public void keyPressed(KeyEvent e);
	abstract protected void prepareLayout();
}
