package org.example.controller;

import java.util.Scanner;

public class Context {
    private State currentState;
    private Scanner scanner;

    private final String URL = "jdbc:mysql://localhost:3306/ims";
    private final String USERNAME = "root";
    private final String PASSWORD = "root123";

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

    public String[] getDatabaseInfo(){
        return new String[]{URL, USERNAME, PASSWORD};
    }

}
