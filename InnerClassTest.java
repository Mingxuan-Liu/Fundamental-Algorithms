package SecondWeek;

import java.awt.*;
import java.awt.event.*;
import java.time.*;

import javax.swing.*;

/**
 * This program demonstrates the use of inner classes.
 * @version 1.11 2017-12-14
 * @author Cay Horstmann
 */
public class InnerClassTest {
    public static void main(String[] args){
        TalkingClock clock = new TalkingClock(1000,true);
        clock.start();

        // keep o=program running until the user selects "OK"
        JOptionPane.showMessageDialog(null,"Quit program");
        System.exit(0);
    }
}
