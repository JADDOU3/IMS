package org.example.controller.managecontact;

import org.example.contact.Buyer;
import org.example.contact.Contact;
import org.example.contact.Supplier;
import org.example.controller.Context;
import org.example.controller.State;

import java.sql.*;

public class AddContact implements State {
    private Context context;
    public AddContact(Context context) {
        this.context = context;
    }

    @Override
    public void handleInput() {
        Contact contact = null;
        System.out.println("Choose the Contact type :\n"+
                           "1. Supplier     2. Buyer");

        switch (context.getScanner().nextLine()){
            case "1":
                contact = new Supplier();
                break;
            case "2"  :
                contact = new Buyer();
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

        addToContactTable(contact);

        System.out.println("Contact Added Successfully");
        context.setCurrentState(new ManageContact(context));
        context.handleInput();

    }

    private void addContact(Contact contact){
        String querySQL = "INSERT INTO " +contact.getType()+ " (contact_id , name, phone, email, address) VALUES (? , ?, ?, ?, ?)";
        try(Connection connection = DriverManager.getConnection(context.getDatabaseInfo()[0],context.getDatabaseInfo()[1],context.getDatabaseInfo()[2]);
            PreparedStatement preparedStatement = connection.prepareStatement(querySQL)){
            preparedStatement.setInt(1, contact.getId());
            preparedStatement.setString(2, contact.getName());
            preparedStatement.setString(3, contact.getAddress());
            preparedStatement.setString(4, contact.getEmail());
            preparedStatement.setString(5, contact.getPhone());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void addToContactTable(Contact contact) {
        String querySQL = "INSERT INTO contact (name, phone, email, address, type) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(context.getDatabaseInfo()[0], context.getDatabaseInfo()[1], context.getDatabaseInfo()[2]);
             PreparedStatement preparedStatement = connection.prepareStatement(querySQL, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, contact.getName());
            preparedStatement.setString(2, contact.getPhone());
            preparedStatement.setString(3, contact.getEmail());
            preparedStatement.setString(4, contact.getAddress());
            preparedStatement.setString(5, contact.getType());
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    contact.setId(generatedKeys.getInt(1));
                    addContact(contact);
                } else {
                    throw new SQLException("Failed to obtain contact ID.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
