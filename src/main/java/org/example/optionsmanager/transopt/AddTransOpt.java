package org.example.optionsmanager.transopt;

import org.example.controller.Context;
import org.example.controller.managetransaction.RecordTransaction;
import org.example.optionsmanager.Option;

public class AddTransOpt extends Option {
    private Context context;

    public AddTransOpt(Context context) {
        super("Record Transaction");
        this.context = context;
    }

    @Override
    public void run(){
        RecordTransaction recordTransaction = new RecordTransaction(context);
        recordTransaction.handleInput();
    }
}
