package org.example.optionsmanager.reportopt;

import org.example.controller.Context;
import org.example.controller.report.TransactionHistory;
import org.example.optionsmanager.Option;

public class TransactionHistoryOpt extends Option {
    private Context context;

    public TransactionHistoryOpt(Context context) {
        super("Transactions History");
        this.context = context;
    }

    @Override
    public void run(){
        TransactionHistory transactionHistory = new TransactionHistory(context);
        transactionHistory.handleInput();
    }
}
