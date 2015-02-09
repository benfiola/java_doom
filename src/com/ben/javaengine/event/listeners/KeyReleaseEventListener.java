package com.ben.javaengine.event.listeners;

import com.ben.javaengine.event.events.KeyReleaseEvent;

public interface KeyReleaseEventListener extends EventListener {
	
	public void onKeyReleaseEvent(KeyReleaseEvent e);
}
