package com.data;

import com.model.threads.Action;
import com.model.threads.Function;
import com.model.threads.ObjectWithTimer;
import com.orm.functionalinterfaces.functions.FuncZero;
import com.orm.reflect.Sql;
import java.sql.*;
import java.util.List;
//anders
//creates the connection gives callablestatements to DAOs
public class ConnectionData {
    private ObjectWithTimer connectionTimer;
    private static final String CONNECTION_STRING = "jdbc:sqlserver://localhost:1433;" +
            "databaseName=FerrariDB;" +
            "integratedSecurity=true;" +
            "trustServerCertificate=true;";
    
    public ConnectionData() {
        //connectionTimer will check every 20 seconds if its been run in the meanwhile, if it hasn't it will disconnect the sql connection
        connectionTimer = new ObjectWithTimer(new Function() {
            @Override
            public Object function() {
                try {
                    return DriverManager.getConnection(CONNECTION_STRING);
                } catch (Exception e) {
                    return null;
                }
            }
        }, 20, new Action() {
            @Override
            public void action() {
                try {
                    ((Connection) connectionTimer.getValue()).close();
                } catch (Exception e) {
                }
            }
        });
    }
    //gets the SP string and returns call
    public CallableStatement makeCall(String spCall) {
        try {
            return ((Connection)connectionTimer.getValue()).prepareCall(spCall);
        }
        catch(Exception e) {
            return null;
        }
    }
    public <T> T querySingle(String spCall, FuncZero<T> constructor) {
        try {
            return Sql.query(makeCall(spCall), constructor);
        } catch (Exception e) {
            return null;
        }
    }
    public <T, U> T querySingle(String spCall, FuncZero<T> constructor, U param) {
        try {
            return Sql.query(makeCall(spCall), constructor, param);
        } catch (Exception e) {
            return null;
        }
    }
    public <T> List<T> queryMultiple(String spCall, FuncZero<T> constructor) {
        try {
            return Sql.executeQuery(makeCall(spCall), constructor);
        } catch (Exception e) {
            return null;
        }
    }
    public <T, U> List<T> queryMultiple(String spCall, FuncZero<T> constructor, U param) {
        try {
            return Sql.executeQuery(makeCall(spCall), constructor, param);
        } catch (Exception e) {
            return null;
        }
    }
    public <T> boolean executeQuery(String spCall, T param){
        try {
            return Sql.execute(makeCall(spCall), param);
        } catch (Exception e) {
            return false;
        }
    }
}
