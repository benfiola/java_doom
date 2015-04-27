package com.ben.javaengine.display.converter;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JPanel;

import com.ben.javaengine.Controller;
import com.ben.javaengine.display.components.NavigableListOptionButton;
import com.ben.javaengine.display.components.Spacer;
import com.ben.javaengine.game.event.menu.NextOptionEvent;
import com.ben.javaengine.game.event.menu.PreviousOptionEvent;
import com.ben.javaengine.game.event.menu.SelectOptionEvent;
import com.ben.javaengine.game.state.MainMenuState;
import com.ben.javaengine.menubuttons.AbstractButton;
import com.ben.javaengine.menubuttons.NavigableListButton;

public class MainMenuStateConverter extends AbstractMenuStateConverter {
	//private static final Logger LOG = Logger.getLogger(MainMenuStateConverter.class);
	private static final String TITLE = "Main Menu";
	private MainMenuState state;
	
	public MainMenuStateConverter(MainMenuState state, JPanel contentPane) {
		super(state, contentPane, TITLE);
		this.state = state;
	}
	
	@Override
	protected void prepareLayout() {		
		contentPane.setLayout(new GridBagLayout());
		createHeader();
		List<AbstractButton> menuOptions = state.getOptions();
		for(int x = 0; x < menuOptions.size(); x++) {
			AbstractButton currOption = menuOptions.get(x);
			if(currOption instanceof NavigableListButton) {
				NavigableListButton currNavOption = (NavigableListButton) currOption;
				createRow(currNavOption, x);
			}
		}
		createFooter();
	}
	
	@Override
	protected Color getOptionBackgroundColor(AbstractButton option) {
		if(state.isSelected(option)) {
			return SELECTED_COLOR;
		}
		return MENU_BACKGROUND_COLOR;
	}
	
	private void createRow(NavigableListButton option, int index) {
		int gridY = index + TITLE_HEIGHT;
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = gridY;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.1;
		c.weighty = 0.1;
		contentPane.add(new Spacer(), c);
		
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = gridY;
		c.weightx = 0.9;
		c.weighty = 0.9;
		c.fill = GridBagConstraints.BOTH;
		contentPane.add(new NavigableListOptionButton(option, getOptionBackgroundColor(option), MENU_TEXT_COLOR) ,c);
		
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = gridY;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.1;
		c.weighty = 0.1;
		contentPane.add(new Spacer(), c);
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			Controller.getInstance().notifyOfEvent(new PreviousOptionEvent());
		} else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			Controller.getInstance().notifyOfEvent(new NextOptionEvent());
		} else if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			Controller.getInstance().notifyOfEvent(new SelectOptionEvent());
		}
	}
}
