package com.company;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Random;

public class SystemCore {
    private ArrayList<Process> readyProcesses = new ArrayList<>();
    private ArrayList<Process> blockingProcesses = new ArrayList<>();

    public void createProcesses() {
        Random random = new Random();
        int numOfProcess = random.nextInt(5) + 1;
        for(int i = 0; i < numOfProcess; i++) {
            Process newProcess = new Process(i);
            newProcess.setIsBlocked(random.nextBoolean());
            int numOfThreads = random.nextInt(5) + 1;
            int runTimeProcess = 0;
            for (int j = 0; j < numOfThreads; j++) {
                int runTimeThread = random.nextInt(100)+1;
                runTimeProcess += runTimeThread;
                Thread newThread = new Thread(j, runTimeThread);
                newProcess.addThread(newThread);
            }
            newProcess.setRunTime(runTimeProcess);
            readyProcesses.add(newProcess);
            System.out.println(newProcess);
        }
    }

    public void performPlan() {
        planUnblockedProcesses();
        System.out.println("------------------------");
        planBlockedProcesses();
    }

    private void planUnblockedProcesses() {
        Driver driver = new Driver();
        for (Process process: readyProcesses) {
            System.out.println("Process " + process.getId() + " is started");
            if (process.getIsBlocked()) {
                System.out.println("Process " + process.getId() + " is blocked "); //блокировка процесса
                System.out.println("Waiting for finishing operations..."); //ожидание окончания операции
                //возврат управления пользовательскому процессу
                for (Thread thread: process.getThreads()) {
                    thread.runThread(process.getId());
                }
            }
            else {
                for (int i = 0; i < process.getThreads().size(); i++) {
                    process.getThreads().get(i).runThread(process.getId());
                }
            }
        }
    }

    private void workWithBlockingProcess() {
        if (!blockingProcesses.isEmpty()) {
            Process currProcess = blockingProcesses.get(0);
            for (int j = 0; j < currProcess.getThreads().size(); j++) {
                currProcess.getThreads().get(j).runThread(currProcess.getId());
            }
            System.out.println("Process " + currProcess.getId() + " finished");
            blockingProcesses.remove(currProcess);
        }
    }

    private void planBlockedProcesses() {
        Driver driver = new Driver();
        boolean isBlockingProcess = false;
        for (int i = 0; i < readyProcesses.size(); i++) {
            System.out.println("Process " + readyProcesses.get(i).getId() + " is started");
            if (readyProcesses.get(i).getIsBlocked()) {
                System.out.println("Process " + readyProcesses.get(i).getId() + " is blocked "); //блокировка процесса
                driver.performingOperation();
                blockingProcesses.add(readyProcesses.get(i));
                continue;
                //возвращение управления операционнной системе и запуск доступного процесса
            } else {
                for (int j = 0; j < readyProcesses.get(i).getThreads().size(); j++) {
                    readyProcesses.get(i).getThreads().get(j).runThread(readyProcesses.get(i).getId());
                }
                System.out.println("Process " + readyProcesses.get(i).getId() + " finished");
            }
            workWithBlockingProcess();//перевод пользовательского процесса в состоянии готовности
        }
        workWithBlockingProcess();
    }


}
