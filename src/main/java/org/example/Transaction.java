package org.example;

import lombok.Data;
import org.example.controller.Context;

import java.sql.*;
import java.util.Date;

@Data
public class Transaction {
    private int id;
    private int buyerID;
    private int supplierID;
    private Date date;
    private int amount;
    private String type;
    private String state;
    private int itemID;

    public Transaction() {}

    public boolean buyerExists() {
        String query = "SELECT 1 FROM buyer WHERE id = ?";
        boolean exists = false;
        Context context = new Context();
        try (Connection connection = DriverManager.getConnection(context.getDatabaseInfo()[0],context.getDatabaseInfo()[1],context.getDatabaseInfo()[2]);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, buyerID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                exists = resultSet.next();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return exists;
    }

    public boolean supplierExists() {
        String query = "SELECT 1 FROM supplier WHERE id = ?";
        boolean exists = false;
        Context context = new Context();
        try (Connection connection = DriverManager.getConnection(context.getDatabaseInfo()[0],context.getDatabaseInfo()[1],context.getDatabaseInfo()[2]);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, supplierID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                exists = resultSet.next();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return exists;
    }

    public boolean itemExists() {
        String query = "SELECT 1 FROM items WHERE item_id = ?";
        boolean exists = false;
        Context context = new Context();
        try (Connection connection = DriverManager.getConnection(context.getDatabaseInfo()[0],context.getDatabaseInfo()[1],context.getDatabaseInfo()[2]);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, itemID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                exists = resultSet.next();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return exists;
    }

}
