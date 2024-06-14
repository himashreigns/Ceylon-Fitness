package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class AssistantDashboardFormController {
    public AnchorPane assisDashboardContext;
    public AnchorPane assistContext;

    public void assisDashboardOnAction(ActionEvent actionEvent) {
    }

    public void assisCustomerOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("ManageCustomerForm");
    }

    public void assisSupplementOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("ManageSupplimentForm");
    }

    public void placeOrderOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("PlaceOrderForm");
    }

    public void employeeLogoutOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/LoginForm.fxml");
        Parent load = FXMLLoader.load(resource);
        assistContext.getChildren().clear();
        assistContext.getChildren().add(load);
    }

    public void importItemOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("ImportSupplimentForm");
    }

    void loadUi(String filName) throws IOException {
        URL resource = getClass().getResource("../view/" + filName + ".fxml");
        Parent load = FXMLLoader.load(resource);
        assisDashboardContext.getChildren().clear();
        assisDashboardContext.getChildren().add(load);
    }
}
