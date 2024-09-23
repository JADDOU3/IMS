package org.example.controller.report;

import org.example.controller.Context;
import org.example.controller.State;
import org.example.optionsmanager.Menue;
import org.example.optionsmanager.Option;

public class LowStock implements State {
    private Context context;

    public LowStock(Context context) {
        this.context = context;
    }

    @Override
    public void handleInput() {


    }
}
