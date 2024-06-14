package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class FrontPageFormController {

    public AnchorPane fpContext;

    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("LoginForm");
    }


    void loadUi(String filName) throws IOException {
        URL resource = getClass().getResource("../view/" + filName + ".fxml");
        Parent load = FXMLLoader.load(resource);
        fpContext.getChildren().clear();
        fpContext.getChildren().add(load);
    }
}