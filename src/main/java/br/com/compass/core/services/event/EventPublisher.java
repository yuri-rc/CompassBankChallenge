package br.com.compass.core.services.event;

import java.util.ArrayList;
import java.util.List;

public class EventPublisher {
    private final List<EventListener> listeners = new ArrayList<>();

    public void addListener(EventListener listener) {
        listeners.add(listener);
    }

    public void publish(Object event) {
        for (EventListener listener : listeners) {
            if (listener.getClass().getGenericInterfaces()[0].getTypeName().contains(event.getClass().getSimpleName())) {
                listener.onEvent(event);
            }
        }
    }
}