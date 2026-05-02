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


    public void computeNewRecall(Problem problem) {
        // New Problems
        LocalDate dateAdded = problem.getDateAdded();
        problem.setStep(intervals.getFirst());
        problem.addRecallDate(dateAdded, 1);

    }

    public void computeNextRecall(Problem problem) {
        LocalDate currentDate = LocalDate.now();
        int step =  problem.getStep();
        problem.addRecallDate(currentDate, step);
    }

}
