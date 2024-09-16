package org.example.optionsmanager.contactoption;

import org.example.controller.Context;
import org.example.controller.managecontact.UpdateContact;
import org.example.optionsmanager.Option;

public class UpdateContactOpt extends Option {
    private Context context;

    public UpdateContactOpt(Context context) {
        super("Update Contact");
        this.context = context;
    }

    @Override
    public void run(){
        UpdateContact updateContact = new UpdateContact(context);
        updateContact.handleInput();
    }
}
