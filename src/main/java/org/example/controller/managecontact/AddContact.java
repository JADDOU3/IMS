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

public class AddContact implements State {
    private Context context;
    public AddContact(Context context) {
        this.context = context;
    }

    @Override
    public void handleInput() {
        Contact contact = null;
        String in;
        System.out.println("Choose the Contact type :\n"+
                           "1. Supplier     2. Buyer");
        in = context.getScanner().nextLine();
        switch (in){
            case "1":
                contact = new Supplier();
                in = "supplier";
                break;
            case "2"  :
                contact = new Buyer();
                in = "buyers";
                break;
            default:
                System.out.println("Please enter a valid Contact Type");
                handleInput();
        }
        System.out.println("Enter Contact Name :");
        contact.setName(context.getScanner().nextLine());
        System.out.println("Enter Contact Address :");
        contact.setAddress(context.getScanner().nextLine());
        System.out.println("Enter Contact Phone Number :");
        contact.setPhone(context.getScanner().nextLine());
        System.out.println("Enter Contact Email Address :");
        contact.setEmail(context.getScanner().nextLine());

        addContact(contact );

        System.out.println("Contact Added Successfully");
        context.setCurrentState(new ManageContact(context));
        context.handleInput();

    }

    public void addContact(Contact contact){
        String insertContactSQL = "INSERT INTO " +contact.getType()+ " (contact_name, address, email, phone) VALUES (?, ?, ?, ?)";
        try(Connection connection = DriverManager.getConnection(context.getDatabaseInfo()[0],context.getDatabaseInfo()[1],context.getDatabaseInfo()[2]);
            PreparedStatement preparedStatement = connection.prepareStatement(insertContactSQL)){
            preparedStatement.setString(1, contact.getName());
            preparedStatement.setString(2, contact.getAddress());
            preparedStatement.setString(3, contact.getEmail());
            preparedStatement.setString(4, contact.getPhone());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
