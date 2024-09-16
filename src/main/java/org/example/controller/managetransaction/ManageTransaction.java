package org.example.controller.managetransaction;

import org.example.controller.Context;
import org.example.controller.State;
import org.example.optionsmanager.Menue;
import org.example.optionsmanager.Option;
import org.example.optionsmanager.transopt.AddTransOpt;
import org.example.optionsmanager.transopt.UpdateTransOpt;
import org.example.optionsmanager.transopt.ViewTransOpt;

import java.sql.*;

public class ManageTransaction implements State {
    private Context context;

    public ManageTransaction(Context context){
        this.context = context;
    }

    @Override
    public void handleInput() {
        Option[] options = {
                new AddTransOpt(context),
                new UpdateTransOpt(context),
                new ViewTransOpt(context)
        };
        Menue menue = new Menue(options);
        menue.showMenu();

    }

    public void viewTransaction() {

    }
}
