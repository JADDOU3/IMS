package org.example.optionsmanager.searchopt;

import org.example.controller.Context;
import org.example.controller.search.SearchContactN;
import org.example.controller.search.SearchState;
import org.example.optionsmanager.Option;

public class SearchCNameOpt extends Option {
    private Context context;

    public SearchCNameOpt(Context context) {
        super("Search for Contact by Name");
        this.context = context;
    }

    @Override
    public void run(){
        SearchContactN searchContactN = new SearchContactN(context);
        searchContactN.handleInput();
    }
}
