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


    public void computeDates(Problem problem) {
        LocalDate addedDate = LocalDate.now(); // Format 2026-03-17
        for(Integer interval : intervals) {
            String nextDate = String.valueOf(addedDate.plusDays(interval));
            problem.addRecallDate(nextDate);
        }
    }
}
