package model;

import java.io.File;
import java.util.List;
import java.util.ArrayList;


// A class which represents a single track in a project.
// Consists of a name, sound file, and sequence of booleans.

public class Track {
    private final String name;
    private boolean enabled;

    private final String fileName;
    private final File soundFile;
    private final List<Boolean> sequence;


    // Constructor for track
    // REQUIRES: length > 0, filePath is a valid file path.
    // EFFECTS:  Assigns a name, a file name, and a sound file to the Track.
    //           Creates a sequence of given length. Enables the track.
    public Track(String name, String fileName, int length) {
        this.name = name;
        this.fileName = fileName;
        this.soundFile = new File("." + File.separator + "data" + File.separator + fileName);

        this.sequence = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            sequence.add(false);
        }

        enabled = true;
    }

    // REQUIRES: length > 0
    // MODIFIES: this
    // EFFECTS:  Extends the track's sequence by the given length.
    public void extendTrack(int length) {
        for (int i = 0; i < length; i++) {
            sequence.add(false);
        }
    }

    // REQUIRES: Given position < track length
    // MODIFIES: this, EventLog.getInstance()
    // EFFECTS:  Flip the value in the track's sequence at the given position.
    //           Logs this event.
    public void toggleNote(int position) {
        sequence.set(position, !sequence.get(position));

        EventLog.getInstance().logEvent(new Event("Toggled note of track '" + name + "' at index " + position + "."));
    }



    // SETTERS AND GETTERS
    public String getName() {
        return name;
    }

    public int getLength() {
        return sequence.size();
    }

    public boolean getEnabled() {
        return enabled;
    }

    public boolean getNote(int position) {
        return sequence.get(position);
    }

    public File getSoundFile() {
        return soundFile;
    }

    public String getFileName() {
        return fileName;
    }

    public void setEnabled(boolean state) {
        enabled = state;
    }
}
