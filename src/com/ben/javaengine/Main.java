package com.ben.javaengine;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/*
 * This is the Main class of our application.  Here we have
 * the main application loop, which will keep our application running
 * until the Controller signals the application to exit.
 */
public class Main {
	private static Logger LOG = Logger.getLogger(Main.class);
	public static void main(String[] args) {
		BasicConfigurator.configure();
		LOG.info("Initializing");
		while(!Controller.getInstance().wantsToExit()) {
			
		}
		LOG.info("Exiting");
		System.exit(Controller.getInstance().getExitCode());
	}
}
