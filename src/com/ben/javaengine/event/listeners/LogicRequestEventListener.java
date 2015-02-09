package com.ben.javaengine.event.listeners;

import com.ben.javaengine.event.events.LogicRequestEvent;


public interface LogicRequestEventListener extends EventListener {
	
	public void onLogicRequestEvent(LogicRequestEvent e);
}
