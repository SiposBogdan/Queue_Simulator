package org.example;

import java.util.List;

public interface Strategy {
    public void addTask(List<Server> servers, Task t, List<Thread> threads);
}
