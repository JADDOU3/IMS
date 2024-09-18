package org.example.optionsmanager.searchopt;

import org.example.controller.Context;
import org.example.controller.search.SearchContactC;
import org.example.controller.search.SearchContactN;
import org.example.controller.search.SearchState;
import org.example.optionsmanager.Option;

public class SearchCCatagory extends Option {
    private Context context;

    public SearchCCatagory(Context context) {
        super("Search for Contact by type");
        this.context = context;
    }

    @Override
    public void run(){
        SearchContactC searchContactC = new SearchContactC(context);
        searchContactC.handleInput();
    }
}
