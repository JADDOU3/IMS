package org.example.controller.manageitem;

import org.example.controller.Context;
import org.example.controller.State;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteItem implements State {
    private Context context;
    public DeleteItem(Context context) {
        this.context = context;
    }

    @Override
    public void handleInput() {
        System.out.println("Enter the item ID:");
        int id = context.getScanner().nextInt();

        deleteItem(id);
        System.out.println("Item deleted successfully.");

        context.setCurrentState(new ManageItem(context));
        context.handleInput();

    }

    private void deleteItem(int id){
        String querySQL = "DELETE FROM items WHERE item_id = ?";
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
