package persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderTest {
    String testDirectory = "." + File.separator + "data" + File.separator + "test" + File.separator;
    FileReader fileReader;

    @BeforeEach
    public void setup() {
        fileReader = new FileReader();
    }

    @Test
    public void testReadFile() {
        try{
            String readOutput = fileReader.readFile(testDirectory + "Test Project.json");

            String expectedOutput = "{" +
                    "    \"selectedTrack\": 1," +
                    "    \"trackList\": [" +
                    "        {";

            assertTrue(readOutput.startsWith(expectedOutput));

        } catch (IOException e) {
            fail("Unexpected IOException");
        }
    }

    @Test
    public void testReadFileInvalidFilePath() {
        try{
            fileReader.readFile(testDirectory + "Fake Project.zip");
            fail("Expected IOException");
        } catch (IOException e) {
            // expected behaviour
        }
    }
}