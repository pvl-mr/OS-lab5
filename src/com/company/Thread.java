package com.company;

public class Thread {
    private int tid;
    private int runTime;

    public Thread(int tid, int runTime) {
        this.tid = tid;
        this.runTime = runTime;
    }

    public void runThread(int pid) {
        System.out.println("Thread " + tid + " of process " + pid + " is running");
    }


}
