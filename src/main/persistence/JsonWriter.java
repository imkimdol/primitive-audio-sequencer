package persistence;

import model.Project;
import model.Track;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

// A class which handles converting Projects to JSON data and writing them as files

// Class based on from JsonWriter in JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class JsonWriter {
    // Constructor for JsonWriter
    // EFFECTS: Converts given project to JSON data and writes it as a file at filePath
    public JsonWriter(Project project, String filePath) throws FileNotFoundException {
        JSONObject content = projectToJson(project);

        PrintWriter printWriter = new PrintWriter(filePath);

        printWriter.print(content.toString(4));
        printWriter.close();
    }



    // [HELPER METHODS]

    // REQUIRES: project is a valid Project
    // EFFECTS:  Returns a JSONObject which includes all data of given Project
    private JSONObject projectToJson(Project project) {
        JSONObject json = new JSONObject();

        json.put("name", project.getName());
        json.put("tempo", project.getTempo());
        json.put("length", project.getLength());
        json.put("selectedTrack", project.getSelectedTrack());
        json.put("trackList", tracksToJson(project.getTrackList()));

        return json;
    }

    // EFFECTS: Returns a JSONArray which includes all data of given track list
    private JSONArray tracksToJson(List<Track> trackList) {
        JSONArray jsonArray = new JSONArray();

        for (Track track : trackList) {
            jsonArray.put(trackToJson(track));
        }

        return jsonArray;
    }

    // REQUIRES: track is a valid Track
    // EFFECTS:  Returns a JSONObject which includes all data of given Track
    private JSONObject trackToJson(Track track) {
        JSONObject json = new JSONObject();

        json.put("name", track.getName());
        json.put("enabled", track.getEnabled());
        json.put("fileName", track.getFileName());
        json.put("sequence", sequenceToString(track));

        return json;
    }

    // REQUIRES: track has valid sequence and length information
    // EFFECTS:  Converts a track's sequence data into a string.
    //           The character at a certain position is "*" when true and "-" when false.
    private String sequenceToString(Track track) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < track.getLength(); i++) {
            stringBuilder.append(track.getNote(i) ? "*" : "-");
        }

        return stringBuilder.toString();
    }
}