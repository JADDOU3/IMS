package org.example.controller.manageitem;

import org.example.controller.Context;
import org.example.controller.State;
import org.example.item.Item;

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
    }

}
