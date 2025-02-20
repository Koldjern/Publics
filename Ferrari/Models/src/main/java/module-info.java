module com.model {
    requires com.rki;
    requires java.sql;
    exports com.model.entities;
    exports com.model.threads;
    exports com.model.enums;
    exports com.model.dtos.agreementclosed.set;
    exports com.model.dtos.agreementclosed.get;
    exports com.model.dtos.agreementopen.set;
    exports com.model.dtos.agreementopen.get;
    exports com.model.dtos.city.get;
    exports com.model.dtos.customer.get;
    exports com.model.dtos.customer.set;
    exports com.model.dtos.employee.get;
    exports com.model.dtos.employee.set;
    exports com.model.dtos.invoice.get;
    exports com.model.dtos.invoice.set;
    exports com.model.dtos.rate;
    exports com.model.dtos.vehicle.set;
    exports com.model.dtos.vehicle.get;
    exports com.model;


}