package com.model.threads;
@FunctionalInterface
public interface FunctionParam<T, U> {
    public T function(U u);
}
