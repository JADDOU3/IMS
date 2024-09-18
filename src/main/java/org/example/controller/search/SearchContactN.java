package org.example.controller.search;

import org.example.controller.Context;
import org.example.controller.State;

import java.sql.*;

public class SearchContactN implements State {
    private Context context;

    public SearchContactN(Context context) {
        this.context = context;
    }

    @Override
    public void handleInput() {
        System.out.println("Enter the contact name :");
        String name = context.getScanner().nextLine();
        getContact(name);
    }

    public void getContact(String contactName){
        String querySQL = "SELECT * FROM contact WHERE name = ?";
        try(Connection connection = DriverManager.getConnection(context.getDatabaseInfo()[0],context.getDatabaseInfo()[1],context.getDatabaseInfo()[2]);
            PreparedStatement preparedStatement = connection.prepareStatement(querySQL)){

            preparedStatement.setString(1, contactName);

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

