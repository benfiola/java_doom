package com.ben.javaengine.event.publishers;

import org.apache.log4j.Logger;

import com.ben.javaengine.event.events.AbstractEvent;
import com.ben.javaengine.event.events.LogicResponseEvent;
import com.ben.javaengine.event.listeners.LogicResponseEventListener;

public class LogicResponseEventPublisher extends AbstractEventPublisher {	
	private static Logger LOG = Logger.getLogger(LogicResponseEventPublisher.class);

	@Override
	public void publish(AbstractEvent e) {
		for(Object sub : subs) {
			if(sub instanceof LogicResponseEventListener && e instanceof LogicResponseEvent) {
				LogicResponseEventListener castedSub = (LogicResponseEventListener) sub;
				LogicResponseEvent castedEvent = (LogicResponseEvent) e;
				castedSub.onLogicResponse(castedEvent);
			}
		}
	}

	@Override
	public void subscribe(Object obj) {
		subs.add(obj);
	}

	@Override
	public Class<? extends AbstractEvent> getEventClass() {
		return LogicResponseEvent.class;
	}
}
