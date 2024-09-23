package org.example.controller.managetransaction;

import org.example.controller.Context;
import org.example.controller.State;
import org.example.optionsmanager.Menue;
import org.example.optionsmanager.Option;
import org.example.optionsmanager.transopt.AddTransOpt;
import org.example.optionsmanager.transopt.UpdateTransOpt;
import org.example.optionsmanager.transopt.ViewTransOpt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManageTransaction implements State {
    private Context context;

    public ManageTransaction(Context context){
        this.context = context;
    }

    @Override
    public void handleInput() {
        Option[] options = {
                new AddTransOpt(context),
                new UpdateTransOpt(context),
                new ViewTransOpt(context)
        };
        Menue menue = new Menue(options);
        menue.showMenu();

    }

    public void viewTransaction() {
        String querySQL = "SELECT * FROM transaction";
        try(Connection connection = DriverManager.getConnection(context.getDatabaseInfo()[0],context.getDatabaseInfo()[1],context.getDatabaseInfo()[2]);
            PreparedStatement preparedStatement = connection.prepareStatement(querySQL);
            ResultSet resultSet = preparedStatement.executeQuery()){

            System.out.println("\n{ [ ID ]\t[ Buyer ID ]\t[ Supplier ID ]\t\t[ Item ID ]\t\t\t[ Date ]\t\t [ Amount ]\t\t[ Type ]\t\t[ State ] }");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int buyerId = resultSet.getInt("buyer_id");
                int supplierId = resultSet.getInt("supplier_id");
                int itemId = resultSet.getInt("item_id");
                Date date = resultSet.getDate("date");
                int amount = resultSet.getInt("amount");
                String type = resultSet.getString("type");
                String state = resultSet.getString("state");

                System.out.println("{ [ "+ id + " ]\t\t\t[ " + buyerId + " ]\t\t\t[ " + supplierId + " ]\t\t\t\t[ "+itemId+" ]\t\t\t[ " + date + " ]\t\t[ "+amount+" ]\t\t[ " + type + " ]\t\t[ " + state + " ] }");
            }
            System.out.println();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
