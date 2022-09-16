package persistence;

import model.Project;
import model.Track;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest {
    JsonReader jsonReader;
    JsonReader jsonReaderInvalidPath;
    JsonReader jsonReaderEmpty;

    String testDirectory = "." + File.separator + "data" + File.separator + "test" + File.separator;

    @BeforeEach
    public void setup() {


        jsonReader = new JsonReader(testDirectory + "Test Project.json");
        jsonReaderEmpty = new JsonReader(testDirectory + "Test Project Empty.json");
        jsonReaderInvalidPath = new JsonReader(testDirectory + "Project.app");
    }

    @Test
    public void testConstructor() {
        assertEquals(testDirectory + "Test Project.json", jsonReader.getFilePath());
        assertEquals(testDirectory + "Test Project Empty.json", jsonReaderEmpty.getFilePath());
        assertEquals(testDirectory + "Project.app", jsonReaderInvalidPath.getFilePath());
    }

    @Test
    // Also tests makeProject and addTrack as they are helper functions
    // Tests behaviour for all three functions
    public void testLoadProject() {
        try {
            Project readProject = jsonReader.loadProject();

            assertEquals("Test Project", readProject.getName());
            assertEquals(110, readProject.getTempo());
            assertEquals(128, readProject.getLength());
            assertEquals(1, readProject.getSelectedTrack());

            assertEquals(2, readProject.getTrackList().size());

            Track kickTrack = readProject.getTrack(0);
            Track snareTrack = readProject.getTrack(1);

            assertEquals("Kick", kickTrack.getName());
            assertEquals("kick.wav", kickTrack.getFileName());
            assertTrue(kickTrack.getNote(0));
            assertTrue(kickTrack.getNote(4));
            assertTrue(kickTrack.getNote(8));
            assertTrue(kickTrack.getNote(12));

            assertEquals("Snare", snareTrack.getName());
            assertEquals("snare.wav", snareTrack.getFileName());
            assertTrue(snareTrack.getNote(4));
            assertTrue(snareTrack.getNote(12));

        } catch (IOException e) {
            fail("Unexpected IOException");
        }
    }

    @Test
    // Also tests makeProject and addTrack as they are helper functions
    // Tests behaviour for all three functions
    public void testLoadProjectEmpty() {
        try {
            Project readProject = jsonReaderEmpty.loadProject();

            assertEquals("Test Project Empty", readProject.getName());
            assertEquals(90, readProject.getTempo());
            assertEquals(64, readProject.getLength());
            assertEquals(0, readProject.getSelectedTrack());

            assertEquals(0, readProject.getTrackList().size());

        } catch (IOException e) {
            fail("Unexpected IOException");
        }
    }

    @Test
    public void testLoadProjectInvalidFilePath() {
        try {
            jsonReaderInvalidPath.loadProject();
            fail("Expected IOException");
        } catch (IOException e) {
            // Expected behaviour
        }
    }

}