package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {

    Project testProject;
    Project testProjectTempo1;
    Project testProjectLength1;

    @BeforeEach
    public void setUp() {
        testProject = new Project("Test Project", 120, 64);

        testProjectTempo1 = new Project("Test Project with Tempo 1", 1, 64);
        testProjectLength1 = new Project("Test Project with Length 1", 120, 1);
    }

    @Test
    public void testConstructor() {
        List<Track> trackList = testProject.getTrackList();

        assertEquals("Test Project", testProject.getName());
        assertEquals(120, testProject.getTempo());
        assertEquals(64, testProject.getLength());
        assertEquals(0, testProject.getSelectedTrack());
        assertEquals(0, trackList.size());
    }

    @Test
    // Tests lower bound
    public void testConstructorTempoOne() {
        List<Track> trackList = testProjectTempo1.getTrackList();

        assertEquals("Test Project with Tempo 1", testProjectTempo1.getName());
        assertEquals(1, testProjectTempo1.getTempo());
        assertEquals(64, testProjectTempo1.getLength());
        assertEquals(0, testProjectTempo1.getSelectedTrack());
        assertEquals(0, trackList.size());
    }

    @Test
    // Tests lower bound
    public void testConstructorLengthOne() {
        List<Track> trackList = testProjectLength1.getTrackList();

        assertEquals("Test Project with Length 1", testProjectLength1.getName());
        assertEquals(120, testProjectLength1.getTempo());
        assertEquals(1, testProjectLength1.getLength());
        assertEquals(0, testProjectLength1.getSelectedTrack());
        assertEquals(0, trackList.size());
    }

    @Test
    public void testExtendProject() {
        testProject.addTrack("Test Track", "beep.wav");

        testProject.extendProject(64);

        assertEquals(64 + 64, testProject.getLength());

        for (Track track : testProject.getTrackList()){
            assertEquals(64 + 64, track.getLength());
        }
    }

    @Test
    // Tests lower bound
    public void testExtendProjectOne() {
        testProject.extendProject(1);

        assertEquals(64 + 1, testProject.getLength());

        for (Track track : testProject.getTrackList()){
            assertEquals(64 + 1, track.getLength());
        }
    }

    @Test
    public void testAddTrackOne() {
        testProject.addTrack("Kick", "kick.wav");

        Track testTrack = testProject.getTrack(0);

        assertEquals(1, testProject.getTrackList().size());

        assertEquals("Kick", testTrack.getName());
        assertEquals("kick.wav", testTrack.getFileName());
    }

    @Test
    public void testAddTracksMultiple() {
        testProject.addTrack("Kick", "kick.wav");
        testProject.addTrack("Snare", "snare.wav");
        testProject.addTrack("Clap", "clap.wav");

        Track kickTrack = testProject.getTrack(0);
        Track snareTrack = testProject.getTrack(1);
        Track clapTrack = testProject.getTrack(2);

        assertEquals(3, testProject.getTrackList().size());

        assertEquals("Kick", kickTrack.getName());
        assertEquals("kick.wav", kickTrack.getFileName());

        assertEquals("Snare", snareTrack.getName());
        assertEquals("snare.wav", snareTrack.getFileName());

        assertEquals("Clap", clapTrack.getName());
        assertEquals("clap.wav", clapTrack.getFileName());
    }

    @Test
    public void testRemoveTrack() {
        testProject.addTrack("Beep", "beep.wav");
        testProject.removeTrack(0);

        assertEquals(0, testProject.getTrackList().size());
    }

    @Test
    public void testRemoveTrackMultiple() {
        testProject.addTrack("Beep", "beep.wav");
        testProject.addTrack("Kick", "kick.wav");
        testProject.addTrack("Snare", "snare.wav");
        testProject.addTrack("Clap", "clap.wav");

        // Beep, Kick, Snare, Clap
        testProject.removeTrack(1);
        // Beep, Snare, Clap
        testProject.removeTrack(2);
        // Beep, Snare

        assertEquals(2, testProject.getTrackList().size());

        Track beepTrack = testProject.getTrack(0);
        Track snareTrack = testProject.getTrack(1);

        assertEquals("Beep", beepTrack.getName());
        assertEquals("beep.wav", beepTrack.getFileName());

        assertEquals("Snare", snareTrack.getName());
        assertEquals("snare.wav", snareTrack.getFileName());
    }

    @Test
    public void testSetName() {
        testProject.setName("Renamed Project");
        assertEquals("Renamed Project", testProject.getName());
    }

    @Test
    public void testSetTempo() {
        testProject.setTempo(60);
        assertEquals(60, testProject.getTempo());
    }

    @Test
    // Tests lower bound
    public void testSetTempoOne() {
        testProject.setTempo(1);
        assertEquals(1, testProject.getTempo());
    }

    @Test
    public void testSetSelectedTrack() {
        testProject.setSelectedTrack(1);
        assertEquals(1, testProject.getSelectedTrack());
    }
}