package org.example.controller.managetransaction;

import org.example.controller.Context;
import org.example.controller.State;

public class UpdateTransaction implements State {
    private Context context;

    public UpdateTransaction(Context context){
        this.context = context;
    }

    @Override
    public void handleInput() {

    }
}
