package org.example.optionsmanager.reportopt;

import org.example.controller.Context;
import org.example.controller.report.CurrentStatus;
import org.example.controller.report.TransactionHistory;
import org.example.optionsmanager.Option;

public class CurrentStatusOpt extends Option {
    private Context context;

    public CurrentStatusOpt(Context context) {
        super("Current Status");
        this.context = context;
    }

    @Override
    public void run(){
        CurrentStatus currentStatus = new CurrentStatus(context);
        currentStatus.handleInput();
    }
}