package org.example;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.io.File;

public class SimulationFrame extends JFrame {
    JPanel panel;
    Controller controller = new Controller(this);
    JTextField nrClientiPanel;
    JTextField nrRanduriPanel;
    JTextField maxSimTimePanel;
    JTextField minTimeArrPanel;
    JTextField maxTimeArrPanel;
    JTextField minSerPanel;
    JTextField maxSerPanel;
    JTextField zonaAfis;
    JTextArea afisareRez;

    JRadioButton ShortestTime;
    JRadioButton ShortestQueue;
    JScrollPane scrollPane;
    File fisierOut;

    public SimulationFrame(){
        //nrClientiPanel.setText("Introduceti numarul de clienti");
        this.panel = new JPanel();
        nrClientiPanel = new JTextField();
        nrRanduriPanel = new JTextField();
        minSerPanel = new JTextField();
        maxSerPanel = new JTextField();
        minTimeArrPanel = new JTextField();
        maxTimeArrPanel = new JTextField();
        maxSimTimePanel = new JTextField();

        nrClientiPanel.setText("Introduceti numarul de clienti");
        nrClientiPanel.setPreferredSize(new Dimension(200,40));
        nrRanduriPanel.setText("Introduceti numarul de randuri");
        nrRanduriPanel.setPreferredSize(new Dimension(200,40));
        minSerPanel.setText("Introduceti durata minima a serviciului");
        minSerPanel.setPreferredSize(new Dimension(200,40));
        maxSerPanel.setText("Introduceti durata maxima a serviciului");
        maxSerPanel.setPreferredSize(new Dimension(200,40));;
        minTimeArrPanel.setText("Introduceti timpul minim de sosire");
        minTimeArrPanel.setPreferredSize(new Dimension(200,40));
        maxTimeArrPanel.setText("Introduceti timpul maxim de sosire");
        maxTimeArrPanel.setPreferredSize(new Dimension(200,40));
        maxSimTimePanel.setText("Introduceti timpul maxim de simulare");
        maxSimTimePanel.setPreferredSize(new Dimension(200,40));
        panel.add(nrClientiPanel);
        panel.add(nrRanduriPanel);
        panel.add(maxSimTimePanel);
        panel.add(minSerPanel);
        panel.add(maxSerPanel);
        panel.add(minTimeArrPanel);
        panel.add(maxTimeArrPanel);
        ShortestTime = new JRadioButton("Shortest time");
        ShortestTime.addActionListener(this.controller);
        ShortestQueue = new JRadioButton("Shortest queue");
        ShortestQueue.addActionListener(this.controller);
        ButtonGroup var = new ButtonGroup();
        var.add(ShortestQueue);
        var.add(ShortestTime);
        panel.add(ShortestTime);
        panel.add(ShortestQueue);
        afisareRez = new JTextArea(15,40);
        //afisareRez.setPreferredSize(new Dimension(400, 200));

// Create JScrollPane and add JTextArea to it
        scrollPane = new JScrollPane(afisareRez,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        //scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
// Set the preferred size of the JScrollPane to match the JTextArea
        //scrollPane.setPreferredSize(new Dimension(400, 200));
        //DefaultCaret caret = (DefaultCaret) afisareRez.getCaret();
        //caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        // Add JScrollPane to JPanel
        panel.add(scrollPane);
        //this.pagetContentPane().add(scrollPane);
        //this.getContentPane().add(scrollPane);
        //afisareRez.setText("Aici va aparea rezultatul");

        //panel.add(afisareRez);

        panel.setVisible(true);
        panel.setBackground(Color.gray);

        this.add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(500, 500));
        this.setVisible(true);
        this.setLayout(null);
    }

}
