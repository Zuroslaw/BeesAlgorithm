package util;

import java.util.Stack;

/**
 * Created by mateu on 13.12.2016.
 */

class TimerData {
    public long id;
    public long time;
    public String ticName;

    TimerData(long id, long time) {this.id = id; this.time = time; this.ticName = null;}
    TimerData(long id, long time, String ticName) {this(id, time); this.ticName = ticName;}
}

public class Timer {

    private static long idCount = 0;

    private static Stack<TimerData> TimerStack = new Stack<>();

    private Timer() {}

    public static void tic() {
        TimerData timerValue = TimerStack.push(new TimerData(idCount++, System.currentTimeMillis()));
        displayTic(timerValue);
    }

    public static void tic(String ticName) {
        TimerData timerValue = TimerStack.push(new TimerData(idCount++, System.currentTimeMillis(), ticName));
        displayTic(timerValue);
    }

    public static double toc() {
        TimerData timerValue = TimerStack.pop();
        return displayToc(timerValue);
    }

    private static void displayTic(TimerData timerValue) {
        System.out.println(""+ (timerValue.ticName == null ? "":(timerValue.ticName + "-> ")) +  "### Timer: Beginning time measurement with id: " + timerValue.id);
    }

    private static double displayToc(TimerData timerValue) {
        double currentTimeMillis = System.currentTimeMillis();
        double ticTimeMillis = timerValue.time;
        double seconds = (currentTimeMillis - ticTimeMillis)/1000;
        System.out.println(""+ (timerValue.ticName == null ? "":(timerValue.ticName + "-> ")) +  "### Timer: Time of measurement with id: " + timerValue.id + " -> " + seconds);
        return seconds;
    }
}
