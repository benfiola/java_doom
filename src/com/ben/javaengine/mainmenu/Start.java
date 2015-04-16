package com.ben.javaengine.mainmenu;

import com.ben.javaengine.game.state.AbstractState;
import com.ben.javaengine.game.state.RendererState;
import com.ben.javaengine.options.NavigableListOption;

public class Start extends NavigableListOption {

	public Start() {
		super("Start");
	}

	@Override
	public AbstractState nextState() {
		return new RendererState();
	}

}
