package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AllCustomersFormController {


    public TableView<Customer> tblAllCustomer;
    public TableColumn colAllCusId;
    public TableColumn colAllCusName;
    public TableColumn colAllCusAddress;
    public TableColumn colAllCusContact;
    public TableColumn colAllCusOption;

    public void initialize(){
        colAllCusId.setCellValueFactory(new PropertyValueFactory("id"));
        colAllCusName.setCellValueFactory(new PropertyValueFactory("name"));
        colAllCusAddress.setCellValueFactory(new PropertyValueFactory("address"));
        colAllCusContact.setCellValueFactory(new PropertyValueFactory("contact"));
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
        tblAllCustomer.setItems(obList);


    }

}
