package persistence;

import model.Project;
import model.Track;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

// A class which handles reading JSON files and converting them to Projects

// Class based on from JsonReader in JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class JsonReader {
    private final String filePath;
    private final FileReader fileReader;


    // Constructor for JsonReader
    // EFFECTS: Sets filePath to given path. Instantiates a new FileReader.
    public JsonReader(String filePath) {
        this.filePath = filePath;
        fileReader = new FileReader();
    }

    // EFFECTS: Loads the JSON file at filePath and returns it as a Project.
    public Project loadProject() throws IOException {
        String dataString = fileReader.readFile(filePath);
        JSONObject dataJson = new JSONObject(dataString);

        return makeProject(dataJson);
    }



    // [SETTERS AND GETTERS]
    public String getFilePath() {
        return filePath;
    }



    // [HELPER METHODS]

    // EFFECTS: Returns a new Project instance from given JSON data.
    private Project makeProject(JSONObject dataJson) {
        String name = dataJson.getString("name");
        int tempo = dataJson.getInt("tempo");
        int length = dataJson.getInt("length");
        int selectedTrack = dataJson.getInt("selectedTrack");
        JSONArray trackArray = dataJson.getJSONArray("trackList");

        Project project = new Project(name, tempo, length);

        project.setSelectedTrack(selectedTrack);

        for (Object trackDataObject : trackArray) {
            JSONObject trackDataJson = (JSONObject) trackDataObject;
            addTrack(trackDataJson, project, project.getTrackList());
        }

        return project;
    }

    // EFFECTS: Adds a Track to project from given trackData.
    private void addTrack(JSONObject trackData, Project project, List<Track> trackList) {
        String name = trackData.getString("name");
        String fileName = trackData.getString("fileName");
        boolean enabled = trackData.getBoolean("enabled");
        String sequence = trackData.getString("sequence");

        project.addTrack(name, fileName);
        Track currentTrack = trackList.get(trackList.size() - 1);

        currentTrack.setEnabled(enabled);

        for (int i = 0; i < currentTrack.getLength(); i++) {
            if (sequence.charAt(i) == '*') {
                currentTrack.toggleNote(i);
            }
        }
    }
}