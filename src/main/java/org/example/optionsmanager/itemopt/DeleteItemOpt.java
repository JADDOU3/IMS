package org.example.optionsmanager.itemopt;

import org.example.controller.Context;
import org.example.controller.manageitem.DeleteItem;
import org.example.controller.manageitem.UpdateItem;
import org.example.optionsmanager.Option;

public class DeleteItemOpt extends Option {
    private Context context;

    public DeleteItemOpt(Context context) {
        super("Delete Item");
        this.context = context;
    }

    @Override
    public void run(){
        DeleteItem deleteItem = new DeleteItem(context);
        deleteItem.handleInput();
    }

}
