package com.ben.javaengine.display.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import com.ben.javaengine.menubuttons.ConfigurableButton;

public class ConfigurableOptionButton extends JPanel {
	private Color backgroundColor;
	
	private static final long serialVersionUID = 1L;
	
	public ConfigurableOptionButton(ConfigurableButton value, Color backgroundColor, Color textColor) {
		super();
		this.setLayout(new GridBagLayout());
		this.backgroundColor = backgroundColor;
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.25;
		c.weighty = 0.25;
		c.fill = GridBagConstraints.BOTH;
		add(new CenteredTextLabel(value.getProperty().getCanonicalName(), textColor), c);
		
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0.25;
		c.weighty = 0.25;
		c.fill = GridBagConstraints.BOTH;
		add(new CenteredTextLabel(value.getNewValueString(), textColor), c);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(backgroundColor);
		g.fillRect(0 , 0, getWidth(), getHeight());
	}
}