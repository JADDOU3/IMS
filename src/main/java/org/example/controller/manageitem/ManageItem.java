package org.example.controller.manageitem;

import org.example.controller.Context;
import org.example.controller.State;

import java.sql.*;

public class ManageItem implements State {
    private Context context;
    public ManageItem(Context context) {
        this.context = context;
    }

    @Override
    public void handleInput() {
        System.out.println("1. Add Item      2. Update Item\n"+
                           "3. Delete Item   4. View Items");
        String in = context.getScanner().nextLine();
        switch (in){
            case "1":
                context.setCurrentState(new AddItem(context));
                break;
            case "2":
                context.setCurrentState(new UpdateItem(context));
                break;
            case "3":
                context.setCurrentState(new DeleteItem(context));
                break;
            case "4":
                viewItem();
                break;
            default:
                System.out.println("Invalid input");
        }
        context.handleInput();
    }

    private void viewItem() {
        String querySQL = "SELECT * FROM items";
        try(Connection connection = DriverManager.getConnection(context.getDatabaseInfo()[0],context.getDatabaseInfo()[1],context.getDatabaseInfo()[2]);
            PreparedStatement preparedStatement = connection.prepareStatement(querySQL);
            ResultSet resultSet = preparedStatement.executeQuery()){

            System.out.println("\n{ [ ID ]\t[ Name ]\t\t[ Quantity ]\t\t[ Price ] }");

            while (resultSet.next()) {
                int id = resultSet.getInt("item_id");
                String name = resultSet.getString("item_name");
                int quantity = resultSet.getInt("quantity");
                int price = resultSet.getInt("price");

                System.out.println("{ [ "+ id + " ]\t\t[ " + name + " ]\t\t[ " + quantity + " ]\t\t\t\t[ " + price + " ] }");
            }
            System.out.println();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
