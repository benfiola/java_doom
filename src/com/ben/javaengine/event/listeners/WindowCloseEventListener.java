package com.ben.javaengine.event.listeners;

import com.ben.javaengine.event.events.WindowCloseEvent;

public interface WindowCloseEventListener extends EventListener {
	
	public void onWindowCloseEvent(WindowCloseEvent e);
}
