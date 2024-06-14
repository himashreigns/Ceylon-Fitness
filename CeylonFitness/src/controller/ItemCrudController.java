package controller;

import util.CrudUtil;
import model.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemCrudController {
    public static ArrayList<String> getItemCodes() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT itemCode FROM Supplement");
        ArrayList<String> codeList = new ArrayList<>();
        while (result.next()){
            codeList.add(result.getString(1));
        }
        return codeList;
    }
    public static Item getItem(String code) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM Supplement WHERE itemCode=?",code);
        if (result.next()){
            return new Item(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getInt(5),
                    result.getDouble(6)
            );
        }
        return null;
    }
}
