package org.example.controller.managetransaction;

import org.example.controller.Context;
import org.example.controller.State;
import org.example.item.Item;
import org.example.transaction.Transaction;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateTransaction implements State {
    private Context context;

    public UpdateTransaction(Context context){
        this.context = context;
    }

    @Override
    public void handleInput() {
        Transaction transaction ;
        System.out.println("Enter the transaction ID");
        int id = context.getScanner().nextInt();
        transaction = getTransaction(id);

        if(transaction.getState().equals("approved")){
            System.out.println("Transaction is approved cant be updated");
            context.setCurrentState(new ManageTransaction(context));
            context.handleInput();
        }

        System.out.println("1. Update Date      2. Update Amount    3. Update State");
        switch (context.getScanner().nextInt()) {
            case 1:
                transaction.setDate(new java.util.Date());
                break;
            case 2:
                System.out.println("Enter new amount:");
                transaction.setAmount(context.getScanner().nextInt());
                break;
            case 3:
                transaction.setState("approved");
                updateInventory(transaction);
                break;
            default:
                System.out.println("Invalid input");
                handleInput();
        }
        updateTransaction(transaction);

    }

    public void updateTransaction(Transaction transaction) {
        String querySQL = "UPDATE transaction SET buyer_id = ?, supplier_id = ? , item_id = ? , date = ? , amount = ? , type = ? , state = ? WHERE id = ?";
        try(Connection connection = DriverManager.getConnection(context.getDatabaseInfo()[0],context.getDatabaseInfo()[1],context.getDatabaseInfo()[2]);
            PreparedStatement preparedStatement = connection.prepareStatement(querySQL)){
            preparedStatement.setInt(1 , transaction.getBuyerID());
            preparedStatement.setInt(2 , transaction.getSupplierID());
            preparedStatement.setInt(3 , transaction.getItemID());
            preparedStatement.setDate(4 , new java.sql.Date(transaction.getDate().getTime()));
            preparedStatement.setDouble(5 , transaction.getAmount());
            preparedStatement.setString(6 , transaction.getType());
            preparedStatement.setString(7 , transaction.getState());
            preparedStatement.setInt(8 , transaction.getId());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Transaction getTransaction(int id) {
        String querySQL = "SELECT * FROM transaction WHERE id = ?";
        Transaction transaction = null;
        try (Connection connection = DriverManager.getConnection(context.getDatabaseInfo()[0], context.getDatabaseInfo()[1], context.getDatabaseInfo()[2]);
             PreparedStatement preparedStatement = connection.prepareStatement(querySQL)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    transaction = new Transaction();
                    transaction.setId(resultSet.getInt("id"));
                    transaction.setBuyerID(resultSet.getInt("buyer_id"));
                    transaction.setSupplierID(resultSet.getInt("supplier_id"));
                    transaction.setItemID(resultSet.getInt("item_id"));
                    transaction.setDate(resultSet.getDate("date"));
                    transaction.setAmount(resultSet.getInt("amount"));
                    transaction.setType(resultSet.getString("type"));
                    transaction.setState(resultSet.getString("state"));

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return transaction;
    }

    public void updateInventory(Transaction transaction){
        String querySQL = "SELECT * FROM items WHERE item_id = ?";
        Item item = null;
        try (Connection connection = DriverManager.getConnection(context.getDatabaseInfo()[0], context.getDatabaseInfo()[1], context.getDatabaseInfo()[2]);
             PreparedStatement preparedStatement = connection.prepareStatement(querySQL)) {

            preparedStatement.setInt(1, transaction.getItemID());

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
        if(transaction.getType().equals("purchase"))
            item.setQuantity(item.getQuantity() + transaction.getAmount());
        else
            item.setQuantity(item.getQuantity() - transaction.getAmount());
        updateItem(item);
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
}
