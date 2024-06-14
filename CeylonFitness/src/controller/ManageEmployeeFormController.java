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
import model.Employee;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ManageEmployeeFormController {
    public JFXTextField txtEmpId;
    public JFXTextField txtEmpName;
    public JFXTextField txtEmpAddress;
    public JFXTextField txtEmpContact;
    public JFXTextField txtEmpId2;
    public JFXTextField txtEmpName2;
    public JFXTextField txtEmpAddress2;
    public JFXTextField txtEmpContact2;
    public TableView<Employee> tblManageEmp;
    public TableColumn colEmpId;
    public TableColumn colEmpName;
    public TableColumn colEmpAddress;
    public TableColumn colEmpContact;
    public TableColumn colEmpSalary;
    public JFXTextField txtEmpSalary;
    public JFXTextField txtEmpSalary2;

    public void addEmpOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Employee employee = new Employee(
                txtEmpId.getText(),txtEmpName.getText(),txtEmpAddress.getText(),txtEmpContact.getText(),Double.parseDouble(txtEmpSalary.getText())
        );

        try {

            if (CrudUtil.execute("INSERT INTO Employee VALUES(?,?,?,?,?)",employee.getId(),employee.getName(),employee.getAddress(),employee.getContact(),employee.getSalary())){
                new Alert(Alert.AlertType.CONFIRMATION,"Saved...").show();
            }



        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Something went wrong!...").show();
        }

        loadAllCustomers();

    }

    public void modifyEmpOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Employee employee = new Employee(
                txtEmpId2.getText(),txtEmpName2.getText(),txtEmpAddress2.getText(),txtEmpContact2.getText(),Double.parseDouble(txtEmpSalary2.getText()));



        try {

            boolean isUpdated = CrudUtil.execute("UPDATE Employee SET name=? , address=? , contact=? , salary=? WHERE employeeId=?", employee.getName(), employee.getAddress(), employee.getContact(), employee.getSalary(), employee.getId());
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

    public void deleteEmpOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {


            if (CrudUtil.execute("DELETE FROM Employee WHERE employeeId=?",txtEmpId2.getText())) {
                new Alert(Alert.AlertType.CONFIRMATION, "DELETED").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
        }

        loadAllCustomers();
    }

    public void searchEmpOnAction(ActionEvent actionEvent) {
        search();

    }

    private void search () {
        try {
            ResultSet result = CrudUtil.execute("SELECT * FROM Employee WHERE employeeId=?",txtEmpId2.getText());
            if (result.next()) {
                txtEmpName2.setText(result.getString(2));
                txtEmpAddress2.setText(result.getString(3));
                txtEmpContact2.setText(result.getString(4));
                txtEmpSalary2.setText(String.valueOf(result.getDouble(5)));


            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Result").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void initialize(){
        colEmpId.setCellValueFactory(new PropertyValueFactory("id"));
        colEmpName.setCellValueFactory(new PropertyValueFactory("name"));
        colEmpAddress.setCellValueFactory(new PropertyValueFactory("address"));
        colEmpContact.setCellValueFactory(new PropertyValueFactory("contact"));
        colEmpSalary.setCellValueFactory(new PropertyValueFactory("salary"));
        try {
            loadAllCustomers();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadAllCustomers() throws ClassNotFoundException, SQLException {



        ResultSet result = CrudUtil.execute("SELECT * FROM Employee");

        ObservableList<Employee> obList = FXCollections.observableArrayList();
        while (result.next()){
            obList.add(
                    new Employee(
                            result.getString("employeeId"),
                            result.getString("name"),
                            result.getString("address"),
                            result.getString("contact"),
                            result.getDouble("salary")

                    )
            );
        }
        tblManageEmp.setItems(obList);


    }
}
