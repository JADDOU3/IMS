package org.example.controller.manageitem;

import org.example.contact.Contact;
import org.example.controller.Context;
import org.example.controller.State;
import org.example.item.Item;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddItem implements State {
    private Context context;
    public AddItem(Context context) {
        this.context = context;
    }

    @Override
    public void handleInput() {
        System.out.println("Enter the item's name:");
        String in = context.getScanner().nextLine();
        Item item = new Item();
        item.setName(in);
        System.out.println("Enter the item's price:");
        in = context.getScanner().nextLine();
        item.setPrice(Integer.parseInt(in));
        System.out.println("Enter the item's quantity:");
        in = context.getScanner().nextLine();
        item.setQuantity(Integer.parseInt(in));

        addItem(item);
        System.out.println("Item added successfully!");

        context.setCurrentState(new ManageItem(context));
        context.handleInput();

    }

    private void addItem(Item item){
        String querySQL = "INSERT INTO items (item_name, quantity, price) VALUES (?, ?, ?)";
        try(Connection connection = DriverManager.getConnection(context.getDatabaseInfo()[0],context.getDatabaseInfo()[1],context.getDatabaseInfo()[2]);
            PreparedStatement preparedStatement = connection.prepareStatement(querySQL)){
            preparedStatement.setString(1, item.getName());
            preparedStatement.setInt(2, item.getQuantity());
            preparedStatement.setInt(3, item.getPrice());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
