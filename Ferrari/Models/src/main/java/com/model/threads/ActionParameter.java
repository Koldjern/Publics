package com.model.threads;
@FunctionalInterface
public interface ActionParameter<T> {
    public void action(T parameter);
}
