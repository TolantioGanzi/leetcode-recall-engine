package org.example.Model;

import lombok.Getter;
import lombok.Setter;

//@Getter
//@Setter

public class LeetcodeProblem {
    private int problem_id;
    private String problem_slug;
    private String difficulty;
    private String topics;

    public LeetcodeProblem(int problem_id, String problem_slug, String difficulty, String topics) {
        this.problem_id = problem_id;
        this.problem_slug = problem_slug;
        this.difficulty = difficulty;
        this.topics = topics;
    }

    public int getProblem_id() {
        return problem_id;
    }

    public void setProblem_id(int problem_id) {
        this.problem_id = problem_id;
    }

    public String getProblem_slug() {
        return problem_slug;
    }

    public void setProblem_slug(String problem_slug) {
        this.problem_slug = problem_slug;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getTopics() {
        return topics;
    }

    public void setTopics(String topics) {
        this.topics = topics;
    }
}
