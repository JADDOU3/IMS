package org.example.optionsmanager.contactoption;

import org.example.controller.Context;
import org.example.controller.managecontact.AddContact;
import org.example.optionsmanager.Option;

public class AddContactOption extends Option {
    private Context context;

    public AddContactOption(Context context) {
        super("Add Contact");
        this.context = context;
    }

    @Override
    public void run() {
        AddContact addContact = new AddContact(context);
        addContact.handleInput();
    }
}
