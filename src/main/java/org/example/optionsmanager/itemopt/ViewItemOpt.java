package org.example.optionsmanager.itemopt;

import org.example.controller.Context;
import org.example.controller.manageitem.DeleteItem;
import org.example.controller.manageitem.ManageItem;
import org.example.optionsmanager.Option;

public class ViewItemOpt extends Option {
    private Context context;

    public ViewItemOpt(Context context) {
        super("View Items");
        this.context = context;
    }

    @Override
    public void run(){
        ManageItem manageItem = new ManageItem(context);
        manageItem.viewItem();
    }
}
