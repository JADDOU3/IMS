package org.example.controller.managecontact;

import org.example.contact.Buyer;
import org.example.contact.Contact;
import org.example.contact.Supplier;
import org.example.controller.Context;
import org.example.controller.State;

import java.sql.*;

public class UpdateContact implements State {
    private Context context;
    public UpdateContact(Context context) {
        this.context = context;
    }
    @Override
    public void handleInput() {
        System.out.println("Choose the Contact type :\n"+
                "1. Supplier     2. Buyer");
        switch (context.getScanner().nextLine()){
            case "1":
                suppHelper();
                break;
            case "2"  :
                buyHelper();
                break;
            default:
                System.out.println("Please enter a valid Contact Type");
                handleInput();
        }
        context.setCurrentState(new ManageContact(context));
        context.handleInput();
    }

    private Supplier getSupplier(int id){
        String querySQL = "SELECT * FROM supplier WHERE contact_id = ?";
        Supplier supplier = null;
        try (Connection connection = DriverManager.getConnection(context.getDatabaseInfo()[0], context.getDatabaseInfo()[1], context.getDatabaseInfo()[2]);
             PreparedStatement preparedStatement = connection.prepareStatement(querySQL)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                   supplier = new Supplier();
                   supplier.setId(resultSet.getInt("contact_id"));
                   supplier.setName(resultSet.getString("name"));
                   supplier.setAddress(resultSet.getString("address"));
                   supplier.setPhone(resultSet.getString("phone"));
                   supplier.setEmail(resultSet.getString("email"));
                   supplier.setRating(resultSet.getInt("supplier_rating"));
                   supplier.setPreferred(resultSet.getBoolean("preferred_supplier"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return supplier;
    }

    private Buyer getBuyer(int id){
        String querySQL = "SELECT * FROM buyer WHERE contact_id = ?";
        Buyer buyer = null;
        try (Connection connection = DriverManager.getConnection(context.getDatabaseInfo()[0], context.getDatabaseInfo()[1], context.getDatabaseInfo()[2]);
             PreparedStatement preparedStatement = connection.prepareStatement(querySQL)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    buyer = new Buyer();
                    buyer.setId(resultSet.getInt("contact_id"));
                    buyer.setName(resultSet.getString("name"));
                    buyer.setAddress(resultSet.getString("address"));
                    buyer.setPhone(resultSet.getString("phone"));
                    buyer.setEmail(resultSet.getString("email"));
                    buyer.setLoyaltyPoints(resultSet.getInt("loyalty_points"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return buyer;
    }

    private void suppHelper(){
        System.out.println("Enter the Contact ID");
        int id = context.getScanner().nextInt();
        Supplier supplier = getSupplier(id);
        supplier.setId(id);
        System.out.println("Choose what you want to update\n"+
                "1. Name        2. Phone\n"+
                "3. Email       4. Address\n"+
                "5. Rating      6. prefabrication\n" +
                "7. Back");
        switch (context.getScanner().nextInt()){
            case 1:
                System.out.println("Enter the new Name");
                supplier.setName(context.getScanner().nextLine());
                break;
            case 2:
                System.out.println("Enter the new Phone");
                supplier.setPhone(context.getScanner().nextLine());
                break;
            case 3:
                System.out.println("Enter the new Email");
                supplier.setEmail(context.getScanner().nextLine());
                break;
            case 4:
                System.out.println("Enter the new Address");
                supplier.setAddress(context.getScanner().nextLine());
                break;
            case 5:
                System.out.println("Enter the new Rating");
                int rating = context.getScanner().nextInt();
                if(rating <= 100 && rating >= 0)
                    supplier.setRating(rating);
                else
                    System.out.println("Invalid Rating ; Should be Between 0 and 100");
                break;
            case 6:
                boolean isPreferred;
                System.out.println("Is this supplier preferred? (yes/no)");
                isPreferred = context.getScanner().next().toLowerCase().trim().equals("yes");
                supplier.setPreferred(isPreferred);
                break;
            case 7:
                handleInput();
                break;
            default:
                System.out.println("Invalid Input");
                suppHelper();
                break;
        }
        updateContact(supplier);
        updateSupplier(supplier);
    }

    private void buyHelper(){
        System.out.println("Enter the Contact ID");
        int id = context.getScanner().nextInt();
        Buyer buyer = getBuyer(id);
        buyer.setId(id);
        System.out.println("Choose what you want to update\n"+
                "1. Name                 2. Phone\n"+
                "3. Email                4. Address\n"+
                "5. Loyalty Points       6. Back");
        switch (context.getScanner().nextInt()){
            case 1:
                System.out.println("Enter the new Name");
                buyer.setName(context.getScanner().nextLine());
                break;
            case 2:
                System.out.println("Enter the new Phone");
                buyer.setPhone(context.getScanner().nextLine());
                break;
            case 3:
                System.out.println("Enter the new Email");
                buyer.setEmail(context.getScanner().nextLine());
                break;
            case 4:
                System.out.println("Enter the new Address");
                buyer.setAddress(context.getScanner().nextLine());
                break;
            case 5:
                System.out.println("Enter the new Loyalty points");
                buyer.setLoyaltyPoints(context.getScanner().nextInt());
                break;
            case 6:
                handleInput();
                break;
            default:
                System.out.println("Invalid Input");
                buyHelper();
                break;
        }
        updateContact(buyer);
        updateBuyer(buyer);
    }

    private <T extends Contact> void updateContact(T contact) {
        String querySQL =" UPDATE contact SET name = ?, phone = ?, email = ?,  address = ? WHERE id = ?";
        try(Connection connection = DriverManager.getConnection(context.getDatabaseInfo()[0],context.getDatabaseInfo()[1],context.getDatabaseInfo()[2]);
            PreparedStatement preparedStatement = connection.prepareStatement(querySQL)){
            preparedStatement.setString(1, contact.getName());
            preparedStatement.setString(2, contact.getPhone());
            preparedStatement.setString(3, contact.getEmail());
            preparedStatement.setString(4, contact.getAddress());
            preparedStatement.setInt(5, contact.getId());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void updateSupplier(Supplier contact) {
        String querySQL =" UPDATE supplier SET name = ?, phone = ?, email = ?,  address = ? , supplier_rating = ? , preferred_supplier = ? WHERE contact_id = ?";
        try(Connection connection = DriverManager.getConnection(context.getDatabaseInfo()[0],context.getDatabaseInfo()[1],context.getDatabaseInfo()[2]);
            PreparedStatement preparedStatement = connection.prepareStatement(querySQL)){
            preparedStatement.setString(1, contact.getName());
            preparedStatement.setString(2, contact.getPhone());
            preparedStatement.setString(3, contact.getEmail());
            preparedStatement.setString(4, contact.getAddress());
            preparedStatement.setInt(5, contact.getRating());
            preparedStatement.setBoolean(6, contact.getIsPreferred());
            preparedStatement.setInt(7, contact.getId());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateBuyer(Buyer contact) {
        String querySQL =" UPDATE buyer SET name = ?, phone = ?, email = ?,  address = ? , loyalty_points = ? WHERE contact_id = ?";
        try(Connection connection = DriverManager.getConnection(context.getDatabaseInfo()[0],context.getDatabaseInfo()[1],context.getDatabaseInfo()[2]);
            PreparedStatement preparedStatement = connection.prepareStatement(querySQL)){
            preparedStatement.setString(1, contact.getName());
            preparedStatement.setString(2, contact.getPhone());
            preparedStatement.setString(3, contact.getEmail());
            preparedStatement.setString(4, contact.getAddress());
            preparedStatement.setInt(5, contact.getLoyaltyPoints());
            preparedStatement.setInt(6, contact.getId());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
