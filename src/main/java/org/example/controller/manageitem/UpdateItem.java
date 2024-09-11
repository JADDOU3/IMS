package org.example.controller.manageitem;

import org.example.controller.Context;
import org.example.controller.State;
import org.example.item.Item;

import java.sql.*;

public class UpdateItem implements State {
    private Context context;
    public UpdateItem(Context context) {
        this.context = context;
    }

    @Override
    public void handleInput() {
        System.out.println("Enter the item ID:");
        Item item = getItem(context.getScanner().nextInt());
        System.out.println("1. Update Name      2. Update Price     3. Update Quantity");
        switch (context.getScanner().nextInt()) {
            case 1:
                System.out.println("Enter new item name:");
                item.setName(context.getScanner().next());
                break;
            case 2:
                System.out.println("Enter new item price:");
                item.setPrice(context.getScanner().nextInt());
                break;
            case 3:
                System.out.println("Enter new item quantity:");
                item.setQuantity(context.getScanner().nextInt());
                break;
            default:
                System.out.println("Invalid input");
                handleInput();
        }

        updateItem(item);
        System.out.println("Item has been updated successfully.");

        context.setCurrentState(new ManageItem(context));
        context.handleInput();

    }

    private void updateItem(Item item) {
        String querySQL = "UPDATE items SET item_name = ?, quantity = ? , price = ? WHERE item_id = ?";
        try(Connection connection = DriverManager.getConnection(context.getDatabaseInfo()[0],context.getDatabaseInfo()[1],context.getDatabaseInfo()[2]);
            PreparedStatement preparedStatement = connection.prepareStatement(querySQL)){
            preparedStatement.setString(1, item.getName());
            preparedStatement.setInt(2, item.getQuantity());
            preparedStatement.setDouble(3, item.getPrice());
            preparedStatement.setInt(4, item.getId());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Item getItem(int id) {
        String querySQL = "SELECT * FROM items WHERE item_id = ?";
        Item item = null;
        try (Connection connection = DriverManager.getConnection(context.getDatabaseInfo()[0], context.getDatabaseInfo()[1], context.getDatabaseInfo()[2]);
             PreparedStatement preparedStatement = connection.prepareStatement(querySQL)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    item = new Item();
                    item.setId(resultSet.getInt("item_id"));
                    item.setName(resultSet.getString("item_name"));
                    item.setQuantity(resultSet.getInt("quantity"));
                    item.setPrice(resultSet.getInt("price"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return item;
    }

}
