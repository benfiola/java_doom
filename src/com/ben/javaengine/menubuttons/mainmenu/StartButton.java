package com.ben.javaengine.menubuttons.mainmenu;

import com.ben.javaengine.game.state.AbstractState;
import com.ben.javaengine.game.state.RendererState;
import com.ben.javaengine.menubuttons.NavigableListButton;

public class StartButton extends NavigableListButton {

	public StartButton() {
		super("Start");
	}

	@Override
	public AbstractState getNextState() {
		return new RendererState();
	}

}
