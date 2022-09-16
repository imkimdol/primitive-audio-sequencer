package ui.audio;

import model.Track;

import javax.sound.sampled.*;
import java.io.File;
import java.util.*;


// A class which handles playing a single sound.

public class AudioPlayer extends TimerTask {
    private final Track track;
    private final File soundFile;
    private final Timer timer;
    private final int maxTime;

    private Clip clip;
    private int currentTime;


    // Constructor for AudioPlayer
    // EFFECTS: Sets track and timer to given values, sets soundFile from the given Track.
    //          Sets current time to 0 and max time to track's length.
    public AudioPlayer(Track track, Timer timer) {
        this.track = track;
        this.timer = timer;

        soundFile = track.getSoundFile();

        currentTime = 0;
        maxTime = track.getLength();
    }

    @Override
    // MODIFIES: clip
    // EFFECTS:  For each call to this method:
    //           Check if current time is equal to max time. If yes, stop the timer.
    //           Otherwise, check if the current note is enabled. If yes, initialize and play sound.
    //           Increment time.
    public void run() {
        if (currentTime >= maxTime) {
            timer.cancel();
        } else if (track.getNote(currentTime) && track.getEnabled()) {
            initializeSound();
            clip.start();
        }

        currentTime++;
    }

    // MODIFIES: this, clip
    // EFFECTS:  Initializes the class for playback.
    private void initializeSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
