package com.ben.javaengine.display.converter;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.apache.log4j.Logger;

import com.ben.javaengine.display.components.CenteredTextLabel;
import com.ben.javaengine.display.components.NavigableFooterOptionButton;
import com.ben.javaengine.display.components.Spacer;
import com.ben.javaengine.game.state.AbstractMenuState;
import com.ben.javaengine.options.AbstractOption;
import com.ben.javaengine.options.NavigableFooterOption;

abstract public class AbstractMenuStateConverter extends AbstractStateConverter {
	private static final Logger LOG = Logger
			.getLogger(MainMenuStateConverter.class);

	protected static final Integer TITLE_HEIGHT = 2;
	protected static final Color PANEL_COLOR = Color.GRAY;
	protected static final Color MENU_BACKGROUND_COLOR = Color.BLACK;
	protected static final Color SELECTED_COLOR = Color.BLUE;
	protected static final Color EDITING_COLOR = Color.RED;
	protected static final Color MENU_TEXT_COLOR = Color.WHITE;
	protected static final Color TITLE_COLOR = Color.RED;

	private AbstractMenuState state;
	public String title;

	public AbstractMenuStateConverter(AbstractMenuState state,
			JPanel contentPane, String title) {
		super(contentPane);
		this.title = title;
		this.state = state;
	}

	abstract protected Color getOptionBackgroundColor(AbstractOption option);

	protected void createHeader() {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = TITLE_HEIGHT;
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.fill = GridBagConstraints.BOTH;
		contentPane.add(new Spacer(), c);

		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = TITLE_HEIGHT;
		c.weightx = 0.9;
		c.weighty = 0.9;
		c.fill = GridBagConstraints.BOTH;
		contentPane.add(new CenteredTextLabel(title, TITLE_COLOR), c);

		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = TITLE_HEIGHT;
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.fill = GridBagConstraints.BOTH;
		contentPane.add(new Spacer(), c);
	}

	protected void createFooter() {
		List<NavigableFooterOption> footerOptions = new ArrayList<NavigableFooterOption>();
		Integer listItems = 0;
		for (AbstractOption option : state.getOptions()) {
			if (option instanceof NavigableFooterOption) {
				footerOptions.add((NavigableFooterOption) option);
			} else {
				listItems++;
			}
		}
		int gridY = listItems + TITLE_HEIGHT + 1;
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridwidth = 3;
		c.gridheight = 1;
		c.gridy = gridY++;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.5;
		c.weighty = 0.5;
		contentPane.add(new Spacer(), c);
		
		if (footerOptions.isEmpty()) {
			c = new GridBagConstraints();
			c.gridx = 0;
			c.gridwidth = 3;
			c.gridheight = 1;
			c.gridy = gridY;
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 0.5;
			c.weighty = 0.5;
			contentPane.add(new Spacer(), c);
		}
		if (footerOptions.size() == 1) {
			c = new GridBagConstraints();
			c.gridx = 0;
			c.gridwidth = 1;
			c.gridheight = 1;
			c.gridy = gridY;
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 0.5;
			c.weighty = 0.5;
			contentPane.add(new Spacer(), c);

			c = new GridBagConstraints();
			c.gridx = 2;
			c.gridwidth = 1;
			c.gridheight = 1;
			c.gridy = gridY;
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 0.5;
			c.weighty = 0.5;
			contentPane.add(new Spacer(), c);

			c = new GridBagConstraints();
			NavigableFooterOption curr = footerOptions.get(0);
			c.gridx = 1;
			c.gridwidth = 1;
			c.gridheight = 1;
			c.gridy = gridY;
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 0.5;
			c.weighty = 0.5;
			contentPane.add(
					new NavigableFooterOptionButton(curr,
							getOptionBackgroundColor(curr), MENU_TEXT_COLOR), c);
		}
		if (footerOptions.size() == 2) {
			c = new GridBagConstraints();
			NavigableFooterOption curr = footerOptions.get(0);
			c.gridx = 0;
			c.gridwidth = 1;
			c.gridheight = 1;
			c.gridy = gridY;
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 0.5;
			c.weighty = 0.5;
			contentPane.add(new NavigableFooterOptionButton(curr,
					getOptionBackgroundColor(curr), MENU_TEXT_COLOR), c);

			c = new GridBagConstraints();
			c.gridx = 1;
			c.gridwidth = 1;
			c.gridheight = 1;
			c.gridy = gridY;
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 0.5;
			c.weighty = 0.5;
			contentPane.add(new Spacer(), c);

			c = new GridBagConstraints();
			curr = footerOptions.get(1);
			c.gridx = 2;
			c.gridwidth = 1;
			c.gridheight = 1;
			c.gridy = gridY;
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 0.5;
			c.weighty = 0.5;
			contentPane.add(new NavigableFooterOptionButton(curr,
					getOptionBackgroundColor(curr), MENU_TEXT_COLOR), c);

		}
	}
}
