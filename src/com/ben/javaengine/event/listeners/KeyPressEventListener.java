package com.ben.javaengine.event.listeners;

import com.ben.javaengine.event.events.KeyPressEvent;

public interface KeyPressEventListener extends EventListener {
	
	public void onKeyPressEvent(KeyPressEvent e);
}
