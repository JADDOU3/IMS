package org.example.controller.manageitem;

import org.example.controller.Context;
import org.example.controller.State;
import org.example.optionsmanager.Menue;
import org.example.optionsmanager.Option;
import org.example.optionsmanager.itemopt.*;

import java.sql.*;

public class ManageItem implements State {
    private Context context;
    public ManageItem(Context context) {
        this.context = context;
    }

    @Override
    public void handleInput() {
        Option[] options = {
                new AddMenueOpt(context),
                new DeleteItemOpt(context),
                new UpdateItemOpt(context),
                new ViewItemOpt(context)
        };
        Menue menue = new Menue(options);
        menue.showMenu();
    }

    public void viewItem() {
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
