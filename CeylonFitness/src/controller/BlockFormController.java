package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class BlockFormController {
    public AnchorPane blockFormContext;
    public AnchorPane dashboardContext;

    public void dashboardOnAction(ActionEvent actionEvent) throws IOException {

    }

    public void customerOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("AllCustomersForm");
    }

    public void employeeOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("ManageEmployeeForm");
    }

    public void itemOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("AllSupplements");
    }

    public void supplierOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("ManageSupplierForm");
    }

    public void reportsOnAction(ActionEvent actionEvent) {
    }

    public void logoutOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/LoginForm.fxml");
        Parent load = FXMLLoader.load(resource);
        blockFormContext.getChildren().clear();
        blockFormContext.getChildren().add(load);
    }

    void loadUi(String filName) throws IOException {
        URL resource = getClass().getResource("../view/" + filName + ".fxml");
        Parent load = FXMLLoader.load(resource);
        dashboardContext.getChildren().clear();
        dashboardContext.getChildren().add(load);
    }

    public void ordersOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("ManageOrdersForm");
    }
}
