package com.orm.reflect;

import com.orm.ParamAndMethod;
import com.orm.ParamObject;
import com.orm.functionalinterfaces.functions.FuncOne;
import com.orm.functionalinterfaces.functions.FuncZero;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Sql {
    //get
    public static <T> T query(CallableStatement cs, FuncZero<T> constructor) throws Exception {
        return executeQuery(cs, constructor).getFirst();
    }
    //get with param
    public static <T, U> T query(CallableStatement cs, FuncZero<T> constructor, U param) throws Exception {
        return executeQuery(cs, constructor, param).getFirst();
    }
    //get all with param
    public static <T, U> List<T> executeQuery(CallableStatement cs, FuncZero<T> constructor, U param) throws Exception {
        return getQuery(setQuery(cs, param, PreparedStatement::executeQuery), constructor);
    }

    //get all
    public static <T> List<T> executeQuery(CallableStatement cs, FuncZero<T> constructor) throws Exception {
        return getQuery(setQuery(cs, constructor.invoke(), PreparedStatement::executeQuery), constructor);
    }
    //set
    public static <T> boolean execute(CallableStatement cs, T param) throws Exception {
        return setQuery(cs, param, (c) -> c.executeUpdate() > 0);
    }
    //sets sql values
    private static <T> List<T> getQuery(ResultSet resultSet, FuncZero<T> constructor) throws Exception {
        ArrayList<T> returnResult = new ArrayList<>();
        ResultSetMetaData data = resultSet.getMetaData();
        ArrayList<String> params = new ArrayList<>();
        int columns = data.getColumnCount();
        for (int i = 1; i <= columns; i++)
            params.add(new String(data.getColumnName(i)));
        T t = constructor.invoke();
        List<ParamAndMethod> uses = setup(t, params, false,1, "set");
        while (resultSet.next()) {
            t = constructor.invoke();
            for(ParamAndMethod pm : uses)
                pm.invokeAction(resultSet, t);
            returnResult.add(t);
        }
        return returnResult;
    }
    //sets class values
    private static <T, U> U setQuery(CallableStatement cs, T param, FuncOne<U, CallableStatement> query)
            throws Exception {
        //Class<?> t = param.getClass();
        ArrayList<String> params = new ArrayList<>();
        ParameterMetaData data = cs.getParameterMetaData();
        int amount = data.getParameterCount();
        if (amount != 0) {
            for (Method m : param.getClass().getDeclaredMethods())
                if(m.getReturnType() != void.class)
                    params.add(m.getName().replace("get", ""));
            List<ParamAndMethod> uses = setup(param, params, true, 0, "get");
            if (uses.size() == amount) {
                uses.forEach(x -> {
                    try {
                        x.invokeAction(cs);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }
        return query.invoke(cs);
    }
    //gives the set /get params of sql and classes
    private static <T> List<ParamAndMethod> setup(T t, List<String> params, boolean set, int count, String getset) throws Exception {
        ArrayList<ParamAndMethod> uses = new ArrayList<>();
        for (Method m : t.getClass().getMethods())
            for (String p : params) {
                if ((getset + p).toLowerCase().equals(m.getName().toLowerCase()) && m.getParameterCount() == count) {
                    if(set) {
                        Type type = m.getReturnType();
                        if (type.equals(Integer.class) || type.equals(int.class))
                            uses.add(new ParamAndMethod<>(
                                    //passes the get method
                                    getParam(() -> (Integer) m.invoke(t), p),
                                    //uses the column int, get method result, and CS
                                    (String a, Integer b, CallableStatement c) -> c.setInt(a, b)));
                        else if (type.equals(String.class)) 
                            uses.add(new ParamAndMethod<>(
                                    getParam(() -> (String) m.invoke(t), p),
                                    (String a, String b, CallableStatement c) -> c.setString(a, b)));
                        else if (type.equals(Date.class))
                            uses.add(new ParamAndMethod<>(
                                    getParam(() -> (Date) m.invoke(t), p),
                                    (String a, Date b, CallableStatement c) -> c.setDate(a, b)));
                        else if (type.equals(Double.class) || type.equals(double.class))
                            uses.add(new ParamAndMethod<>(
                                    getParam(() -> (Double) m.invoke(t), p),
                                    (String a, Double b, CallableStatement c) -> c.setDouble(a, b)));
                        else if (type.equals(Byte[].class) || type.equals(byte[].class))
                            uses.add(new ParamAndMethod<>(
                                    getParam(() -> (byte[]) m.invoke(t), p),
                                    (String a, byte[] b, CallableStatement c) -> c.setBytes(a, b)));
                    }
                    else {
                        Type type = m.getParameters()[0].getType();
                        if (type.equals(Integer.class) || type.equals(int.class))
                            uses.add(new ParamAndMethod<>(
                                            new ParamObject<>(p),
                                            (String a, T b, ResultSet c) -> m.invoke(b, c.getInt(a))));
                        else if (type.equals(String.class))
                            uses.add(new ParamAndMethod<>(
                                            new ParamObject<>(p),
                                            (String a, T b, ResultSet c) -> m.invoke(b, c.getString(a))));
                        else if (type.equals(Date.class))
                            uses.add(new ParamAndMethod<>(
                                            new ParamObject<>(p),
                                            (String a, T b, ResultSet c) -> m.invoke(b, c.getDate(a))));
                        else if (type.equals(Double.class) || type.equals(double.class))
                            uses.add(new ParamAndMethod<>(
                                    new ParamObject<>(p),
                                    (String a, T b, ResultSet c) -> m.invoke(b, c.getDouble(a))));
                        else if (type.equals(Byte[].class) || type.equals(byte[].class))
                            uses.add(new ParamAndMethod<>(
                                    new ParamObject<>(p),
                                    (String a, T b, ResultSet c) -> m.invoke(b, c.getBytes(a))));
                    }
                }
            }
        return uses;
    }
    private static <T> ParamObject<T> getParam(FuncZero<T> func, String name) throws Exception {
        return new ParamObject<>(name, func.invoke());
    }
}
