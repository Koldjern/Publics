package com.orm.functionalinterfaces.functions;

@FunctionalInterface
public interface FuncTwo<T, U, V>{
    public T invoke(U u, V v) throws Exception;
}
