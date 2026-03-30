package org.example.Model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.*;

//@Getter
//@Setter
//@ToString


public class Problem {

    private int id;
    private String title;
    private String pattern;
    private String result;
    private String difficulty;
    private String topics;
    public int step;
    public String dateAdded;
    private List<String> recallDates;

    public Problem() {}


    public Problem(int id, String title, String result, String difficulty,
                   String topics, List<String> recallDates) {

        this.id = id;
        this.title = title;
        this.result = result;
        this.difficulty = difficulty;
        this.topics = topics;
        this.recallDates = recallDates;

        this.step = 0;
        this.dateAdded = String.valueOf(LocalDate.now());

    }

    public void addRecallDate(String date) {
        this.recallDates.add(date);
    }

    public List<String> getRecallDates() {
        return recallDates;
    }

    public void setRecallDates(List<String> recallDates) {
        this.recallDates = recallDates;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
