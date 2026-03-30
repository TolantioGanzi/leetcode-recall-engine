package org.example.Repository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
public class FileStore {
    private final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    private final File file = Path.of("data/problems.json").toFile();

    public FileStore(){} // For Jackson JSON


    // Load problems.json from DISK
    /*
    public ProblemRepository loadDatabase() throws IOException {
        try {
            return objectMapper.readValue(file, ProblemRepository.class);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("[LOAD ERROR] Failed to load DB, temporary DB has been loaded: ");
        return new ProblemRepository();
    }
*/
    // Rewrite updated repository to problems.json
    public void saveDatabase(ProblemRepository problemRepo) {
        try {
            objectMapper.writeValue(file, problemRepo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
