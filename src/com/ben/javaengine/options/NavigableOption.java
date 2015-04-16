package com.ben.javaengine.options;

import com.ben.javaengine.game.state.AbstractState;

abstract public class NavigableOption extends AbstractOption {
	protected String value;
	public NavigableOption(String value) {
		super();
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	abstract public AbstractState nextState();
}
