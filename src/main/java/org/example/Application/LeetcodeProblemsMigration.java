package org.example.Application;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;

public class LeetcodeProblemsMigration {

    private static final String JDBC_URL = "jdbc:h2:tcp://localhost/~/problems_test";
    private static final String JDBC_USER = "sa";
    private static final String JDBC_PASSWORD = "1234";

    public void test(String[] args) {
        String jsonFilePath = "merged_problems.json";

        try {
            ObjectMapper mapper = new ObjectMapper();
            LeetcodeProblemsWrapper wrapper =
                    mapper.readValue(new File(jsonFilePath), LeetcodeProblemsWrapper.class);

            try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
                createTable(conn);
                insertProblems(conn, wrapper.questions);
            }

            System.out.println("Migration completed successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createTable(Connection conn) throws Exception {
        String sql = """
            CREATE TABLE IF NOT EXISTS PROBLEMS (
                PROBLEM_ID INT PRIMARY KEY,
                PROBLEM_SLUG VARCHAR(255),
                DIFFICULTY VARCHAR(50),
                TOPICS VARCHAR(1000)
            )
        """;

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }

    private static void insertProblems(Connection conn, List<LeetcodeProblemJson> problems) throws Exception {
        String sql = """
            MERGE INTO PROBLEMS (PROBLEM_ID, PROBLEM_SLUG, DIFFICULTY, TOPICS)
            KEY (PROBLEM_ID)
            VALUES (?, ?, ?, ?)
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (LeetcodeProblemJson problem : problems) {
                ps.setInt(1, Integer.parseInt(problem.problem_id));
                ps.setString(2, problem.problem_slug);
                ps.setString(3, problem.difficulty);

                String topics = problem.topics == null
                        ? ""
                        : problem.topics.stream().collect(Collectors.joining(", "));

                ps.setString(4, topics);
                ps.addBatch();
            }

            ps.executeBatch();
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    static class LeetcodeProblemsWrapper {
        public List<LeetcodeProblemJson> questions;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    static class LeetcodeProblemJson {
        public String problem_id;
        public String problem_slug;
        public String difficulty;
        public List<String> topics;
    }
}