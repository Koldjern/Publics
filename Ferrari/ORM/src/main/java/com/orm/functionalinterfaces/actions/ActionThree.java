package com.orm.functionalinterfaces.actions;

@FunctionalInterface
public interface ActionThree<T, U, V> {
    public void invoke(T t, U u, V v) throws Exception;

}
