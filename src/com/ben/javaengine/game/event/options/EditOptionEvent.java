package com.ben.javaengine.game.event.options;

import java.awt.event.KeyEvent;

import com.ben.javaengine.game.event.AbstractEvent;

public class EditOptionEvent extends AbstractEvent  {
	private KeyEvent event;
	public EditOptionEvent(KeyEvent e) {
		super();
		this.event = e;
	}
	
	public KeyEvent getEvent() {
		return event;
	}
}
