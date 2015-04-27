package com.ben.javaengine.menubuttons.mainmenu;

import com.ben.javaengine.game.state.AbstractState;
import com.ben.javaengine.game.state.ExitState;
import com.ben.javaengine.menubuttons.NavigableListButton;

public class ExitButton extends NavigableListButton {

	public ExitButton() {
		super("Exit");
	}

	@Override
	public AbstractState getNextState() {
		return new ExitState();
	}

}
