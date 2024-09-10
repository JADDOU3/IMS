package org.example.controller.managecontact;

import org.example.contact.Contact;
import org.example.controller.Context;
import org.example.controller.State;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddContact implements State {
    private Context context;
    public AddContact(Context context) {
        this.context = context;
    }

    @Override
    public void handleInput() {
        String name;
        String phone;
        String email;
        String address;
        String contactType;

        addContact(name,phone,email,address,contactType);

    }

    public void addContact(String name, String phone, String email, String address, String contactType){
        String insertContactSQL = "INSERT INTO contacts (contact_name, phone, email, address, contact_type) VALUES (?, ?, ?, ?, ?)";
        try(Connection connection = DriverManager.getConnection(context.getDatabaseInfo()[0],context.getDatabaseInfo()[1],context.getDatabaseInfo()[2]);
            PreparedStatement preparedStatement = connection.prepareStatement(insertContactSQL)){

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
