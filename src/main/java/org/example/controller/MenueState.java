package org.example.controller;


import org.example.controller.managecontact.ManageContact;

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
