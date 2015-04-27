package com.ben.javaengine.menubuttons;

import com.ben.javaengine.game.state.AbstractState;

abstract public class NavigableButton extends AbstractButton {
	protected String value;
	public NavigableButton(String value) {
		super();
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	abstract public AbstractState getNextState();
}
