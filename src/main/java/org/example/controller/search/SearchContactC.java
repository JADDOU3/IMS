package org.example.controller.search;

import org.example.controller.Context;
import org.example.controller.State;

import java.sql.*;

public class SearchContactC implements State {
    private Context context;

    public SearchContactC(Context context) {
        this.context = context;
    }

    @Override
    public void handleInput() {
        System.out.println("Choose Contact type :\n 1. supplier   2. buyer ");
        String command = context.getScanner().nextLine();
        switch (command) {
            case "1":
                getContact("supplier");
                break;
            case "2" :
                getContact("buyer");
                break;
            default:
                System.out.println("Invalid input");
                handleInput();
        }


    }

    public void getContact(String category){
        String querySQL = "SELECT * FROM contact WHERE type = ?";
        try(Connection connection = DriverManager.getConnection(context.getDatabaseInfo()[0],context.getDatabaseInfo()[1],context.getDatabaseInfo()[2]);
            PreparedStatement preparedStatement = connection.prepareStatement(querySQL)){

            preparedStatement.setString(1, category);

            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                System.out.println("{ [ ID ]\t[ Name ]\t\t[ Phone ]\t\t\t[ Email ]\t\t\t\t\t[ Address ]\t\t [ type ] }");

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String phone = resultSet.getString("phone");
                    String email = resultSet.getString("email");
                    String address = resultSet.getString("address");
                    String type = resultSet.getString("type");

                    System.out.println("{ [ " + id + " ]\t\t[ " + name + " ]\t\t[ " + phone + " ]\t\t[ " + email + " ]\t\t[ " + address + " ]\t\t [ " + type + " ] }");
                }
            }
            System.out.println();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
