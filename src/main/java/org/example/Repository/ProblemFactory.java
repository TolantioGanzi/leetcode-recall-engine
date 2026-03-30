package org.example.Repository;

import org.example.Model.LeetcodeProblem;
import org.example.Model.Problem;
import org.example.Service.ScheduleService;

import java.util.ArrayList;
import java.util.List;

// constructs proper problem object to be added to User Database
public class ProblemFactory {
    private List<String> recallDates = new ArrayList<>();

    // Problem Builder
    public Problem userProblemBuilder(String result, LeetcodeProblem leetProblem) {
        int id = leetProblem.getProblem_id();
        String title = leetProblem.getProblem_slug();
        String difficulty = leetProblem.getDifficulty();
        String topics = leetProblem.getTopics();

        ScheduleService scheduleService = new ScheduleService();
        Problem problem = new Problem(id, title, result, difficulty, topics, recallDates);
        scheduleService.computeDates(problem);
        return problem;
    }
}