package com.ben.javaengine.mainmenu;

import com.ben.javaengine.game.state.AbstractState;
import com.ben.javaengine.game.state.ExitState;
import com.ben.javaengine.options.NavigableListOption;

public class Exit extends NavigableListOption {

	public Exit() {
		super("Exit");
	}

	@Override
	public AbstractState nextState() {
		return new ExitState();
	}

}
