package com.company;

import java.util.ArrayList;

public class Process {

    private int pid;
    private boolean isBlocked;

    private int runTime;
    private ArrayList threads;

    public Process(int pid) {
        this.pid = pid;
        threads = new ArrayList();
    }

    public int getId() {
        return pid;
    }

    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }

    public boolean getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    @Override
    public String toString() {
        String strIsBlocked = isBlocked ? " is blocked " : " isn't blocked";
        return "Process " + pid + ". Runtime: " + runTime + strIsBlocked;
    }

    public void addThread(Thread newThread) {
        threads.add(newThread);
    }

    public ArrayList<Thread> getThreads() {
        return threads;
    }

}
