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
import model.Item;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ManageSupplimentFormController {
    public JFXTextField txtItemCode;
    public JFXTextField txtItemName;
    public JFXTextField txtItemDes;
    public JFXTextField txtItemExp;
    public JFXTextField txtItemCode2;
    public JFXTextField txtItemName2;
    public JFXTextField txtItemDes2;
    public JFXTextField txtItemExp2;
    public JFXTextField txtItemQOH;
    public JFXTextField txtItemUP;
    public JFXTextField txtItemQOH2;
    public JFXTextField txtItemUP2;
    public TableView<Item> tblManageSupplement;
    public TableColumn colItemCode;
    public TableColumn colItemName;
    public TableColumn colItemDes;
    public TableColumn colItemExp;
    public TableColumn colItemQOH;
    public TableColumn colItemUP;
    public TableColumn colItemOption;

    public void addItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
         Item item = new Item(
                txtItemCode.getText(),txtItemName.getText(),txtItemDes.getText(),txtItemExp.getText(),Integer.parseInt(txtItemQOH.getText()),Double.parseDouble(txtItemUP.getText())
        );

        try {

            if (CrudUtil.execute("INSERT INTO Supplement VALUES(?,?,?,?,?,?)",item.getItemCode(),item.getName(),item.getDescription(),item.getExpireDate(),item.getQtyOnHand(),item.getUnitPrice())){
                new Alert(Alert.AlertType.CONFIRMATION,"Saved...").show();
            }



        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Something went wrong!...").show();
        }
        loadAllCustomers();


    }

    public void modifyItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Item item = new Item(
                txtItemCode2.getText(),txtItemName2.getText(),txtItemDes2.getText(),txtItemExp2.getText(),Integer.parseInt(txtItemQOH2.getText()),Double.parseDouble(txtItemUP2.getText()));



        try {

            boolean isUpdated = CrudUtil.execute("UPDATE Supplement SET name=? , description=? , expireDate=? , qtyOnHand=? , unitPrice=? WHERE itemCode=?", item.getItemCode(), item.getName(), item.getDescription(), item.getExpireDate(),item.getQtyOnHand(),item.getUnitPrice());
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

    public void deleteItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        try {


            if (CrudUtil.execute("DELETE FROM Supplement WHERE itemCode=?",txtItemCode2.getText())) {
                new Alert(Alert.AlertType.CONFIRMATION, "DELETED").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
        }
        loadAllCustomers();
    }

    public void searchItemOnAction(ActionEvent actionEvent) {
        search();
    }

    private void search () {
        try {
            ResultSet result = CrudUtil.execute("SELECT * FROM Supplement WHERE itemCode=?",txtItemCode2.getText());
            if (result.next()) {
                txtItemName2.setText(result.getString(2));
                txtItemDes2.setText(result.getString(3));
                txtItemExp2.setText(result.getString(4));
                txtItemQOH2.setText(String.valueOf(result.getInt(5)));
                txtItemUP2.setText(String.valueOf(result.getDouble(6)));


            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Result").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void initialize(){
        colItemCode.setCellValueFactory(new PropertyValueFactory("itemCode"));
        colItemName.setCellValueFactory(new PropertyValueFactory("name"));
        colItemDes.setCellValueFactory(new PropertyValueFactory("description"));
        colItemExp.setCellValueFactory(new PropertyValueFactory("expireDate"));
        colItemQOH.setCellValueFactory(new PropertyValueFactory("qtyOnHand"));
        colItemUP.setCellValueFactory(new PropertyValueFactory("unitPrice"));
        try {
            loadAllCustomers();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadAllCustomers() throws ClassNotFoundException, SQLException {



        ResultSet result = CrudUtil.execute("SELECT * FROM Supplement");

        ObservableList<Item> obList = FXCollections.observableArrayList();
        while (result.next()){
            obList.add(
                    new Item(
                            result.getString("itemCode"),
                            result.getString("name"),
                            result.getString("description"),
                            result.getString("expireDate"),
                            result.getInt("qtyOnHand"),
                            result.getDouble("unitPrice")

                    )
            );
        }
        tblManageSupplement.setItems(obList);


    }

}
