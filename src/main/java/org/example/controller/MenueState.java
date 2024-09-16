package org.example.controller;


import org.example.controller.managecontact.ManageContact;
import org.example.controller.manageitem.ManageItem;
import org.example.optionsmanager.Menue;
import org.example.optionsmanager.Option;
import org.example.optionsmanager.contactoption.ConactOpt;
import org.example.optionsmanager.itemopt.ItemOpt;

public class MenueState implements State{
    private Context context;

    public MenueState(Context context) {
        this.context = context;
    }

    @Override
    public void handleInput() {
        Option[] options = { new ItemOpt(context) , new ConactOpt(context)};
        Menue menue = new Menue(options);
        menue.showMenu();
    }
}
