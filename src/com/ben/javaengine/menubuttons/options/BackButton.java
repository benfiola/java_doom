package com.ben.javaengine.menubuttons.options;

import com.ben.javaengine.game.state.AbstractState;
import com.ben.javaengine.game.state.MainMenuState;
import com.ben.javaengine.menubuttons.NavigableFooterButton;

public class BackButton extends NavigableFooterButton {
	public BackButton() {
		super("Back");
	}

	@Override
	public AbstractState getNextState() {
		return new MainMenuState();
	}
}
