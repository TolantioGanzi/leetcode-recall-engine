package org.example.Model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString


public class Problem {

    private int id;
    public int step;
    private String title;
    private String pattern;
    private String result;
    private String difficulty;
    private String topics;
    public LocalDate dateAdded;
    private LocalDate nextRecall;

    public Problem() {}


    public Problem(int id, String title, String result, String difficulty,
                   String topics, LocalDate nextRecall) {

        this.id = id;
        this.title = title;
        this.result = result;
        this.difficulty = difficulty;
        this.topics = topics;
        this.nextRecall = nextRecall;

        this.step = 0;
        this.dateAdded = LocalDate.now();

    }

    public void addRecallDate(LocalDate date, int currentStep) {
        this.nextRecall = date.plusDays(currentStep);
    }
}
