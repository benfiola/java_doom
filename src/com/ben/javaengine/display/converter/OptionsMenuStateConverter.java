package com.ben.javaengine.display.converter;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JPanel;

import org.apache.log4j.Logger;

import com.ben.javaengine.Controller;
import com.ben.javaengine.display.components.ConfigurableOptionButton;
import com.ben.javaengine.display.components.Spacer;
import com.ben.javaengine.game.event.options.EditOptionEvent;
import com.ben.javaengine.game.event.options.NextOptionEvent;
import com.ben.javaengine.game.event.options.PreviousOptionEvent;
import com.ben.javaengine.game.event.options.SelectOptionEvent;
import com.ben.javaengine.game.state.OptionsMenuState;
import com.ben.javaengine.options.AbstractOption;
import com.ben.javaengine.options.ConfigurableOption;

public class OptionsMenuStateConverter extends AbstractMenuStateConverter {
	private static final Logger LOG = Logger.getLogger(OptionsMenuStateConverter.class);
	private static final String TITLE = "Options";
	private OptionsMenuState state;

	public OptionsMenuStateConverter(OptionsMenuState state, JPanel contentPane) {
		super(state, contentPane, TITLE);
		this.state = state;
	}

	@Override
	protected void prepareLayout() {
		contentPane.setLayout(new GridBagLayout());
		createHeader();
		List<AbstractOption> options = state.getOptions();
		for(int x = 0; x < options.size(); x++) {
			AbstractOption currOption = options.get(x);
			if(currOption instanceof ConfigurableOption) {
				ConfigurableOption currConfOption = (ConfigurableOption) currOption;
				createRow(currConfOption, x);
			}
		}
		createFooter();
	}
	
	@Override
	protected Color getOptionBackgroundColor(AbstractOption option) {
		Color toReturn = MENU_BACKGROUND_COLOR;
		if(state.isSelected(option)) {
			toReturn = SELECTED_COLOR;
			if(state.isEditing()) {
				toReturn = EDITING_COLOR;
			}
		}
		return toReturn;
	}
	
	private void createRow(ConfigurableOption option, int index) {
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
		contentPane.add(new ConfigurableOptionButton(option, getOptionBackgroundColor(option), MENU_TEXT_COLOR) ,c);
		
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
		} else {
			Controller.getInstance().notifyOfEvent(new EditOptionEvent(e));
		}
	}
}
