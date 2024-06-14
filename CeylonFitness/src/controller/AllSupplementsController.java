package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Item;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AllSupplementsController {
    public TableView<Item> tblAllSupplements;
    public TableColumn colAllItemCode;
    public TableColumn colAllItemName;
    public TableColumn colAllItemDes;
    public TableColumn colAllItemExp;
    public TableColumn colAllItemQOH;
    public TableColumn colAllItemUP;

    public void initialize(){
        colAllItemCode.setCellValueFactory(new PropertyValueFactory("itemCode"));
        colAllItemName.setCellValueFactory(new PropertyValueFactory("name"));
        colAllItemDes.setCellValueFactory(new PropertyValueFactory("description"));
        colAllItemExp.setCellValueFactory(new PropertyValueFactory("expireDate"));
        colAllItemQOH.setCellValueFactory(new PropertyValueFactory("qtyOnHand"));
        colAllItemUP.setCellValueFactory(new PropertyValueFactory("unitPrice"));
        try {
            loadAllCustomers();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadAllCustomers() throws ClassNotFoundException, SQLException {


        ResultSet result = CrudUtil.execute("SELECT * FROM Supplement");

        ObservableList<Item> obList = FXCollections.observableArrayList();
        while (result.next()) {
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
        tblAllSupplements.setItems(obList);

    }

    }

