package org.example.controller.search;

import org.example.controller.Context;
import org.example.controller.State;

import java.sql.*;

public class SearchItem implements State {
    private Context context;

    public SearchItem(Context context) {
        this.context = context;
    }

    @Override
    public void handleInput() {
        System.out.println("Enter the item's name :");
        String name = context.getScanner().nextLine();
        getItem(name);

    }
    public void getItem(String itemName){
        String querySQL = "SELECT * FROM items WHERE item_name = ?";
        try(Connection connection = DriverManager.getConnection(context.getDatabaseInfo()[0],context.getDatabaseInfo()[1],context.getDatabaseInfo()[2]);
            PreparedStatement preparedStatement = connection.prepareStatement(querySQL)){

            preparedStatement.setString(1, itemName);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                System.out.println("\n{ [ ID ]\t[ Name ]\t\t[ Quantity ]\t\t[ Price ] }");

                while (resultSet.next()) {
                    int id = resultSet.getInt("item_id");
                    String name = resultSet.getString("item_name");
                    int quantity = resultSet.getInt("quantity");
                    int price = resultSet.getInt("price");

                    System.out.println("{ [ " + id + " ]\t\t[ " + name + " ]\t\t[ " + quantity + " ]\t\t\t\t[ " + price + " ] }");
                }
            }
            System.out.println();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
