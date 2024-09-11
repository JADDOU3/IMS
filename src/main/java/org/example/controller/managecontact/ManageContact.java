package org.example.controller.managecontact;

import org.example.controller.Context;
import org.example.controller.MenueState;
import org.example.controller.State;

import java.awt.*;
import java.sql.*;

public class ManageContact implements State {
    private Context context;

    public ManageContact(Context context){
        this.context = context;
    }

    @Override
    public void handleInput() {
        System.out.println("1. Add Contact      2. Update Contact\n"+
                           "3. Delete Contact   4. View Contacts\n"+
                           "5. Back");
        String in = context.getScanner().nextLine();
        switch (in){
            case "1":
                context.setCurrentState(new AddContact(context));
                break;
            case "2":
                context.setCurrentState(new UpdateContact(context));
                break;
            case "3":
                context.setCurrentState(new DeleteContact(context));
                break;
            case "4":
                viewContact();
                break;
            case "5":
                context.setCurrentState(new MenueState(context));
                break;
            default:
                System.out.println("Invalid input");
        }
        context.handleInput();

    }

    private void viewContact(){
        String querySQL = "SELECT * FROM buyers";
        try(Connection connection = DriverManager.getConnection(context.getDatabaseInfo()[0],context.getDatabaseInfo()[1],context.getDatabaseInfo()[2]);
            PreparedStatement preparedStatement = connection.prepareStatement(querySQL);
            ResultSet resultSet = preparedStatement.executeQuery()){

            System.out.println("Buyers Data:");
            System.out.println("{ [ ID ]\t[ Name ]\t\t[ Phone ]\t\t\t[ Email ]\t\t\t\t\t[ Address ] }");

            while (resultSet.next()) {
                int id = resultSet.getInt("contact_id");
                String name = resultSet.getString("contact_name");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");

                System.out.println("{ [ "+ id + " ]\t\t[ " + name + " ]\t\t[ " + phone + " ]\t\t[ " + email + " ]\t\t\t[ " + address+ " ] }");
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        querySQL = "SELECT * FROM supplier";
        try(Connection connection = DriverManager.getConnection(context.getDatabaseInfo()[0],context.getDatabaseInfo()[1],context.getDatabaseInfo()[2]);
            PreparedStatement preparedStatement = connection.prepareStatement(querySQL);
            ResultSet resultSet = preparedStatement.executeQuery()){

            System.out.println("Suppliers Data:");
            System.out.println("{ [ ID ]\t[ Name ]\t\t[ Phone ]\t\t\t[ Email ]\t\t\t\t\t[ Address ] }");

            while (resultSet.next()) {
                int id = resultSet.getInt("contact_id");
                String name = resultSet.getString("contact_name");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");

                System.out.println("{ [ "+ id + " ]\t\t[ " + name + " ]\t\t[ " + phone + " ]\t\t[ " + email + " ]\t\t\t[ " + address+ " ] }");
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
