package org.example.optionsmanager.itemopt;

import org.example.controller.Context;
import org.example.controller.manageitem.ManageItem;
import org.example.optionsmanager.Option;

public class ItemOpt extends Option {
    private Context context;

    public ItemOpt(Context context){
        super("Manage Items");
        this.context = context;
    }

    @Override
    public void run(){
        ManageItem mi = new ManageItem(context);
        mi.handleInput();
    }
}
