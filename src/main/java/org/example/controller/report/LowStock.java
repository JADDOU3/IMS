package org.example.controller.report;

import org.example.controller.Context;
import org.example.controller.State;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LowStock implements State {
    private Context context;

    public LowStock(Context context) {
        this.context = context;
    }

    @Override
    public void handleInput() {
        System.out.println("Print using : \n1.Console   2.CSV file");
        int i = context.getScanner().nextInt();
        if(i != 1 && i != 2) {
            System.out.println("Invalid input");
            handleInput();
        }
        showLowStockItems(i);

        context.setCurrentState(new GenerateReportState(context));
        context.handleInput();
    }

    public void showLowStockItems(int i){
        List<String[]> list = new ArrayList<>();
        String querySQL = "SELECT * FROM items WHERE quantity < 10";
        try(Connection connection = DriverManager.getConnection(context.getDatabaseInfo()[0],context.getDatabaseInfo()[1],context.getDatabaseInfo()[2]);
            PreparedStatement preparedStatement = connection.prepareStatement(querySQL);
            ResultSet resultSet = preparedStatement.executeQuery()){

            while (resultSet.next()) {
                String[] line = new String[4];
                line[0] = String.valueOf(resultSet.getInt("item_id"));
                line[1] = resultSet.getString("item_name");
                line[2] = String.valueOf(resultSet.getInt("quantity"));
                line[3] = String.valueOf(resultSet.getInt("price"));
                list.add(line);
                }

            if(i == 1)
                consolePrint(list);
            else
                csvPrint(list);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void consolePrint(List<String[]> list){
        System.out.println("\n{ [ ID ]\t[ Name ]\t\t[ Quantity ]\t\t[ Price ] }");
        for(String[] line : list) {
            System.out.println("{ [ " + line[0] + " ]\t\t[ " + line[1] + " ]\t\t[ " + line[2] + " ]\t\t\t\t[ " + line[3] + " ] }");
        }
        System.out.println();
    }

    public void csvPrint(List<String[]> list){
        System.out.println("Enter file path :");
        String filePath = context.getScanner().nextLine();
        try (PrintWriter writer = new PrintWriter(new File(filePath))) {
            writer.println("{ [ ID ]\t[ Name ]\t\t[ Quantity ]\t\t[ Price ] }");
            for(String[] line : list) {
                writer.println("{ [ " + line[0] + " ]\t\t[ " + line[1] + " ]\t\t[ " + line[2] + " ]\t\t\t\t[ " + line[3] + " ] }");
            }

            System.out.println("Report exported successfully to " + filePath);
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
