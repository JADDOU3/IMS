package org.example.optionsmanager.transopt;

import org.example.controller.Context;
import org.example.controller.managetransaction.ManageTransaction;
import org.example.controller.managetransaction.UpdateTransaction;
import org.example.optionsmanager.Option;

public class UpdateTransOpt extends Option {
    private Context context;

    public UpdateTransOpt(Context context) {
        super("Update Transaction");
        this.context = context;
    }

    @Override
    public void run(){
        UpdateTransaction updateTransaction = new UpdateTransaction(context);
        updateTransaction.handleInput();
    }
}
