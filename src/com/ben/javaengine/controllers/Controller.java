package com.ben.javaengine.controllers;

import org.apache.log4j.Logger;

/*
 * This is the controller class of our application.  It should initialize
 * all the components of our application and facilitate communication between
 * the components.
 */
public class Controller {
	private static Logger LOG = Logger.getLogger(Controller.class);
	
	public Controller() {
		init();
	}

	public void init() {

	}
	
	public void quit(Integer status) {
		LOG.info("Exiting with status code : " + status);
		System.exit(status);
	}
}
