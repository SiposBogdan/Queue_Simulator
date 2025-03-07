# Queue Simulation Application

## Overview
This project is a **Queue Simulation System** developed in **Java**. It simulates customer queues using different distribution strategies, allowing users to set parameters, visualize results, and generate statistical reports.

---

## Features
- Simulation of multiple queues with dynamic allocation.
- Multithreading – One thread per queue for parallel processing.
- Thread Synchronization – Ensures safe data handling.
- Two distribution strategies:
  - Shortest Time
  - Shortest Queue
- Graphical User Interface (GUI)
- Results exported to files.
- Statistical data such as **Average Waiting Time**, **Peak Hour**, and **Average Service Time**.

---

## Technologies Used
- **Java**
- **Swing** for GUI
- **BlockingQueue** for thread-safe queue management
- File I/O for saving results

---

## How It Works
1. The user sets simulation parameters:
   - Number of clients
   - Number of queues
   - Simulation time
   - Arrival and service time intervals
2. The system assigns customers to queues using the selected strategy.
3. The simulation updates in real-time on the GUI.
4. Final statistics are displayed both in the GUI and saved to a file.
