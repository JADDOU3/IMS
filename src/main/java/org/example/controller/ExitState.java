package org.example.controller;

public class ExitState implements State{
    private Context context;

    public ExitState(Context context) {
        this.context = context;
    }

    @Override
    public void handleInput() {

    }
}
