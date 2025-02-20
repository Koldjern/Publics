package com.data.actions.general;

import java.util.ArrayList;
import java.util.List;
import com.model.threads.FunctionParam;

public interface ReadAll <T, U>{
    public T readAll(U parameter);
    public static <T, U> List<T> DtoToModels(List<U> list, FunctionParam<T,U> mixer) {
        List<T> result = new ArrayList<>();
        for(U t : list)
            result.add(mixer.function(t));
        return result;
    }
}
