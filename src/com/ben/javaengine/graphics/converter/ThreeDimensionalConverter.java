package com.ben.javaengine.graphics.converter;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.ben.javaengine.graphics.entitiies.AbstractGraphicData;
import com.ben.javaengine.logic.LogicMain;

public class ThreeDimensionalConverter extends AbstractLogicDataConverter {
	
	public ThreeDimensionalConverter(JPanel panel) {
		super(panel);
	}
	
	@Override
	public List<AbstractGraphicData> convert(LogicMain data) {
		List<AbstractGraphicData> toReturn = new ArrayList<AbstractGraphicData>();
		
		return toReturn;
	}

}
