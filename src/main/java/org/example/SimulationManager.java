package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SimulationManager implements Runnable {
    private SimulationFrame sim;
    public int timeLimit = 180;
    public int maxProcessingTime = 10;
    public int minProcessingTime = 2;
    public int maxArrivalTime = 30;
    public int minArrivalTime = 2;
    public int numberOfServers = 3;
    public int numberOfClients = 100;
    public int simulationTime = 0;
    public float averageTime = 0;
    public int peakHour = 0;
    public float averageServiceTime = 0;
    public int policy;
    public SelectionPolicy selectionPolicy = SelectionPolicy.SHORTEST_TIME;
    private Scheduler scheduler;
    private SimulationFrame frame;
    private File fisierOut;
    private ArrayList<Task> generatedTasks;

    public SimulationManager(SimulationFrame sim) {
        try {
            System.out.println("sssssssss");
            this.sim = sim;
            //this.maxProcessingTime = Integer.parseInt(sim.maxSerPanel.getText());
            this.minProcessingTime = Integer.parseInt(sim.minSerPanel.getText());
            this.maxArrivalTime = Integer.parseInt(sim.maxTimeArrPanel.getText());
            this.minArrivalTime = Integer.parseInt(sim.minTimeArrPanel.getText());
            this.numberOfServers = Integer.parseInt(sim.nrRanduriPanel.getText());
            this.numberOfClients =  Integer.parseInt(sim.nrClientiPanel.getText());
            this.simulationTime = Integer.parseInt(sim.maxSimTimePanel.getText());
            this.averageTime = 0;
            this.policy = policy;
            this.fisierOut = new File("out-test-1.txt");
            this.peakHour = 0;
            System.out.println(this.maxProcessingTime + " " + this.minProcessingTime);
            try {
                this.fisierOut.createNewFile();
            }catch(Exception ex) {
                System.out.println("File does not exists and could not be created ");
                return;
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return;
        }
        generateNRandomTasks();
        //for(Task t: generatedTasks){
        //  System.out.println(t);
        // }
        scheduler = new Scheduler(numberOfServers, numberOfClients);
        // for(Server q : scheduler.getServers())
        // {
        //    System.out.println(q.isStare());
        // }
    }
    private int getMaxWait(int maxWait)
    {
        if(generatedTasks.isEmpty()) //&& maxWait <= 0)
        {
            maxWait = scheduler.getLongestQeue();
        }
        else
        {
            maxWait--;
        }

        return maxWait;
    }
    private int peakHour(){
        int peakTime = 0;
        for(Server s: scheduler.getServers()){
            peakTime = peakTime + s.getLaCoada();
        }
        return peakTime;
    }
    public void generateNRandomTasks(){
        generatedTasks = new ArrayList<Task>(numberOfClients);
        for (int i=0; i<numberOfClients; i++)
        {
            int randomArrivingTime = (int)(Math.random() * (maxArrivalTime - minArrivalTime + 1) + minArrivalTime);
            int randomProcessingTime = (int)(Math.random() * (maxProcessingTime - minProcessingTime + 1) + minProcessingTime);
            Task t= new Task (randomArrivingTime, randomProcessingTime, i);
            generatedTasks.add(t);
        }
        Collections.sort(generatedTasks, new SortareTimp());
    }
    @Override
    public void run() {
        String sir = null;
        FileWriter fisierScrie;
        try{
            fisierScrie = new FileWriter(this.fisierOut.toString());
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
            return;
        }
        int currentTime = 0;
        int asteptatMax = 0;
        int nrClientiProcesati = 0;
        int aux = 0;
        int peakIndex = 0;
        System.out.println("haaaa");
        while(currentTime < simulationTime && (!generatedTasks.isEmpty() || asteptatMax >= 1)){
            while(!generatedTasks.isEmpty() && generatedTasks.get(0).getArrivalTime() == currentTime){
                averageServiceTime = averageServiceTime + generatedTasks.get(0).getServiceTime();
                aux = scheduler.dispatchTask(generatedTasks.get(0), policy);
                averageTime = averageTime + aux;
                if(peakHour < peakHour()){
                    peakHour = peakHour();
                    peakIndex = currentTime;
                }
                //System.out.println(aux);
                nrClientiProcesati++;
                generatedTasks.remove(0);
            }
            String deScrisInFisier = puneInSir(currentTime);
            try{
                sir = sir + deScrisInFisier+"\n";

                fisierScrie.write(deScrisInFisier);
                sim.afisareRez.setText(sir);
                sim.revalidate();
                //Thread.sleep(1000);
            }catch (Exception ex){
                //System.out.println("Nu se poate scrie in fisier");
            }
            asteptatMax = getMaxWait(asteptatMax);
            currentTime++;
            try {
                Thread.sleep(1000);
            }catch (Exception ex){
                //System.out.println("Nu merge sleep");
            }
        }
        for(Server s : scheduler.getServers())
        {
            s.setStare(false);
        }
        try{

            fisierScrie.write("\nAverage waiting time: " + averageTime/nrClientiProcesati+"\n");
            sir = sir + "\nAverage waiting time: " + averageTime/nrClientiProcesati+"\n";
            sim.afisareRez.setText(sir);
            fisierScrie.write("Peak hour: " + peakIndex +"\n");
            sir = sir + "Peak hour: " + peakIndex +"\n";
            sim.afisareRez.setText(sir);
            fisierScrie.write("Average service time: " + averageServiceTime/nrClientiProcesati +"\n");
            sir = sir + "Average service time: " + averageServiceTime/nrClientiProcesati +"\n";
            sim.afisareRez.setText(sir);

        }catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        try{
            fisierScrie.close();
        }catch(Exception ex) {}
    }
    public String puneInSir(int currentTime){
        String sir = "\nTime: "+currentTime+"\n";
        sir = sir + "Waiting clients: ";
        for(int i = 0; i < generatedTasks.size(); i++){
            sir = sir + generatedTasks.get(i).toString();
        }
        sir = sir + "\n" + scheduler.toString();
        return sir;
    }

    public static void main(String[] args) {
        //SelectionPolicy a = SelectionPolicy.SHORTEST_TIME;
        //SimulationManager gen = new SimulationManager(6,2, 15, 2, 2, 4, 60, 0, new File("out-test-1.txt"));
        SimulationFrame sim = new SimulationFrame();
        /*SimulationManager gen = new SimulationManager();
        Thread t = new Thread(gen);
        t.start();
        try{
            t.join();
        }catch(Exception ex) {
            System.out.println(ex.getMessage());
        }*/
    }


}
