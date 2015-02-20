package com.ben.javaengine.graphics.converter;

import java.util.List;

import javax.swing.JPanel;

import com.ben.javaengine.graphics.entitiies.AbstractGraphicData;
import com.ben.javaengine.logic.LogicMain;

abstract public class AbstractLogicDataConverter {
	protected JPanel panel;
	
	public AbstractLogicDataConverter(JPanel panel) {
		this.panel = panel;
	}
	
	abstract public List<AbstractGraphicData> convert(LogicMain data);
}
