package com.ben.javaengine;

import com.ben.javaengine.configuration.Configuration;
import com.ben.javaengine.display.DisplayMain;
import com.ben.javaengine.game.GameMain;
import com.ben.javaengine.game.event.AbstractEvent;
import com.ben.javaengine.game.state.AbstractState;
import com.ben.javaengine.game.state.ExitState;
import com.ben.javaengine.game.state.MainMenuState;

public class Controller {
	private Configuration configuration;
	private DisplayMain display;
	private GameMain game;
	private static Controller instance;
	
	private boolean wantsToExit;
	private Integer exitCode;
	
	private Controller() {
		configuration = new Configuration();
	}
	
	private void init() {
		display = new DisplayMain();
		game = new GameMain();
		updateState(new MainMenuState());
	}
	
	public static Controller getInstance() {
		if(instance == null) {
			instance = new Controller();
			instance.init();
		}
		return instance;
	}
	
	public void notifyOfEvent(AbstractEvent event) {
		game.getState().takeAction(event);
		display.setState(game.getState());
	}
	
	public void updateState(AbstractState state) {
		if(state instanceof ExitState) {
			prepareToExit(0);
			return;
		}
		game.setState(state);
		display.setState(state);
	}
	
	public Configuration getConfiguration() {
		return configuration;
	}
	
	public synchronized boolean wantsToExit() {
		return wantsToExit;
	}
	
	public Integer getExitCode() {
		return exitCode;
	}
	
	public synchronized void prepareToExit(Integer exitCode) {
		this.exitCode = exitCode;
		this.wantsToExit = true;
	}
}
