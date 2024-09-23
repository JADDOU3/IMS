package org.example.optionsmanager.itemopt;

import org.example.controller.Context;
import org.example.controller.manageitem.AddCSV;
import org.example.controller.manageitem.AddMenue;
import org.example.optionsmanager.Option;

public class AddCSVOpt extends Option {
    private Context context;

    public AddCSVOpt(Context context) {
        super("Add via csv file");
        this.context = context;
    }

    @Override
    public void run(){
        AddCSV addCSV = new AddCSV(context);
        addCSV.handleInput();
    }

}
