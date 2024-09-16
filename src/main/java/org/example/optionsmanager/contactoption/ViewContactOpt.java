package org.example.optionsmanager.contactoption;

import org.example.controller.Context;
import org.example.controller.managecontact.ManageContact;
import org.example.optionsmanager.Option;

public class ViewContactOpt extends Option {
    private Context context;

    public ViewContactOpt(Context context) {
        super("View Contacts");
        this.context = context;
    }

    @Override
    public void run() {
        ManageContact manageContact = new ManageContact(context);
        manageContact.viewContact();
    }
}
