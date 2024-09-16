package org.example.optionsmanager.transopt;

import org.example.controller.Context;
import org.example.controller.managetransaction.ManageTransaction;
import org.example.optionsmanager.Option;

public class ViewTransOpt extends Option {
    private Context context;

    public ViewTransOpt(Context context) {
        super("View Transactions");
        this.context = context;
    }

    @Override
    public void run(){
        ManageTransaction manageTransaction = new ManageTransaction(context);
        manageTransaction.viewTransaction();
    }
}
