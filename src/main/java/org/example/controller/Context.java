package org.example.controller;

import java.util.Scanner;

public class Context {
    private State currentState;
    private Scanner scanner;

    public Scanner getScanner(){
        scanner = new Scanner(System.in);
        return scanner;
    }

    public void setCurrentState(State currentState){
        this.currentState = currentState;
    }

    public void handleInput(){
        currentState.handleInput();
    }



}
