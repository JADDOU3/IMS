package org.example.controller.managecontact;

import org.example.contact.Buyer;
import org.example.contact.Contact;
import org.example.contact.Supplier;
import org.example.controller.Context;
import org.example.controller.State;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteContact implements State {
    private Context context;
    public DeleteContact(Context context) {
        this.context = context;
    }
    @Override
    public void handleInput() {
        System.out.println("Enter contact type\n"+
                        "1. Supplier    2. Buyer");
        String in = context.getScanner().nextLine();
        switch (in){
            case "1":
                in = "supplier";
                break;
            case "2"  :
                in = "buyer";
                break;
            default:
                System.out.println("Please enter a valid Contact Type");
                handleInput();
        }
        System.out.println("Enter Contact id:");
        int id = context.getScanner().nextInt();

        String querySQL = "DELETE FROM " +in+ " WHERE contact_id = ?";
        deleteContact(querySQL , id);
        querySQL = "DELETE FROM contact WHERE id = ?";
        deleteContact(querySQL , id);

        System.out.println("Contact deleted successfully");
        context.setCurrentState(new ManageContact(context));
        context.handleInput();

    }

    private void deleteContact(String querySQL , int id) {
        try(Connection connection = DriverManager.getConnection(context.getDatabaseInfo()[0],context.getDatabaseInfo()[1],context.getDatabaseInfo()[2]);
            PreparedStatement preparedStatement = connection.prepareStatement(querySQL)){
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
