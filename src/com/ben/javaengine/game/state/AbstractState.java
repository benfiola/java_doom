package com.ben.javaengine.game.state;

import com.ben.javaengine.game.event.AbstractEvent;

abstract public class AbstractState {
	
	public AbstractState() {
	}
	
	abstract public void takeAction(AbstractEvent e);
}
