package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ImportSupplimentFormController {
    public JFXTextField txtImpSupName;
    public JFXTextField txtImpSupAddress;
    public JFXTextField txtImpSupContact;
    public JFXTextField txtImpItemName;
    public JFXTextField txtImpItemExp;
    public JFXTextField txtImpItemUP;
    public TableView tblImportSup;
    public TableColumn colImpItemCode;
    public TableColumn colImpItemName;
    public TableColumn colImpItemExp;
    public TableColumn colImpItemUP;
    public TableColumn colImpItemQty;
    public TableColumn colImpItemTC;
    public TableColumn colImpOption;
    public JFXComboBox cmbSelectSupplier;
    public JFXComboBox cmbSelectSupplement;
    public JFXTextField txtImpItemQty;



    public void impPlaceOrderOnAction(ActionEvent actionEvent) {
    }

    public void clearOnAction(ActionEvent actionEvent) {
    }

    public void addToCartOnAction(ActionEvent actionEvent) {
    }
}
