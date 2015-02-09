package com.ben.javaengine.event.listeners;

import com.ben.javaengine.event.events.LogicResponseEvent;


public interface LogicResponseEventListener extends EventListener {
	
	public void onLogicResponseEvent(LogicResponseEvent e);
}
