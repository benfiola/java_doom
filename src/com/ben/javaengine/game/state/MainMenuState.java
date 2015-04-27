package com.ben.javaengine.game.state;

import com.ben.javaengine.Controller;
import com.ben.javaengine.game.event.AbstractEvent;
import com.ben.javaengine.game.event.menu.NextOptionEvent;
import com.ben.javaengine.game.event.menu.PreviousOptionEvent;
import com.ben.javaengine.game.event.menu.SelectOptionEvent;
import com.ben.javaengine.menubuttons.NavigableListButton;
import com.ben.javaengine.menubuttons.mainmenu.ExitButton;
import com.ben.javaengine.menubuttons.mainmenu.OptionsButton;
import com.ben.javaengine.menubuttons.mainmenu.StartButton;

public class MainMenuState extends AbstractMenuState {
	
	public MainMenuState() {
		super();
	}
	
	public void selectOption() {
		Controller.getInstance().updateState(((NavigableListButton) options.get(selectedIndex)).getNextState());
	}
	
	public void takeAction(AbstractEvent e) {
		if(e instanceof NextOptionEvent) {
			nextOption();
		} else if(e instanceof PreviousOptionEvent) {
			previousOption();
		} else if(e instanceof SelectOptionEvent) {
			selectOption();
		}
	}

	@Override
	protected void populateOptions() {
		options.add(new StartButton());
		options.add(new OptionsButton());
		options.add(new ExitButton());
	}
}
