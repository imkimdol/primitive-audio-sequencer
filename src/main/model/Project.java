package model;

import java.util.ArrayList;
import java.util.List;


// A class which contains all data about a project.
// Consists of a name, tempo (in bpm), length, and list of tracks.
// All length units are in 16th notes.

public class Project {
    private final List<Track> trackList;
    private String name;
    private int tempo;
    private int length;
    private int selectedTrack;


    // Constructor for Project
    // REQUIRES: tempo > 0, length > 0, notesPerPage > 0
    // EFFECTS:  Sets the name, tempo, and length values from given parameters.
    //           Sets the selected track to 0.
    //           Creates a new LinkedList of Tracks and adds a track named "Beep" with beep.wav file.
    public Project(String name, int tempo, int length) {
        this.name = name;
        this.tempo = tempo;
        this.length = length;

        selectedTrack = 0;

        trackList = new ArrayList<>();
    }

    // MODIFIES: this, trackList
    // EFFECTS:  Extends the project and each track's length by given length.
    public void extendProject(int length) {
        this.length += length;

        for (Track track: trackList) {
            track.extendTrack(length);
        }
    }

    // REQUIRES: fileName is a valid file path
    // MODIFIES: trackList, EventLog.getInstance()
    // EFFECTS:  Adds the track in the trackList at the given position.
    //           Logs this event.
    public void addTrack(String trackName, String fileName) {
        trackList.add(new Track(trackName, fileName, length));

        EventLog.getInstance().logEvent(new Event(
                "Track '" + trackName + "' with file '" + fileName + "' added to project."));
    }

    // REQUIRES: The track at the given position exists
    // MODIFIES: trackList, EventLog.getInstance()
    // EFFECTS:  Removes the track in the trackList at the given position.
    //           Logs this event.
    public void removeTrack(int position) {
        String trackName = trackList.get(position).getName();
        trackList.remove(position);

        EventLog.getInstance().logEvent(new Event("Track '" + trackName + "' removed."));
    }


    // GETTERS AND SETTERS
    public String getName() {
        return name;
    }

    public int getTempo() {
        return tempo;
    }

    public int getSelectedTrack() {
        return selectedTrack;
    }

    public Track getTrack(int position) {
        return trackList.get(position);
    }

    public List<Track> getTrackList() {
        return trackList;
    }

    public int getLength() {
        return length;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSelectedTrack(int index) {
        selectedTrack = index;
    }

    public void setTempo(int bpm) {
        tempo = bpm;
    }

}
