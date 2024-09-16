package org.example.optionsmanager.contactoption;

import org.example.controller.Context;
import org.example.controller.managecontact.ManageContact;
import org.example.optionsmanager.Option;

public class ConactOpt extends Option {
    private Context context;

    public ConactOpt(Context context) {
        super("Manage Contacts");
        this.context = context;
    }

    @Override
    public void run(){
        ManageContact manageContact = new ManageContact(context);
        manageContact.handleInput();
    }
}
