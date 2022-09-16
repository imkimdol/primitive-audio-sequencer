package persistence;

import model.Project;
import model.Track;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest {
    Project project;
    Project projectEmpty;

    String testDirectory = "." + File.separator + "data" + File.separator + "test" + File.separator;

    @BeforeEach
    public void setup() {
        project = new Project("Test Project", 110, 128);
        projectEmpty = new Project("Test Project Empty", 90, 64);

        project.addTrack("Kick", "kick.wav");
        project.addTrack("Snare", "snare.wav");

        Track kickTrack = project.getTrack(0);
        Track snareTrack = project.getTrack(1);

        project.setSelectedTrack(1);

        kickTrack.toggleNote(0);
        kickTrack.toggleNote(4);
        kickTrack.toggleNote(8);
        kickTrack.toggleNote(12);

        snareTrack.toggleNote(4);
        snareTrack.toggleNote(12);
    }

    @Test
    public void testConstructor() {
        try {
            new JsonWriter(project, testDirectory + "Test Project.json");

            JsonReader jsonReader = new JsonReader(testDirectory + "Test Project.json");
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

        } catch (FileNotFoundException e) {
            fail("Unexpected FileNotFoundException");
        } catch (IOException e) {
            fail("Unexpected IOException");
        }
    }

    @Test
    public void testConstructorEmptyProject() {
        try {
            new JsonWriter(projectEmpty, testDirectory + "Test Project Empty.json");

            JsonReader jsonReader = new JsonReader(testDirectory + "Test Project Empty.json");
            Project readProject = jsonReader.loadProject();

            assertEquals("Test Project Empty", readProject.getName());
            assertEquals(90, readProject.getTempo());
            assertEquals(64, readProject.getLength());
            assertEquals(0, readProject.getSelectedTrack());

            assertEquals(0, readProject.getTrackList().size());

        } catch (FileNotFoundException e) {
            fail("Unexpected FileNotFoundException");
        } catch (IOException e) {
            fail("Unexpected IOException");
        }
    }
}