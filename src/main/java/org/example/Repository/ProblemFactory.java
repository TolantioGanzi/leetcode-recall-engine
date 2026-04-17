package org.example.Repository;

import org.example.Model.LeetcodeProblem;
import org.example.Model.Problem;
import org.example.Service.ScheduleService;

import java.time.LocalDate;

// constructs proper problem object to be added to User Database
public class ProblemFactory {
    private LocalDate nextRecall;

    // Problem Builder
    public Problem userProblemBuilder(String result, LeetcodeProblem leetProblem) {
        int id = leetProblem.getProblem_id();
        String title = leetProblem.getProblem_slug();
        String difficulty = leetProblem.getDifficulty();
        String topics = leetProblem.getTopics();

        ScheduleService scheduleService = new ScheduleService();
        Problem problem = new Problem(id, title, difficulty, topics, nextRecall);
        scheduleService.computeRecall(problem);
        return problem;
    }
}