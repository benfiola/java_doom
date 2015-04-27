package com.ben.javaengine.game.state;

import com.ben.javaengine.Controller;
import com.ben.javaengine.game.event.AbstractEvent;

public class RendererState extends AbstractState {
	public RendererState() {
		super();
	}

	@Override
	public void takeAction(AbstractEvent e) {
		Controller.getInstance().updateState(this);
	}

}
