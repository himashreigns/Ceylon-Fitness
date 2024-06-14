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
import model.Supplier;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ManageSupplierFormController {
    public JFXTextField txtSupName;
    public JFXTextField txtSupId;
    public JFXTextField txtSupAddress;
    public JFXTextField txtSupContact;
    public JFXTextField txtSupId2;
    public JFXTextField txtSupName2;
    public JFXTextField txtSupAddress2;
    public JFXTextField txtSupContact2;
    public TableView tblManageSup;
    public TableColumn colSupId;
    public TableColumn colSupName;
    public TableColumn colSupAddress;
    public TableColumn colSupContact;

    public void addSupOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Supplier supplier = new Supplier(
                txtSupId.getText(),txtSupName.getText(),txtSupAddress.getText(),txtSupContact.getText()
        );

        try {

            if (CrudUtil.execute("INSERT INTO Supplier VALUES(?,?,?,?)",supplier.getId(),supplier.getName(),supplier.getAddress(),supplier.getContact())){
                new Alert(Alert.AlertType.CONFIRMATION,"Saved...").show();
            }



        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Something went wrong!...").show();
        }

        loadAllCustomers();

    }

    public void modifySupOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Supplier supplier = new Supplier(
                txtSupId2.getText(),txtSupName2.getText(),txtSupAddress2.getText(),txtSupContact2.getText());



        try {

            boolean isUpdated = CrudUtil.execute("UPDATE Supplier SET name=? , address=? , contact=? WHERE supplierId=?", supplier.getName(), supplier.getAddress(), supplier.getContact(), supplier.getId());
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

    public void DeleteSupOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {


            if (CrudUtil.execute("DELETE FROM Supplier WHERE supplierId=?",txtSupId2.getText())) {
                new Alert(Alert.AlertType.CONFIRMATION, "DELETED").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
        }

        loadAllCustomers();
    }

    public void searchSupOnAction(ActionEvent actionEvent) {
        search();
    }

    private void search () {
        try {
            ResultSet result = CrudUtil.execute("SELECT * FROM Supplier WHERE supplierId=?", txtSupId2.getText());
            if (result.next()) {
                txtSupName2.setText(result.getString(2));
                txtSupAddress2.setText(result.getString(3));
                txtSupContact2.setText(result.getString(4));


            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Result").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

        public void initialize(){
            colSupId.setCellValueFactory(new PropertyValueFactory("id"));
            colSupName.setCellValueFactory(new PropertyValueFactory("name"));
            colSupAddress.setCellValueFactory(new PropertyValueFactory("address"));
            colSupContact.setCellValueFactory(new PropertyValueFactory("contact"));
            try {
                loadAllCustomers();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }

        private void loadAllCustomers() throws ClassNotFoundException, SQLException {



            ResultSet result = CrudUtil.execute("SELECT * FROM Supplier");

            ObservableList<Supplier> obList = FXCollections.observableArrayList();
            while (result.next()){
                obList.add(
                        new Supplier(
                                result.getString("supplierId"),
                                result.getString("name"),
                                result.getString("address"),
                                result.getString("contact")

                        )
                );
            }
            tblManageSup.setItems(obList);


        }

    }



