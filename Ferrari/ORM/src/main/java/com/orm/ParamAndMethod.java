package com.orm;

import com.orm.functionalinterfaces.actions.ActionThree;

public class ParamAndMethod<T, U> {
    private ParamObject<T> param;
    private ActionThree<String, T, U> action;

    public ParamAndMethod(ParamObject<T> param, ActionThree<String, T, U> action) {
        this.param = param;
        this.action = action;
    }
    public void invokeAction(U u) throws Exception {
        action.invoke(param.getName(), param.getValue(), u);
    }
    public void invokeAction(U u, T t) throws Exception {
        action.invoke(param.getName(), t, u);
    }
}
