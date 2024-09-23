package org.example.optionsmanager.reportopt;

import org.example.controller.Context;
import org.example.controller.report.GenerateReportState;
import org.example.optionsmanager.Option;

public class GenerateReport extends Option {
    private Context context;

    public GenerateReport(Context context) {
        super("Generate Report");
        this.context = context;
    }

    @Override
    public void run(){
        GenerateReportState generateReportState = new GenerateReportState(context);
        generateReportState.handleInput();
    }
}
