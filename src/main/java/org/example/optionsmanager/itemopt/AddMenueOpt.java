package org.example.optionsmanager.itemopt;

import org.example.controller.Context;
import org.example.controller.manageitem.add.AddMenue;
import org.example.optionsmanager.Option;

public class AddMenueOpt extends Option {
    private Context context;

    public AddMenueOpt(Context context) {
        super("Add Item");
        this.context = context;
    }

    @Override
    public void run(){
        AddMenue addMenue = new AddMenue(context);
        addMenue.handleInput();
    }

}