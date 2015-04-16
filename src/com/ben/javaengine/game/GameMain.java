package com.ben.javaengine.game;

import com.ben.javaengine.game.state.AbstractState;

public class GameMain {
	private AbstractState state;
	
	public GameMain() {
		
	}
	
	public void setState(AbstractState state) {
		this.state = state;
	}
	
	public AbstractState getState() {
		return this.state;
	}
}
