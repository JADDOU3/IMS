package org.example.controller.manageitem;

import org.example.controller.Context;
import org.example.controller.State;
import org.example.optionsmanager.Menue;
import org.example.optionsmanager.Option;
import org.example.optionsmanager.itemopt.AddCSVOpt;
import org.example.optionsmanager.itemopt.AddItemOpt;

public class AddMenue implements State {
    private Context context;

    public AddMenue(Context context) {
        this.context = context;
    }

    @Override
    public void handleInput() {
        Option[] options = {
            new AddItemOpt(context),
            new AddCSVOpt(context)
        };
        Menue menue = new Menue(options);
        menue.showMenu();
    }
}
