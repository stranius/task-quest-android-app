package com.example.payto.taskquest;

import java.util.ArrayList;

/**
 * Created by payto on 8/16/2017.
 */

public class Game {
    public static ArrayList<Task> tasks = new ArrayList<Task>();

    public void addTask(Task task) {
        tasks.add(task);
    }
}
