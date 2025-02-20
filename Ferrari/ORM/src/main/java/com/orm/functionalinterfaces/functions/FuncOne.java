package com.orm.functionalinterfaces.functions;

@FunctionalInterface
public interface FuncOne<T, U>{
    public T invoke(U u) throws Exception;
}
