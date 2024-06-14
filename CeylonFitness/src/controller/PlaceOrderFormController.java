package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import model.Customer;
import model.Item;
import view.tm.CartTM;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class PlaceOrderFormController {
    public Label lblDate;
    public Label lblTime;
    public ComboBox<String> cmbCustomerId;
    public ComboBox<String> cmbItemCode;
    public TableView tblCart;
    public TableColumn colCode;
    public TableColumn colDescription;
    public TableColumn colUnitPrice;
    public TableColumn colQty;
    public TableColumn colTotalCost;
    public TableColumn colbtn;
    public Label lblTotalCost;
    public JFXTextField txtPOCusName;
    public JFXTextField txtPOCusAddress;
    public JFXTextField txtPOCusContact;
    public JFXTextField txtPOItemDes;
    public JFXTextField txtPOItemQOH;
    public JFXTextField txtPOItemUP;
    public JFXTextField txtPOItemQty;

    public void initialize(){

        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotalCost.setCellValueFactory(new PropertyValueFactory<>("totalCost"));
        colbtn.setCellValueFactory(new PropertyValueFactory<>("btn"));

        loadDateAndTime();
        setCustomerIds();
        setItemCodes();

        cmbCustomerId.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) ->{
                    setCustomerDetails(newValue);
                } );
        cmbItemCode.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {

                    setItemDetails(newValue);

                });


    }
    private void setItemDetails(String selectedItemCode) {
        try {
            Item i = ItemCrudController.getItem(selectedItemCode);
            if (i!=null){
                txtPOItemDes.setText(i.getDescription());
                txtPOItemUP.setText(String.valueOf(i.getUnitPrice()));
                txtPOItemQOH.setText(String.valueOf(i.getQtyOnHand()));

            }else {
                new Alert(Alert.AlertType.WARNING,"Empty Result").show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setItemCodes() {
        try {
            cmbItemCode.setItems(FXCollections.observableArrayList(ItemCrudController.getItemCodes()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setCustomerDetails(String selectedCustomerId) {
        try {
            Customer c = CustomerCrudController.getCustomer(selectedCustomerId);
            if (c!=null){
                txtPOCusName.setText(c.getName());
                txtPOCusAddress.setText(c.getAddress());
                txtPOCusContact.setText(String.valueOf(c.getContact()));


            }else {
                new Alert(Alert.AlertType.WARNING,"Empty Result").show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadDateAndTime(){
        lblDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, event -> {
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(currentTime.getHour()+" : "+currentTime.getMinute()+" : "+currentTime.getSecond());

        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    private void setCustomerIds(){
        try {
            ObservableList<String> cIdObList = FXCollections.observableArrayList(CustomerCrudController.getCustomerIds());

            cmbCustomerId.setItems(cIdObList);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    ObservableList<CartTM> tmList = FXCollections.observableArrayList();

    public void addToCartOnAction(ActionEvent actionEvent) {
        double unitPrice = Double.parseDouble(txtPOItemUP.getText());
        int qty = Integer.parseInt(txtPOItemQty.getText());
        double totalCost = unitPrice*qty;

        CartTM isExists = isExists(cmbItemCode.getValue());
        if (isExists!=null){
            for (CartTM temp:tmList
            ) {
                if (temp.equals(isExists)){
                    temp.setQty(temp.getQty()+qty);
                    temp.setTotalCost(temp.getTotalCost()+totalCost);

                }

            }

        }else {
            Button btn = new Button("Delete");
            CartTM tm = new CartTM(
                    cmbItemCode.getValue(),
                    txtPOItemDes.getText(),
                    unitPrice,
                    qty,
                    totalCost,
                    btn
            );

            btn.setOnAction(event -> {
                for (CartTM TempTm:tmList
                ) {
                    if (TempTm.getCode().equals(tm.getCode())){
                        tmList.remove(tm);
                        calculateTotal();
                    }

                }
            });

            tmList.add(tm);
            tblCart.setItems(tmList);
        }
        tblCart.refresh();
        calculateTotal();


    }
    private CartTM isExists(String itemCode){
        for (CartTM tm:tmList
        ) {
            if (tm.getCode().equals(itemCode)){
                return tm;
            }

        }
        return null;
    }

    private void calculateTotal(){
        double total = 0;
        for (CartTM tm:tmList
        ) {
            total+= tm.getTotalCost();
        }
        lblTotalCost.setText(String.valueOf(total));
    }

    public void placeOrderOnAction(ActionEvent actionEvent) {
    }

    public void addNewCustomerOnAction(ActionEvent actionEvent) {
    }

    public void removeOnAction(ActionEvent actionEvent) {
    }
}
