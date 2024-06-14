package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    public JFXTextField userNameField;
    public JFXPasswordField passwordField;
    public AnchorPane loginFormContext;

    public void backToFrontPageOnAction(ActionEvent actionEvent) throws IOException {
        Stage window=(Stage)loginFormContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/FrontPageForm.fxml"))));
    }

    public void loginOnAction(ActionEvent actionEvent) throws IOException {

            //System.out.println("AAA");
            if(userNameField.getText().equals("admin") && passwordField.getText().equals("admin")){
                System.out.println("Admin In");
                Stage window=(Stage)loginFormContext.getScene().getWindow();
                window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/BlockForm.fxml"))));
            }
            else if(userNameField.getText().equals("recep") && passwordField.getText().equals("recep")){
                System.out.println("Receptionist In");
                Stage window=(Stage)loginFormContext.getScene().getWindow();
                window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/AssistantDashboardForm.fxml"))));
            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING,"Incorrect Username or Password");
                alert.show();
            }

        userNameField.clear();
        passwordField.clear();
    }
    }

