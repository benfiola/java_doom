package com.ben.javaengine.event.publishers;

import java.util.HashSet;

import com.ben.javaengine.event.events.AbstractEvent;

public abstract class AbstractEventPublisher {
	protected HashSet<Object> subs;
	
	public AbstractEventPublisher() {
		subs = new HashSet<Object>();
	}
	
	abstract public Class<? extends AbstractEvent> getEventClass();
	abstract public void publish(AbstractEvent e);
	abstract public void subscribe(Object obj);
}
