package com.ben.javaengine.game.state;

import com.ben.javaengine.Controller;
import com.ben.javaengine.game.event.AbstractEvent;
import com.ben.javaengine.game.event.menu.NextOptionEvent;
import com.ben.javaengine.game.event.menu.PreviousOptionEvent;
import com.ben.javaengine.game.event.menu.SelectOptionEvent;
import com.ben.javaengine.mainmenu.Exit;
import com.ben.javaengine.mainmenu.Options;
import com.ben.javaengine.mainmenu.Start;
import com.ben.javaengine.options.NavigableListOption;

public class MainMenuState extends AbstractMenuState {
	
	public MainMenuState() {
		super();
	}
	
	public void selectOption() {
		Controller.getInstance().updateState(((NavigableListOption) options.get(selectedIndex)).nextState());
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
		options.add(new Start());
		options.add(new Options());
		options.add(new Exit());
	}
}
