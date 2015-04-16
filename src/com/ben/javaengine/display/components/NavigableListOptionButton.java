package com.ben.javaengine.display.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import com.ben.javaengine.options.NavigableListOption;

public class NavigableListOptionButton extends JPanel {
	private static final long serialVersionUID = 1L;
	private NavigableListOption option;
	private Color backgroundColor;
	private Color textColor;
	
	public NavigableListOptionButton(NavigableListOption option, Color backgroundColor, Color textColor) {
		super();
		this.option = option;
		this.backgroundColor = backgroundColor;
		this.textColor = textColor;
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.25;
		c.weighty = 0.5;
		c.fill = GridBagConstraints.BOTH;
		add(new Spacer(), c);
		
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.fill = GridBagConstraints.BOTH;
		add(new CenteredTextLabel(option.getValue(), textColor), c);
		
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		c.weightx = 0.25;
		c.weighty = 0.5;
		c.fill = GridBagConstraints.BOTH;
		add(new Spacer(), c);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(backgroundColor);
		g.fillRect(0, 0, getWidth(), getHeight());
	}
}