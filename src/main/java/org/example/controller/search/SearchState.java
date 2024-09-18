package org.example.controller.search;

import org.example.controller.Context;
import org.example.controller.State;
import org.example.optionsmanager.Menue;
import org.example.optionsmanager.Option;
import org.example.optionsmanager.searchopt.SearchCCatagory;
import org.example.optionsmanager.searchopt.SearchCNameOpt;
import org.example.optionsmanager.searchopt.SearchItemOpt;

public class SearchState implements State {
    private Context context;

    public SearchState(Context context) {
        this.context = context;
    }

    @Override
    public void handleInput() {
        Option[] options = {
                new SearchCNameOpt(context),
                new SearchCCatagory(context),
                new SearchItemOpt(context)
        };
        Menue menue = new Menue(options);
        menue.showMenu();

    }
}
