package SecondWeek;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.*;

/*
 * A clock that prints the time in regular intervals.
 */
public class TalkingClock {
    private int interval;
    private boolean beep;

    /**
     * Constructs a talking clock
     * @param interval the interval between messages (in milliseconds)
     * @param beep true if the clock should beep
     */
    public TalkingClock(int interval,boolean beep){
        this.interval = interval;
        this.beep = beep;
    }

    /**
     * Starts the clock.
     */
    public void start(){
        var listener = new TimePrinter();
        var timer = new Timer(interval,listener);
        timer.start();
    }

    public class TimePrinter implements ActionListener{
        public void actionPerformed(ActionEvent event){
            System.out.println("At the tone, the time is " + Instant.ofEpochMilli(event.getWhen()));
            if (TalkingClock.this.beep) Toolkit.getDefaultToolkit().beep();
        }
    }
}
