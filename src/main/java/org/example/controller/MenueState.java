package org.example.controller;


import org.example.controller.managecontact.ManageContact;
import org.example.controller.manageitem.ManageItem;

public class MenueState implements State{
    private Context context;

    public MenueState(Context context) {
        this.context = context;
    }

    @Override
    public void handleInput() {
        System.out.println("1. Manage Inventory     2. Manage Contacts");
        String in = context.getScanner().nextLine();
        switch (in) {
            case "1":
                context.setCurrentState(new ManageItem(context));
                break;
            case "2":
                context.setCurrentState(new ManageContact(context));
                break;
            default:
                System.out.println("Invalid input");
        }
        context.handleInput();
    }
}
