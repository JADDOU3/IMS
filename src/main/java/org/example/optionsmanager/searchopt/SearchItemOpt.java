package org.example.optionsmanager.searchopt;

import org.example.controller.Context;
import org.example.controller.search.SearchItem;
import org.example.optionsmanager.Option;

public class SearchItemOpt extends Option {
    private Context context;

    public SearchItemOpt(Context context) {
        super("Search for Items");
        this.context = context;
    }

    @Override
    public void run(){
        SearchItem searchItem = new SearchItem(context);
        searchItem.handleInput();
    }
}
