package com.ben.javaengine.event.managers;

import java.lang.reflect.Constructor;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.reflections.Reflections;

import com.ben.javaengine.event.events.AbstractEvent;
import com.ben.javaengine.event.publishers.AbstractEventPublisher;

public class EventManager {
	public static final Logger LOG = Logger.getLogger(EventManager.class);

	private static EventManager em;

	public ConcurrentHashMap<Class<? extends AbstractEvent>, AbstractEventPublisher> pubs;

	protected EventManager() {
		pubs = new ConcurrentHashMap<Class<? extends AbstractEvent>, AbstractEventPublisher>();
		Reflections reflections = new Reflections(AbstractEventPublisher.class
				.getPackage().getName());
		Set<Class<? extends AbstractEventPublisher>> pubClasses = reflections
				.getSubTypesOf(AbstractEventPublisher.class);
		for (Class<? extends AbstractEventPublisher> pubClass : pubClasses) {
			try {
				Constructor<? extends AbstractEventPublisher> toConstruct = pubClass
						.getConstructor();
				AbstractEventPublisher constructed = toConstruct.newInstance();
				pubs.put(constructed.getEventClass(), constructed);
			} catch (Exception e) {
				LOG.error(e);
				continue;
			}
		}
	}

	private AbstractEventPublisher getPublisher(
			Class<? extends AbstractEvent> event) {
		return pubs.get(event);
	}

	protected static EventManager getInstance() {
		if (em == null) {
			em = new EventManager();
		}
		return em;
	}

	public static void publish(final AbstractEvent e) {
		EventManager instance = EventManager.getInstance();
		final AbstractEventPublisher pub = instance.getPublisher(e.getClass());
		if (pub != null) {
			pub.publish(e);
		}

	}

	public static void subscribe(Class<? extends AbstractEvent> eventClass,
			Object o) {
		EventManager instance = EventManager.getInstance();
		AbstractEventPublisher pub = instance.getPublisher(eventClass);
		if (pub != null) {
			pub.subscribe(o);
		}
	}
}
