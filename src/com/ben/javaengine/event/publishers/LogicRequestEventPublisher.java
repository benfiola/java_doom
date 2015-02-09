package com.ben.javaengine.event.publishers;

import org.apache.log4j.Logger;

import com.ben.javaengine.event.events.AbstractEvent;
import com.ben.javaengine.event.events.LogicRequestEvent;
import com.ben.javaengine.event.listeners.LogicRequestEventListener;

public class LogicRequestEventPublisher extends AbstractEventPublisher {	
	private static Logger LOG = Logger.getLogger(LogicRequestEventPublisher.class);

	@Override
	public void publish(AbstractEvent e) {
		for(Object sub : subs) {
			if(sub instanceof LogicRequestEventListener && e instanceof LogicRequestEvent) {
				LogicRequestEventListener castedSub = (LogicRequestEventListener) sub;
				LogicRequestEvent castedEvent = (LogicRequestEvent) e;
				castedSub.onLogicRequestEvent(castedEvent);
			}
		}
	}

	@Override
	public void subscribe(Object obj) {
		subs.add(obj);
	}

	@Override
	public Class<? extends AbstractEvent> getEventClass() {
		return LogicRequestEvent.class;
	}
}
