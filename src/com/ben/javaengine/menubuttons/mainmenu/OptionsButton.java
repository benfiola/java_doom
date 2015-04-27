package com.ben.javaengine.menubuttons.mainmenu;

import com.ben.javaengine.game.state.AbstractState;
import com.ben.javaengine.game.state.OptionsMenuState;
import com.ben.javaengine.menubuttons.NavigableListButton;


public class OptionsButton extends NavigableListButton {

	public OptionsButton() {
		super("Options");
	}

	@Override
	public AbstractState getNextState() {
		return new OptionsMenuState();
	}
}
