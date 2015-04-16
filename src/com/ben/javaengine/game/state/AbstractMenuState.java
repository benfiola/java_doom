package com.ben.javaengine.game.state;

import java.util.ArrayList;
import java.util.List;

import com.ben.javaengine.options.AbstractOption;
import com.ben.javaengine.options.NavigableFooterOption;

abstract public class AbstractMenuState extends AbstractState {
	protected List<AbstractOption> options;
	protected Integer selectedIndex;
	
	public AbstractMenuState() {
		super();
		options = new ArrayList<AbstractOption>();
		selectedIndex = 0;
		populateOptions();
	}
	
	public List<AbstractOption> getOptions() {
		return options;
	}
	
	public void nextOption() {
		selectedIndex++;
		if(selectedIndex >= options.size()) {
			selectedIndex = selectedIndex - options.size();
		}
	}
	
	public void previousOption() {
		selectedIndex--;
		if(selectedIndex < 0) {
			selectedIndex = selectedIndex + options.size();
		}
	}
	
	public boolean isSelected(AbstractOption option) {
		return option.equals(options.get(selectedIndex));
	}
	
	abstract protected void populateOptions();
}
