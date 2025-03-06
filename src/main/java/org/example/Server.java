package org.example;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable{
    private BlockingQueue<Task> tasks;
    private int waitingPeriod;
    private int waitingTasks = 0;
    private int laCoada;
    private boolean stare = true;
    private int id;

    public Server(int maxload, int id){
        this.tasks = new ArrayBlockingQueue<Task>(maxload + 22);
        this.waitingPeriod = 0;
        this.laCoada = 0;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isStare() {
        return stare;
    }

    public int getLaCoada() {
        return laCoada;
    }

    public void setLaCoada(int laCoada) {
        this.laCoada = laCoada;
    }

    public void setStare(boolean stare) {
        this.stare = stare;
    }
    public BlockingQueue<Task> getTasks() {
        return tasks;
    }

    public void setTasks(BlockingQueue<Task> tasks) {
        this.tasks = tasks;
    }

    public int getWaitingPeriod() {
        return waitingPeriod;
    }

    public void setWaitingPeriod(int waitingPeriod) {
        this.waitingPeriod = waitingPeriod;
    }

    public void addTask(Task newTask){
        this.tasks.add(newTask);
        this.waitingPeriod = waitingPeriod + newTask.getServiceTime();
        this.laCoada++;
    }

    @Override
    public String toString() {
        String result;
        if(this.getWaitingPeriod() == 0 || this.tasks.peek() == null || this.tasks.peek().getServiceTime() == 0){
            result = "closed";
        }
        else{
            result = this.tasks.toString();
        }
        return result;
    }

    @Override
    public void run() {
        while(stare){
            //System.out.println("merge1");
            while(tasks.peek() != null){
                try {
                    int p = tasks.peek().getServiceTime();
                    Thread.sleep(1000);
                    waitingPeriod--;
                    // System.out.println("merge2");
                    p--;
                    tasks.peek().setServiceTime(p);
                    if(p == 0){
                        laCoada--;
                        tasks.peek().setServiceTime(0);
                        tasks.poll();
                    }

                }catch (Exception ex){
                }
            }
            setStare(false);
        }
    }

}
