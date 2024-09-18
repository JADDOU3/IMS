package org.example.optionsmanager.searchopt;

import org.example.controller.Context;
import org.example.controller.search.SearchState;
import org.example.optionsmanager.Option;

public class SearchOpt extends Option {
    private Context context;

    public SearchOpt(Context context) {
        super("Search");
        this.context = context;
    }

    @Override
    public void run(){
        SearchState searchState = new SearchState(context);
        searchState.handleInput();
    }
}
