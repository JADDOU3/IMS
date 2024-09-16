package org.example.controller.managetransaction;

import org.example.controller.Context;
import org.example.controller.State;

public class RecordTransaction implements State {
    private Context context;

    public RecordTransaction(Context context){
        this.context = context;
    }

    @Override
    public void handleInput() {

    }
}
