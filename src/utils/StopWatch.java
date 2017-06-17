package utils;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/* Measures time */
public class StopWatch implements Serializable{

    protected long startTime;
    protected long finishTime;

    public StopWatch() {
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(long finishTime) {
        this.finishTime = finishTime;
    }

    public String getElapsedTime() {
        long elapsed = finishTime - startTime;
        long second = TimeUnit.MILLISECONDS.toSeconds(elapsed);
        long minute = TimeUnit.MILLISECONDS.toMinutes(elapsed);
        long hour = TimeUnit.MILLISECONDS.toHours(elapsed);
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

    public long getElapsedTimeInMillies(){
        return finishTime-startTime;
    }


    public void start() {
        startTime = System.currentTimeMillis();
    }

    public void stop() {
        finishTime = System.currentTimeMillis();
    }
}
