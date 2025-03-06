package org.example;

public class Task {
    private int arrivalTime;
    private int serviceTime;
    private int finishTime;
    private int idTask;

    public Task(int arrivalTime, int serviceTime, int id) {
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.idTask = id;
    }

    public int getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }

    public int getIdTask() {
        return idTask;
    }

    public void setId(int idTask) {
        this.idTask = idTask;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }
    @Override
    public String toString(){
        return "(" + this.idTask + "," + this.arrivalTime + "," + this.serviceTime + ")";
    }

}
