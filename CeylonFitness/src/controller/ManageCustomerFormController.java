package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ManageCustomerFormController {
    public JFXTextField txtCusId1;
    public JFXTextField txtCusName1;
    public JFXTextField txtCusAddress1;
    public JFXTextField txtCusContact;
    public JFXTextField txtCusId2;
    public JFXTextField txtCusName2;
    public JFXTextField txtCusAddress2;
    public JFXTextField txtCusContact2;
    public TableColumn colCusId;
    public TableColumn colCusName;
    public TableColumn colCusAddress;
    public TableColumn colCusContact;
    public TableColumn colOption;
    public TableView<Customer> tblManageCustomer;




    public void addCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Customer customer = new Customer(
                txtCusId1.getText(),txtCusName1.getText(),txtCusAddress1.getText(),txtCusContact.getText()
        );

        try {

            if (CrudUtil.execute("INSERT INTO Customer VALUES(?,?,?,?)",customer.getId(),customer.getName(),customer.getAddress(),customer.getContact())){
                new Alert(Alert.AlertType.CONFIRMATION,"Saved...").show();
            }



        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Something went wrong!...").show();
        }

loadAllCustomers();

    }

    public void modifyCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Customer customer = new Customer(
                txtCusId2.getText(),txtCusName2.getText(),txtCusAddress2.getText(),txtCusContact2.getText());



        try {

            boolean isUpdated = CrudUtil.execute("UPDATE Customer SET name=? , address=? , contact=? WHERE customerId=?", customer.getName(), customer.getAddress(), customer.getContact(), customer.getId());
            if(isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION,"UPDATED").show();
            }

            else {
                new Alert(Alert.AlertType.WARNING,"Try Again").show();
            }

        }catch (SQLException | ClassNotFoundException e){
        }
        loadAllCustomers();
    }

    public void deleteCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {


            if (CrudUtil.execute("DELETE FROM customer WHERE customerId=?",txtCusId2.getText())) {
                new Alert(Alert.AlertType.CONFIRMATION, "DELETED").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
        }

        loadAllCustomers();
    }

    public void cusIdOnAction(ActionEvent actionEvent) {
        search();
    }

    private void search () {
        try {
            ResultSet result = CrudUtil.execute("SELECT * FROM customer WHERE customerId=?",txtCusId2.getText());
            if (result.next()) {
                txtCusName2.setText(result.getString(2));
                txtCusAddress2.setText(result.getString(3));
                txtCusContact2.setText(String.valueOf(result.getDouble(4)));


            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Result").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void initialize(){
        colCusId.setCellValueFactory(new PropertyValueFactory("id"));
        colCusName.setCellValueFactory(new PropertyValueFactory("name"));
        colCusAddress.setCellValueFactory(new PropertyValueFactory("address"));
        colCusContact.setCellValueFactory(new PropertyValueFactory("contact"));
        try {
            loadAllCustomers();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadAllCustomers() throws ClassNotFoundException, SQLException {



        ResultSet result = CrudUtil.execute("SELECT * FROM Customer");

        ObservableList<Customer> obList = FXCollections.observableArrayList();
        while (result.next()){
            obList.add(
                    new Customer(
                            result.getString("customerId"),
                            result.getString("name"),
                            result.getString("address"),
                            result.getString("contact")

                    )
            );
        }
        tblManageCustomer.setItems(obList);


    }

}
