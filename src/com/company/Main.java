package com.company;

public class Main {

    public static void main(String[] args) {
        SystemCore core = new SystemCore();
        core.createProcesses();
        core.performPlan();
    }
}
