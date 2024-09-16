package org.example.optionsmanager.contactoption;

import org.example.controller.Context;
import org.example.controller.managecontact.DeleteContact;
import org.example.optionsmanager.Option;

public class DeleteContactOpt extends Option {
    private Context context;

    public DeleteContactOpt(Context context) {
        super("Delete Contact");
        this.context = context;
    }

    @Override
    public void run(){
        DeleteContact deleteContact = new DeleteContact(context);
        deleteContact.handleInput();
    }
}
