package com.ben.javaengine.event.publishers;

import org.apache.log4j.Logger;

import com.ben.javaengine.event.events.AbstractEvent;
import com.ben.javaengine.event.events.WindowCloseEvent;
import com.ben.javaengine.event.listeners.WindowCloseEventListener;

public class WindowCloseEventPublisher extends AbstractEventPublisher {	
	private static Logger LOG = Logger.getLogger(WindowCloseEventPublisher.class);

	@Override
	public void publish(AbstractEvent e) {
		for(Object sub : subs) {
			if(sub instanceof WindowCloseEventListener && e instanceof WindowCloseEvent) {
				WindowCloseEventListener castedSub = (WindowCloseEventListener) sub;
				WindowCloseEvent castedEvent = (WindowCloseEvent) e;
				castedSub.onWindowCloseEvent(castedEvent);
			}
		}
	}

	@Override
	public void subscribe(Object obj) {
		subs.add(obj);
	}

	@Override
	public Class<? extends AbstractEvent> getEventClass() {
		return WindowCloseEvent.class;
	}
}
