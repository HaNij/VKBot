package com.huni;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerManager extends TimerTask {

    private Timer timer;
    private int sec;

    public TimerManager(int sec) {
        this.sec = sec;
        timer = new Timer(true);
        timer.scheduleAtFixedRate(this,0,sec*1000);
    }
    private void completeTask() {
        try {
            Thread.sleep(sec*1000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        System.out.println("TimerTask start at " + new Date());
        completeTask();
        System.out.println("TimerTast ends at " + new Date());
    }
}
