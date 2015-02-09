package com.ben.javaengine.event.publishers;

import org.apache.log4j.Logger;

import com.ben.javaengine.event.events.AbstractEvent;
import com.ben.javaengine.event.events.KeyPressEvent;
import com.ben.javaengine.event.events.KeyReleaseEvent;
import com.ben.javaengine.event.listeners.KeyReleaseEventListener;

public class KeyReleaseEventPublisher extends AbstractEventPublisher {	
	private static Logger LOG = Logger.getLogger(KeyReleaseEventPublisher.class);

	@Override
	public void publish(AbstractEvent e) {
		for(Object sub : subs) {
			if(sub instanceof KeyReleaseEventListener && e instanceof KeyReleaseEvent) {
				KeyReleaseEventListener castedSub = (KeyReleaseEventListener) sub;
				KeyReleaseEvent castedEvent = (KeyReleaseEvent) e;
				castedSub.onKeyReleaseEvent(castedEvent);
			}
		}
	}

	@Override
	public void subscribe(Object obj) {
		subs.add(obj);
	}

	@Override
	public Class<? extends AbstractEvent> getEventClass() {
		return KeyReleaseEvent.class;
	}
}
