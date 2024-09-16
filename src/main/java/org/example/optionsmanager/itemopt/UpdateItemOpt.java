package org.example.optionsmanager.itemopt;

import org.example.controller.Context;
import org.example.controller.manageitem.AddItem;
import org.example.controller.manageitem.UpdateItem;
import org.example.optionsmanager.Option;

public class UpdateItemOpt extends Option {
    private Context context;

    public UpdateItemOpt(Context context) {
        super("Update Item");
        this.context = context;
    }

    @Override
    public void run(){
        UpdateItem updateItem = new UpdateItem(context);
        updateItem.handleInput();
    }

}
