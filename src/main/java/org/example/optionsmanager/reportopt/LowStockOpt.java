package org.example.optionsmanager.reportopt;

import org.example.controller.Context;
import org.example.controller.report.GenerateReportState;
import org.example.controller.report.LowStock;
import org.example.optionsmanager.Option;

public class LowStockOpt extends Option {
    private Context context;

    public LowStockOpt(Context context) {
        super("Low Stock");
        this.context = context;
    }

    @Override
    public void run(){
        LowStock lowStockOpt = new LowStock(context);
        lowStockOpt.handleInput();
    }
}
