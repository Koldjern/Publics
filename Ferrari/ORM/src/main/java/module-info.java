module com.orm {
    requires com.rki;
    requires transitive java.sql;
    exports com.orm.functionalinterfaces.actions;
    exports com.orm.functionalinterfaces.functions;
    exports com.orm.reflect;

}