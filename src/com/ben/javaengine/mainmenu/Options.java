package com.ben.javaengine.mainmenu;

import com.ben.javaengine.game.state.AbstractState;
import com.ben.javaengine.game.state.OptionsMenuState;
import com.ben.javaengine.options.NavigableListOption;


public class Options extends NavigableListOption {

	public Options() {
		super("Options");
	}

	@Override
	public AbstractState nextState() {
		return new OptionsMenuState();
	}
}
