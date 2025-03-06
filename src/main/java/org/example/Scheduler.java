package org.example;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private ArrayList<Server> servers;
    private ArrayList<Thread> threads;
    private int maxNoServers = 0;
    int policy;
    private int maxTasksPerServer = 0;
    private Strategy strategy;
    public Scheduler(int  maxNoServers, int maxTasksPerServer){
        this.maxNoServers = maxNoServers;
        this.maxTasksPerServer = maxTasksPerServer;
        this.servers = new ArrayList<Server>(maxNoServers);
        this.threads = new ArrayList<Thread>(maxNoServers);
        for(int i = 0; i < maxNoServers; i++){
            servers.add(new Server(maxNoServers, i));
            threads.add(new Thread(servers.get(i)));
            threads.get(i).start();
            //System.out.println(servers.get(i).isStare());
        }
        //this.strategy = new ConcreteStrategyTime();
    }
    public void changeStrategy(SelectionPolicy policy){

        if(policy == SelectionPolicy.SHORTEST_QUEUE){
            strategy = new ConcreteStrategyQueue();
        }
        if(policy == SelectionPolicy.SHORTEST_TIME){
            strategy = new ConcreteStrategyTime();
        }
    }
    private int getTimeMin(){
        int min = 0;
        int timpMin = 9999;
        for(int i = 0; i < maxNoServers; i++){
            if(servers.get(i).getWaitingPeriod() < timpMin){
                timpMin = servers.get(i).getWaitingPeriod();
                min = i;
            }
            if(timpMin == 0){
                break;
            }
        }
        return min;
    }
    private int getCoadaMin(){
        int min = 0;
        int coadaMin = 9999;
        for(int i = 0; i < maxNoServers; i++){
            if(servers.get(i).getLaCoada() < coadaMin){
                coadaMin = servers.get(i).getLaCoada();
                min = i;
            }
            if(coadaMin == 0){
                break;
            }
        }
        // System.out.println(min);
        return min;
    }
    public int dispatchTask(Task t, int policy){
        //strategy = new ConcreteStrategyTime();
        int minTimeQueue;
        if(policy == 0)
            minTimeQueue = getCoadaMin();
        else
            minTimeQueue = getTimeMin();
        //System.out.println(servers.get(minTimeQueue).isOpen());
        int aux = 0;
        aux = servers.get(minTimeQueue).getWaitingPeriod();
        servers.get(minTimeQueue).addTask(t);
        if(servers.get(minTimeQueue).isStare() != true)
        {
            servers.get(minTimeQueue).setStare(true);
            threads.set(minTimeQueue, new Thread(servers.get(minTimeQueue)));
            threads.get(minTimeQueue).start();
        }
        return aux;
    }
    public List<Server> getServers(){
        return servers;
    }
    public int getLongestQeue()
    {
        int maxim = 0;
        for(Server server : servers)
        {
            if (server.getWaitingPeriod() > maxim) {
                maxim = server.getWaitingPeriod();
            }
        }
        return maxim;
    }

    public String toString() {
        String rezultat="";
        for (int i = 0; i < servers.size(); i++) {
            rezultat+= "Queue " + i + ": " + servers.get(i).toString() + "\n";
        }

        return rezultat;
    }
}
