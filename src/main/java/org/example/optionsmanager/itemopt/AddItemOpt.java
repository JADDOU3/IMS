package org.example.optionsmanager.itemopt;

import org.example.controller.Context;
import org.example.controller.manageitem.add.AddItem;
import org.example.optionsmanager.Option;

public class AddItemOpt extends Option {
    private Context context;

    public AddItemOpt(Context context) {
        super("Add via console");
        this.context = context;
    }

    @Override
    public void run(){
        AddItem addItem = new AddItem(context);
        addItem.handleInput();
    }

}
