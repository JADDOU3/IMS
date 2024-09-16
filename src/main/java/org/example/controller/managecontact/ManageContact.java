package org.example.controller.managecontact;

import org.example.controller.Context;
import org.example.controller.MenueState;
import org.example.controller.State;
import org.example.optionsmanager.Menue;
import org.example.optionsmanager.Option;
import org.example.optionsmanager.contactoption.AddContactOption;
import org.example.optionsmanager.contactoption.DeleteContactOpt;
import org.example.optionsmanager.contactoption.UpdateContactOpt;
import org.example.optionsmanager.contactoption.ViewContactOpt;

import java.awt.*;
import java.sql.*;

public class ManageContact implements State {
    private Context context;

    public ManageContact(Context context){
        this.context = context;
    }

    @Override
    public void handleInput() {
      Option[] options = {
      new AddContactOption(context),
      new DeleteContactOpt(context),
      new UpdateContactOpt(context),
      new ViewContactOpt(context)
      };

      Menue menue = new Menue(options);
      menue.showMenu();
    }

    public void viewContact(){
        String querySQL = "SELECT * FROM contact";
        try(Connection connection = DriverManager.getConnection(context.getDatabaseInfo()[0],context.getDatabaseInfo()[1],context.getDatabaseInfo()[2]);
            PreparedStatement preparedStatement = connection.prepareStatement(querySQL);
            ResultSet resultSet = preparedStatement.executeQuery()){

            System.out.println("\nContacts Data:");
            System.out.println("{ [ ID ]\t[ Name ]\t\t[ Phone ]\t\t\t[ Email ]\t\t\t\t\t[ Address ]\t\t [ type ] }");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                String type = resultSet.getString("type");

                System.out.println("{ [ "+ id + " ]\t\t[ " + name + " ]\t\t[ " + phone + " ]\t\t[ " + email + " ]\t\t[ " + address+ " ]\t\t [ " + type + " ] }");
            }
            System.out.println();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Enter \"1\" if you want to view detailed data");
        if(context.getScanner().nextLine().equals("1")){
            System.out.println("Detailed Data :");
            showSuppliers();
            showBuyers();
        }

    }

    private void showSuppliers(){
        String querySQL = "SELECT * FROM supplier";
        try(Connection connection = DriverManager.getConnection(context.getDatabaseInfo()[0],context.getDatabaseInfo()[1],context.getDatabaseInfo()[2]);
            PreparedStatement preparedStatement = connection.prepareStatement(querySQL);
            ResultSet resultSet = preparedStatement.executeQuery()){

            System.out.println("\nSuppliers Data:");
            System.out.println("{ [ ID ]\t[ Contact ID ]\t[ Name ]\t\t[ Phone ]\t\t\t[ Email ]\t\t\t\t\t[ Address ]\t\t[ Rating ]\t\t [ prefabrication ]  }");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int conId = resultSet.getInt("contact_id");
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                int rating = resultSet.getInt("supplier_rating");
                boolean prefabrication = resultSet.getBoolean("preferred_supplier");

                System.out.println("{ [ "+ id + " ]\t\t\t[ " + conId + " ]\t\t[ " + name + " ]\t\t[ " + phone + " ]\t\t[ " + email + " ]\t\t[ " + address+ " ]\t\t [ " + rating + " ]\t\t [ " + prefabrication + " ] }");
            }
            System.out.println();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void showBuyers() {
        String querySQL = "SELECT * FROM buyer";
        try (Connection connection = DriverManager.getConnection(context.getDatabaseInfo()[0], context.getDatabaseInfo()[1], context.getDatabaseInfo()[2]);
             PreparedStatement preparedStatement = connection.prepareStatement(querySQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            System.out.println("\nBuyers Data:");
            System.out.println("{ [ ID ]\t[ Contact ID ]\t[ Name ]\t\t[ Phone ]\t\t[ Email ]\t\t\t\t[ Address ]\t\t[ Loyalty Points ] }");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int conId = resultSet.getInt("contact_id");
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                int loyaltyPoints = resultSet.getInt("loyalty_points");

                System.out.println("{ [ "+ id + " ]\t\t\t[ " + conId + " ]\t\t[ " + name + " ]\t\t[ " + phone + " ]\t\t[ " + email + " ]\t\t[ " + address+ " ]\t\t [ " + loyaltyPoints + " ] }");

            }
            System.out.println();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
