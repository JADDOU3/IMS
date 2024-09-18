package org.example.controller.managetransaction;

import lombok.Data;
import org.example.Transaction;
import org.example.controller.Context;
import org.example.controller.State;

import java.sql.*;

public class RecordTransaction implements State {
    private Context context;

    public RecordTransaction(Context context){
        this.context = context;
    }

    @Override
    public void handleInput() {
        Transaction transaction = new Transaction();

        System.out.println("Enter The Buyer ID");
        transaction.setBuyerID(context.getScanner().nextInt());
        if(!transaction.buyerExists()){
            System.out.println("The Buyer ID does not exist");
            handleInput();
        }

        System.out.println("Enter the Supplier ID");
        transaction.setSupplierID(context.getScanner().nextInt());
        if(!transaction.supplierExists()){
            System.out.println("The Supplier ID does not exist");
            handleInput();
        }

        System.out.println("Enter the Item ID");
        transaction.setItemID(context.getScanner().nextInt());
        if(!transaction.itemExists()){
            System.out.println("The Item ID does not exist");
            handleInput();
        }

        System.out.println("Enter the Amount");
        transaction.setAmount(context.getScanner().nextInt());

        transaction.setDate(new java.util.Date());

        System.out.println("is it\n 1.Purchase    2.Sale");
        String x = context.getScanner().nextLine();
        if(x.equals("1")){
            transaction.setType("purchase");
        }
        else if(x.equals("2")){
            transaction.setType("sale");
        }

        transaction.setState("pending");

        addTransaction(transaction);

        context.setCurrentState(new ManageTransaction(context));
        context.handleInput();
    }

    private void addTransaction(Transaction transaction){
        String querySQL = "INSERT INTO transaction (buyer_id, supplier_id,item_id, date, amount, type, state) VALUES (?, ?, ?, ?, ?, ? , ?)";
        try (Connection connection = DriverManager.getConnection(context.getDatabaseInfo()[0], context.getDatabaseInfo()[1], context.getDatabaseInfo()[2]);
             PreparedStatement preparedStatement = connection.prepareStatement(querySQL, Statement.RETURN_GENERATED_KEYS)) {
           preparedStatement.setInt(1,transaction.getBuyerID());
           preparedStatement.setInt(2,transaction.getSupplierID());
           preparedStatement.setInt(3,transaction.getItemID());
            preparedStatement.setDate(4, new java.sql.Date(transaction.getDate().getTime()));
            preparedStatement.setInt(5,transaction.getAmount());
           preparedStatement.setString(6,transaction.getType());
           preparedStatement.setString(7,transaction.getState());
           preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
