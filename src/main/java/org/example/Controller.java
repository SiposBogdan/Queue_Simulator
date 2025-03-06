package org.example;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Controller implements ActionListener {
    public  SimulationFrame simFrame;
    public SimulationManager simManag;
    public File fisierOut;

    public Controller(SimulationFrame simFrame){
        this.simFrame = simFrame;
    }

    public Controller(File fisierOut, SimulationFrame simFra) {
        this.fisierOut = fisierOut;
        this.simFrame = simFra;
    }

    int policy = 0;
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == simFrame.ShortestTime){
            policy = 0;
            //System.out.println("MAAAAAAAA");
            SimulationManager gen = new SimulationManager(simFrame);
            Thread t = new Thread(gen);
            t.start();
            try{
                t.join();
            }catch(Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        if(e.getSource() == simFrame.ShortestQueue){
            policy = 1;
            SimulationManager gen = new SimulationManager(simFrame);
            Thread t = new Thread(gen);
            t.start();
            try{
                t.join();
            }catch(Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

    }
}
