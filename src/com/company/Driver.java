package com.company;

public class Driver {

    private int timeOperation;

    public Driver() {
        timeOperation = (int)Math.random()*100 + 1;
    }

    public int performingOperation() {
        System.out.println("Operation performed.");
        return timeOperation;
    }


}
