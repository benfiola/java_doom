package com.ben.javaengine.event.publishers;

import org.apache.log4j.Logger;

import com.ben.javaengine.event.events.AbstractEvent;
import com.ben.javaengine.event.events.KeyPressEvent;
import com.ben.javaengine.event.events.WindowCloseEvent;
import com.ben.javaengine.event.listeners.KeyPressEventListener;
import com.ben.javaengine.event.listeners.WindowCloseEventListener;

public class KeyPressEventPublisher extends AbstractEventPublisher {	
	private static Logger LOG = Logger.getLogger(KeyPressEventPublisher.class);

	@Override
	public void publish(AbstractEvent e) {
		for(Object sub : subs) {
			if(sub instanceof KeyPressEventListener && e instanceof KeyPressEvent) {
				KeyPressEventListener castedSub = (KeyPressEventListener) sub;
				KeyPressEvent castedEvent = (KeyPressEvent) e;
				castedSub.onKeyPressEvent(castedEvent);
			}
		}
	}

	@Override
	public void subscribe(Object obj) {
		subs.add(obj);
	}

	@Override
	public Class<? extends AbstractEvent> getEventClass() {
		return KeyPressEvent.class;
	}
}
