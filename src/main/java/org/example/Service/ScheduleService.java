package org.example.Service;
import lombok.NoArgsConstructor;
import org.example.Model.Problem;

import java.time.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor

public class ScheduleService {

    private final List<Integer> intervals = new ArrayList<>(Arrays.asList(1, 3, 7, 14, 30));


    public void getNextRecall(Problem problem) {
        // New Problems
        LocalDate dateAdded = problem.getDateAdded();
        if(problem.getStep() <= 0) {
            problem.setStep(intervals.getFirst());
            problem.addRecallDate(dateAdded, 1);
        } else {
            // Recall Problems
            int nextStep = intervals.indexOf(problem.getStep()) + 1;
            // if nextStep > 4 (set to Mastered)
            if(nextStep > 4) {
                System.out.println("Problem Mastered ");
                // LOGIC to REMOVE Problem or Master Problem
            } else {
                problem.setStep(intervals.get(nextStep));
                problem.addRecallDate(dateAdded, problem.getStep());
            }
        }
    }
}
