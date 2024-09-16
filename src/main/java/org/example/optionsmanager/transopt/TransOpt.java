package org.example.optionsmanager.transopt;

import org.example.controller.Context;
import org.example.controller.managetransaction.ManageTransaction;
import org.example.optionsmanager.Option;

public class TransOpt extends Option {
    private Context context;

    public TransOpt(Context context) {
        super("Manage Transactions");
        this.context = context;
    }

    @Override
    public void run(){
        ManageTransaction manageTransaction = new ManageTransaction(context);
        manageTransaction.handleInput();
    }
}
