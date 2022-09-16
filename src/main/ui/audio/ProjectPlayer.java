package ui.audio;

import model.Project;
import model.Track;

import java.util.*;


// A class which handles playing back the loaded project.

public class ProjectPlayer {

    // Constructor for ProjectPlayer
    // EFFECTS: For each track in the project, adds creates a new Timer.
    //          Adds fixed rate schedule to created to timer with delay calculated from current tempo.
    public ProjectPlayer(Project project) {
        for (Track track : project.getTrackList()) {
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new AudioPlayer(track, timer), 200, calculateDelay(project.getTempo()));
        }
    }

    // EFFECTS: Converts beats per minute to milliseconds per sixteenth note.
    private long calculateDelay(int bpm) {
        long millisecondsInASecond = 1000;
        long secondsInAMinute = 60;
        long sixteenthNotesPerBeat = 4;

        return ((millisecondsInASecond * secondsInAMinute) / bpm) / sixteenthNotesPerBeat;
    }
}