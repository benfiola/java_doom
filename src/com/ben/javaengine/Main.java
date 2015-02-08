package com.ben.javaengine;

import com.ben.javaengine.controllers.Controller;

public class Main {
	public static void main(String[] args) {
		Controller c = new Controller();
		while(c.isRunning()) {}
		System.exit(c.getStatus());
	}
}
