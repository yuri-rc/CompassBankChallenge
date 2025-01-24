package br.com.compass.core.services.event;

public interface EventListener<T> {
    void onEvent(T event);
}