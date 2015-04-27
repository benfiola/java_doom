package com.ben.javaengine.display.converter;

import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import com.ben.javaengine.Controller;
import com.ben.javaengine.game.event.renderer.Movement;
import com.ben.javaengine.game.event.renderer.PlayerMovementEvent;
import com.ben.javaengine.game.state.RendererState;


public class RendererStateConverter extends AbstractStateConverter {
	
	private RendererState state;
	
	public RendererStateConverter(RendererState state, JPanel contentPane) {
		super(contentPane);
		this.state = state;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_W) {
			Controller.getInstance().notifyOfEvent(new PlayerMovementEvent(Movement.FORWARD));
		} else if(e.getKeyCode() == KeyEvent.VK_S) {
			Controller.getInstance().notifyOfEvent(new PlayerMovementEvent(Movement.BACKWARD));
		} else if(e.getKeyCode() == KeyEvent.VK_A) {
			Controller.getInstance().notifyOfEvent(new PlayerMovementEvent(Movement.LEFT));
		} else if(e.getKeyCode() == KeyEvent.VK_D) {
			Controller.getInstance().notifyOfEvent(new PlayerMovementEvent(Movement.RIGHT));
		}
	}

	@Override
	protected void prepareLayout() {
		// TODO Auto-generated method stub
		
	}
	
}
