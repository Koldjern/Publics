module com.data {
    requires transitive com.model;
    requires java.sql;
    requires com.microsoft.sqlserver.jdbc;
    requires com.rki;
    requires com.orm;
    exports com.data;
    exports com.data.actions;
    exports com.data.actions.specifics;
    exports com.data.dao;
    exports com.data.dao.interfaces;
    exports com.data.actions.general;

}