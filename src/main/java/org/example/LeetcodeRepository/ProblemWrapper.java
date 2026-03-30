package org.example.LeetcodeRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

// We need this class because JSON is not just a raw list
// It starts with a Wrapper object that begins with questions
// Java needs a class that matches the outer shape
public class ProblemWrapper {
    public List<LeetcodeProblem> questions;
}
