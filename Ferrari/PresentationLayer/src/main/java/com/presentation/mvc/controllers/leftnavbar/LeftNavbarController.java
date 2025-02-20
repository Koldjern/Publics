package com.presentation.mvc.controllers.leftnavbar;

import com.model.entities.Employee;
import com.model.enums.Occupation;
import com.presentation.mvc.controllers.Controller;
import com.presentation.mvc.controllers.agreement.ClosedAgreementsController;
import com.presentation.mvc.controllers.agreement.OpenAgreementsController;
import com.presentation.mvc.controllers.customers.CustomersController;
import com.presentation.mvc.controllers.employee.AccountController;
import com.presentation.mvc.controllers.employee.EmployeesController;
import com.presentation.mvc.controllers.vehicle.AllVehiclesController;
import com.presentation.mvc.models.leftnavbar.NavButtonModel;
import com.presentation.mvc.views.leftnavbar.LeftNavBarView;
import com.presentation.mvc.views.leftnavbar.NavButtonView;
import com.presentation.tools.facade.Facade;

import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
//anders
//makes all the controllers / views / models needed to make the navbar, takes into consideration who's logged in
public class LeftNavbarController extends Controller {
    private LeftNavBarView view;

    public LeftNavbarController() {
        Employee employee = Facade.getInstance().getLoggedIn();
        List<NavButtonController> controllers = new ArrayList<>();
        if(employee.getOccupation() == Occupation.Salesman || employee.getOccupation() == Occupation.Manager) {
            controllers.add(new NavButtonController(new NavButtonModel("FontAwesome", "USERS", "Ansatte"), (event) -> navigate("Employees")));
            controllers.add(new NavButtonController(new NavButtonModel("FontAwesome", "CAR", "Biler"), (event) -> navigate("Vehicles")));
            controllers.add(new NavButtonController(new NavButtonModel("FontAwesome", "FOLDER_OPEN", "Åbne Aftaler"), (event) -> navigate("AgreementsOpen")));
            controllers.add(new NavButtonController(new NavButtonModel("FontAwesome", "FOLDER", "Lukkede Aftaler"), (event) -> navigate("AgreementsClosed")));
            controllers.add(new NavButtonController(new NavButtonModel("FontAwesome", "USERS", "Kunder"), (event) -> navigate("Customers")));
            controllers.add(new NavButtonController(new NavButtonModel("FontAwesome", "USER", "Din konto"), (event) -> navigate("Account")));
            controllers.add(new NavButtonController(new NavButtonModel("FontAwesome", "SIGN_OUT", "Log Af"), (event) -> navigate("Login")));
        }
        if(employee.getOccupation() == Occupation.Admin) {
            controllers.add(new NavButtonController(new NavButtonModel("FontAwesome", "USERS", "Ansatte"), (event) -> navigate("Employees")));
            controllers.add(new NavButtonController(new NavButtonModel("FontAwesome", "USERS", "Kunder"),(event) -> navigate("Customers")));
            controllers.add(new NavButtonController(new NavButtonModel("FontAwesome", "USER", "Din konto"), (event) -> navigate("Account")));
            controllers.add(new NavButtonController(new NavButtonModel("FontAwesome", "SIGN_OUT", "Log Af"), (event) -> navigate("Login")));
            
        }
        List<NavButtonView> views = new ArrayList<>();
        for (NavButtonController c : controllers)
            views.add(c.getView());
        view = new LeftNavBarView(views);
    }
    public void navigate(String nav) {
        switch (nav) {
            case "Login" -> Facade.getInstance().logOff();
            case "Employees" -> Facade.getInstance().setCenter(new EmployeesController());
            case "Account" -> Facade.getInstance().setCenter(new AccountController());
            case "Vehicles" -> Facade.getInstance().setCenter(new AllVehiclesController());
            case "Customers" -> Facade.getInstance().setCenter(new CustomersController());
            case "AgreementsOpen" -> Facade.getInstance().setCenter(new OpenAgreementsController());
            case "AgreementsClosed" -> Facade.getInstance().setCenter(new ClosedAgreementsController());
            default -> {
                return;
            }
        }
    }
    @Override
    public Pane getView() {
        return view;
    }
}
