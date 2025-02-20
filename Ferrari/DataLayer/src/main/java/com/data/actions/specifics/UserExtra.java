package com.data.actions.specifics;

// anders
//more than crud is needed
public interface UserExtra<T, U> {
    public T login(U login);
    public Boolean updateSelf(U update);
}
