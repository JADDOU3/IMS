package org.example.controller.managecontact;

import org.example.contact.Contact;
import org.example.controller.Context;
import org.example.controller.State;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateContact implements State {
    private Context context;
    public UpdateContact(Context context) {
        this.context = context;
    }
    @Override
    public void handleInput() {
        System.out.println("");

    }

    private void updateContact(Contact contact) {
        String querySQL =" UPDATE " +contact.getType()+ " SET contact_name = ?, address = ?, email = ?,  phone = ? WHERE contact_id = ?";

        try(Connection connection = DriverManager.getConnection(context.getDatabaseInfo()[0],context.getDatabaseInfo()[1],context.getDatabaseInfo()[2]);
            PreparedStatement preparedStatement = connection.prepareStatement(querySQL)){
            preparedStatement.setString(1, contact.getName());
            preparedStatement.setString(2, contact.getAddress());
            preparedStatement.setString(3, contact.getEmail());
            preparedStatement.setString(4, contact.getPhone());
            preparedStatement.setInt(5, contact.getId());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
