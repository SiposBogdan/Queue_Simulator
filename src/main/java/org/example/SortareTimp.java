package org.example;

import java.util.Comparator;

public class SortareTimp implements Comparator<Task> {
    public int compare(Task x, Task y)
    {
        return x.getArrivalTime()-y.getArrivalTime();
    }
}
