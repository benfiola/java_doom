package com.ben.javaengine.event.publishers;

import java.util.HashSet;

import org.apache.log4j.Logger;

import com.ben.javaengine.event.events.AbstractEvent;

public abstract class AbstractEventPublisher {
	private static Logger LOG = Logger.getLogger(AbstractEventPublisher.class);

	protected HashSet<Object> subs;
	
	public AbstractEventPublisher() {
		subs = new HashSet<Object>();
	}
	
	abstract public Class<? extends AbstractEvent> getEventClass();
	abstract public void publish(AbstractEvent e);
	abstract public void subscribe(Object obj);
}
