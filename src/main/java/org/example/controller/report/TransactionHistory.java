package org.example.controller.report;

import org.example.controller.Context;
import org.example.controller.State;
import org.example.optionsmanager.Menue;
import org.example.optionsmanager.Option;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionHistory implements State {
    private Context context;

    public TransactionHistory(Context context) {
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
        showTransactionHistory(i);

        context.setCurrentState(new GenerateReportState(context));
        context.handleInput();
    }

    public void showTransactionHistory(int i){
        List<String[]> list = new ArrayList<>();
        String querySQL = "SELECT * FROM transaction";
        try(Connection connection = DriverManager.getConnection(context.getDatabaseInfo()[0],context.getDatabaseInfo()[1],context.getDatabaseInfo()[2]);
            PreparedStatement preparedStatement = connection.prepareStatement(querySQL);
            ResultSet resultSet = preparedStatement.executeQuery()){


            while (resultSet.next()) {
                String[] line = new String[8];
                line[0] = String.valueOf(resultSet.getInt("id"));
                line[1] = String.valueOf(resultSet.getInt("buyer_id"));
                line[2] = String.valueOf(resultSet.getInt("supplier_id"));
                line[3] = String.valueOf(resultSet.getInt("item_id"));
                line[4] = String.valueOf(resultSet.getDate("date"));
                line[5] = String.valueOf(resultSet.getInt("amount"));
                line[6] = resultSet.getString("type");
                line[7] = resultSet.getString("state");
                list.add(line);
                }
            if(i == 1)
                consolePrint(list);
            else
                csvPrint(list);
            System.out.println();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void consolePrint(List<String[]> list){
        System.out.println("\n{ [ ID ]\t[ Buyer ID ]\t[ Supplier ID ]\t\t[ Item ID ]\t\t\t[ Date ]\t\t [ Amount ]\t\t[ Type ]\t\t[ State ] }");
        for(String[] line : list){
            System.out.println("{ [ "+ line[0] + " ]\t\t\t[ " + line[1] + " ]\t\t\t[ " + line[2] + " ]\t\t\t\t[ "+line[3]+" ]\t\t\t[ " + line[4] + " ]\t\t[ "+line[5]+" ]\t\t[ " + line[6] + " ]\t\t[ " + line[7] + " ] }");
        }
        System.out.println();
    }

    public void csvPrint(List<String[]> list){
        System.out.println("Enter file path :");
        String filePath = context.getScanner().nextLine();
        try (PrintWriter writer = new PrintWriter(new File(filePath))) {
            writer.println("{ [ ID ]\t[ Buyer ID ]\t[ Supplier ID ]\t\t[ Item ID ]\t\t\t[ Date ]\t\t [ Amount ]\t\t[ Type ]\t\t[ State ] }");

            for(String[] line : list) {
                writer.println("{ [ "+ line[0] + " ]\t\t\t[ " + line[1] + " ]\t\t\t[ " + line[2] + " ]\t\t\t\t[ "+line[3]+" ]\t\t\t[ " + line[4] + " ]\t\t[ "+line[5]+" ]\t\t[ " + line[6] + " ]\t\t[ " + line[7] + " ] }");
            }

            System.out.println("Report exported successfully to " + filePath);
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
