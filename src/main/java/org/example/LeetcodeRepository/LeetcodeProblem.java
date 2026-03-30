package org.example.LeetcodeRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

// data from merged.json will be converted into this Problem object
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter

public class LeetcodeProblem {
    private String problem_id;
    private String title;
    private String problem_slug;
    private String difficulty;
    private List<String> topics;
}
