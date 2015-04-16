package com.ben.javaengine.options;

import com.ben.javaengine.game.state.AbstractState;
import com.ben.javaengine.game.state.MainMenuState;

public class BackOption extends NavigableFooterOption {
	public BackOption() {
		super("Back");
	}

	@Override
	public AbstractState nextState() {
		return new MainMenuState();
	}
}
