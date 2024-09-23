package org.example.controller.manageitem;

import org.example.controller.Context;
import org.example.controller.State;
import org.example.item.Item;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddCSV implements State {
    private Context context;

    public AddCSV(Context context) {
        this.context = context;
    }

    @Override
    public void handleInput() {
        System.out.println("Enter the file path :");
        String in = context.getScanner().nextLine();
        addFromCSV(in);

        context.setCurrentState(new ManageItem(context));
        context.handleInput();
    }

    public void addFromCSV(String filePath){
        String querySQL = "INSERT INTO items (item_name, quantity, price) VALUES (?, ?, ?)";
        try(Connection connection = DriverManager.getConnection(context.getDatabaseInfo()[0],context.getDatabaseInfo()[1],context.getDatabaseInfo()[2]);
            PreparedStatement preparedStatement = connection.prepareStatement(querySQL);
            BufferedReader br = Files.newBufferedReader(Path.of(filePath))){

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                if (values.length == 3) {
                    String itemName = values[0];
                    int quantity = Integer.parseInt(values[1]);
                    int reorderLevel = Integer.parseInt(values[2]);

                    preparedStatement.setString(1, itemName);
                    preparedStatement.setInt(2, quantity);
                    preparedStatement.setInt(3, reorderLevel);
                    preparedStatement.addBatch();
                } else {
                    System.out.println("Invalid data format in CSV.");
                }
            }

            preparedStatement.executeBatch();
            System.out.println("items added successfully!");

        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
