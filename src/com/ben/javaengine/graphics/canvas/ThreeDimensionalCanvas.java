package com.ben.javaengine.graphics.canvas;

import java.awt.Color;
import java.awt.Graphics;

import org.apache.log4j.Logger;

import com.ben.javaengine.graphics.converter.ThreeDimensionalConverter;

public class ThreeDimensionalCanvas extends AbstractGameCanvas {
	private static final Logger LOG = Logger.getLogger(ThreeDimensionalCanvas.class);

	private static final long serialVersionUID = 1L;
	
	public ThreeDimensionalCanvas() {
		super();
		converter = new ThreeDimensionalConverter(this);
	}
}
