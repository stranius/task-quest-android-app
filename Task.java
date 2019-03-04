package com.example.payto.taskquest;

/**
 * Created by payto on 8/16/2017.
 */

public class Task {
    public int hours, minutes, exp, points, crystal;
    public String name, frequency;

    public Task(String taskName, int taskHours, int taskMinutes, int experience, int pR, String fre) {
        name = taskName;
        hours = taskHours;
        minutes = taskMinutes;
        exp = experience;
        points = pR;
        frequency = fre;
    }

    public String getDuration() {
        String dur = "";

        if(hours > 0) {
            dur += hours + " Hours ";
            if(minutes > 0) {
                dur += minutes + " Minutes";
            }
        }else{
            dur += minutes + " Minutes";
        }

        return dur;
    }

    public void startTask() {

    }

    public void taskComplete() {

    }

    public void remindAtTime() {

    }
}
