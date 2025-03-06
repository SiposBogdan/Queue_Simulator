package org.example;

import java.util.List;

public class ConcreteStrategyQueue implements Strategy{

    @Override
    public void addTask(List<Server> servers, Task t, List<Thread> threads) {
        int coadaMin = 9999;
        int min = 0;
        for(int i = 0; i < servers.size(); i++){
            if(servers.get(i).getLaCoada() < coadaMin){
                coadaMin = servers.get(i).getLaCoada();
                min = i;
            }
            if(coadaMin == 0){
                break;
            }
        }
        for(int i = 0; i < servers.size(); i++) {
            if(i == min) {
                servers.get(i).setStare(true);
                servers.get(i).addTask(t);
            }
        }
        if(servers.get(min).isStare() != true)
        {
            servers.get(min).setStare(true);
            threads.set(min, new Thread(servers.get(min)));
            threads.get(min).start();
        }
    }
}
