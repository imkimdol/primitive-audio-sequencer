package ui;

import model.Event;
import model.EventLog;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// Responsible for printing the event log when the JFrame window closes.

public class LogPrinter extends WindowAdapter {
    @Override
    // EFFECTS: Prints the contents in EventLog.
    public void windowClosing(WindowEvent e) {
        for (Event event : EventLog.getInstance()) {
            System.out.println(event.toString() + "\n");
        }
    }
}
