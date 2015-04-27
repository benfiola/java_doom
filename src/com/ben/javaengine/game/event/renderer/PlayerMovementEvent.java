package com.ben.javaengine.game.event.renderer;

import com.ben.javaengine.game.event.AbstractEvent;

public class PlayerMovementEvent extends AbstractEvent {
	private Movement movement;
	public PlayerMovementEvent(Movement movement) {
		this.movement = movement;
	}
	
	public Movement getMovement() {
		return this.movement;
	}
}
