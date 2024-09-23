package org.example.controller.report;

import org.example.controller.Context;
import org.example.controller.State;
import org.example.optionsmanager.Menue;
import org.example.optionsmanager.Option;
import org.example.optionsmanager.reportopt.CurrentStatusOpt;
import org.example.optionsmanager.reportopt.LowStockOpt;
import org.example.optionsmanager.reportopt.TransactionHistoryOpt;

public class GenerateReportState implements State {
    private Context context;

    public GenerateReportState(Context context) {
        this.context = context;
    }

    @Override
    public void handleInput() {
        Option[] options = {
                new CurrentStatusOpt(context),
                new LowStockOpt(context),
                new TransactionHistoryOpt(context)
        };
        Menue menue = new Menue(options);
        menue.showMenu();

    }
}
