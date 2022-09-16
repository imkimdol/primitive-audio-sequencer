package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class TrackTest {
    Track testTrack;
    Track testTrackLength1;

    @BeforeEach
    public void setUp() {
        testTrack = new Track("Test Track", "beep.wav", 64);
        testTrackLength1 = new Track("Test Track with Length 1", "kick.wav", 1);
    }

    @Test
    public void testConstructor() {
        assertEquals("Test Track", testTrack.getName());
        assertEquals("beep.wav", testTrack.getFileName());
        assertEquals("./data" + File.separator + "beep.wav", testTrack.getSoundFile().getPath());
        assertEquals(64, testTrack.getLength());

        for (int i = 0; i < testTrack.getLength(); i++) {
            assertFalse(testTrack.getNote(i));
        }

        assertTrue(testTrack.getEnabled());
    }

    @Test
    public void testConstructorLength1() {
        assertEquals("Test Track with Length 1", testTrackLength1.getName());
        assertEquals("kick.wav", testTrackLength1.getFileName());
        assertEquals("./data" + File.separator + "kick.wav", testTrackLength1.getSoundFile().getPath());
        assertEquals(1, testTrackLength1.getLength());

        for (int i = 0; i < testTrackLength1.getLength(); i++) {
            assertFalse(testTrackLength1.getNote(i));
        }

        assertTrue(testTrackLength1.getEnabled());
    }

    @Test
    public void testExtendTrack() {
        testTrack.extendTrack(64);

        assertEquals(64 + 64, testTrack.getLength());

        for (int i = 0; i < testTrack.getLength(); i++) {
            assertFalse(testTrack.getNote(i));
        }
    }

    @Test
    public void testExtendTrack1() {
        testTrack.extendTrack(1);

        assertEquals(64 + 1, testTrack.getLength());

        for (int i = 0; i < testTrack.getLength(); i++) {
            assertFalse(testTrack.getNote(i));
        }
    }

    @Test
    public void testToggleNoteStart() {
        testTrack.toggleNote(0);
        assertTrue(testTrack.getNote(0));

        testTrack.toggleNote(0);
        assertFalse(testTrack.getNote(0));
    }

    @Test
    public void testToggleNoteMiddle() {
        int position = testTrack.getLength() / 2;

        testTrack.toggleNote(position);
        assertTrue(testTrack.getNote(position));

        testTrack.toggleNote(position);
        assertFalse(testTrack.getNote(position));
    }

    @Test
    public void testToggleNoteEnd() {
        int position = testTrack.getLength() - 1;

        testTrack.toggleNote(position);
        assertTrue(testTrack.getNote(position));

        testTrack.toggleNote(position);
        assertFalse(testTrack.getNote(position));
    }
}