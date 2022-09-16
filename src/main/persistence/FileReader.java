package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// A class which handles reading files

public class FileReader {
    // Constructor for FileReader
    public FileReader() {
    }

    // EFFECTS: Returns a file with the given file path
    public String readFile(String filePath) throws IOException {
        StringBuilder file = new StringBuilder();

        // Code from JsonReader in JsonSerializationDemo
        // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
        try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
            stream.forEach(file::append);
        }

        return file.toString();
    }
}
