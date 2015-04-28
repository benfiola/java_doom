package com.ben.javaengine.game.state;

import java.util.List;

import com.ben.javaengine.Controller;
import com.ben.javaengine.game.entitites.Entity;
import com.ben.javaengine.game.event.AbstractEvent;
import com.ben.javaengine.game.event.renderer.PlayerMovementEvent;

public class RendererState extends AbstractState {
	private List<Entity> entities;
	private Player player;
	
	public RendererState() {
		super();
		this.entities = spawnEntitites();
	}

	@Override
	public void takeAction(AbstractEvent e) {
		if(e instanceof PlayerMovementEvent) {
			
		}
		Controller.getInstance().updateState(this);
	}
	
	private static List<Entity> spawnEntities() {
		
	}
}
