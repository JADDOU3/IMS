package org.example.controller.manageitem;

import org.example.controller.Context;
import org.example.controller.State;

public class ManageItem implements State {
    private Context context;
    public ManageItem(Context context) {
        this.context = context;
    }

    @Override
    public void handleInput() {
        System.out.println("1. Add Item      2. Update Item\n"+
                           "3. Delete Item   4.View Items");
        String in = context.getScanner().nextLine();
        switch (in){
            case "1":
                context.setCurrentState(new AddItem(context));
                break;
            case "2":
                context.setCurrentState(new UpdateItem(context));
                break;
            case "3":
                context.setCurrentState(new DeleteItem(context));
                break;
            case "4":
                viewItem();
                break;
            default:
                System.out.println("Invalid input");
        }
        context.handleInput();
    }

    private void viewItem() {

    }
}
