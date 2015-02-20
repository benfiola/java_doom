package com.ben.javaengine.graphics.canvas;

import java.awt.Color;
import java.awt.Graphics;

import org.apache.log4j.Logger;

import com.ben.javaengine.graphics.converter.RelativeTopDownConverter;
import com.ben.javaengine.graphics.entitiies.AbstractGraphicData;
import com.ben.javaengine.logic.map.Map;
import com.ben.javaengine.logic.player.Player;
import com.ben.javaengine.map.entities.Sector;
import com.ben.javaengine.map.entities.Wall;
import com.ben.javaengine.utils.Rounder;

public class RelativeTopDownCanvas extends AbstractGameCanvas {
	private static final Logger LOG = Logger.getLogger(RelativeTopDownCanvas.class);

	private static final long serialVersionUID = 1L;
	
	public RelativeTopDownCanvas() {
		super();
		backgroundColor = Color.darkGray;
		converter = new RelativeTopDownConverter(this);
	}
}
