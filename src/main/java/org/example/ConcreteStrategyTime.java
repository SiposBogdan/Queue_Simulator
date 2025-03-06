package org.example;

import java.util.List;

public class ConcreteStrategyTime implements Strategy{

    @Override
    public void addTask(List<Server> servers, Task t, List<Thread> threads) {
        int timpMin = 9999;
        int min = 0;
        for(int i = 0; i < servers.size(); i++){
            if(servers.get(i).getWaitingPeriod() < timpMin){
                timpMin = servers.get(i).getWaitingPeriod();
                min = i;
            }
            if(timpMin == 0){
                break;
            }
        }
        servers.get(min).addTask(t);
        //System.out.println("AAAAAAAAAAAAAAAAAA");
        if(servers.get(min).isStare() != true)
        {
            servers.get(min).setStare(true);
            threads.set(min, new Thread(servers.get(min)));
            System.out.println("se activeaza");
            threads.get(min).start();
        }

    }
}
