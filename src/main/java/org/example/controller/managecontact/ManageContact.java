package org.example.controller.managecontact;

import org.example.controller.Context;
import org.example.controller.MenueState;
import org.example.controller.State;

import java.awt.*;

public class ManageContact implements State {
    private Context context;

    public ManageContact(Context context){
        this.context = context;
    }

    @Override
    public void handleInput() {
        System.out.println("1. Add Contact      2. Update Contact\n"+
                           "3. Delete Contact   4.View Contacts\n"+
                           "5. Back");
        String in = context.getScanner().nextLine();
        switch (in){
            case "1":
                context.setCurrentState(new AddContact(context));
                break;
            case "2":
                context.setCurrentState(new UpdateContact(context));
                break;
            case "3":
                context.setCurrentState(new DeleteContact(context));
                break;
            case "4":
                viewContact();
                break;
            case "5":
                context.setCurrentState(new MenueState(context));
                break;
            default:
                System.out.println("Invalid input");
        }
        context.handleInput();

    }

    private void viewContact(){

    }
}
