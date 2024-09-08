package org.example.controller.managecontact;

import org.example.contact.Contact;
import org.example.controller.Context;
import org.example.controller.State;

public class DeleteContact implements State {
    private Context context;
    public DeleteContact(Context context) {
        this.context = context;
    }
    @Override
    public void handleInput() {

    }
}
