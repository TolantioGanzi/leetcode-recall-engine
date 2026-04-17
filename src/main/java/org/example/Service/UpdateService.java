package org.example.Service;

import org.example.Model.LeetcodeProblem;
import org.example.Model.Problem;
import org.example.Repository.ProblemFactory;
import org.example.Repository.ProblemRepository;

public class UpdateService {
    private ProblemRepository problemRepo;
    private ProblemFactory factory;
    private ScheduleService scheduleService;

    public UpdateService(ScheduleService scheduleService, ProblemFactory factory, ProblemRepository problemRepo) {
        this.scheduleService = scheduleService;
        this.factory = factory;
        this.problemRepo = problemRepo;
    }

    // Add new problem to Leetcode
    public void addService(int problemNumber, String result) {
        LeetcodeProblem leetProblem = problemRepo.getProblem(problemNumber);
        Problem problem = factory.userProblemBuilder(result, leetProblem);
        problemRepo.addProblem(problem);
    }

    // Fetches list of problems due today
    public void fetchDueService() {
        problemRepo.fetchDueProblems();
    }

    // Recall Problem
    public void recallService(int problemID, String result) {
        Problem problem = problemRepo.fetchRecallProblem(problemID);
        scheduleService.computeRecall(problem);
        // Pass -> compute next recall date
        // Fail -> Mark Problem as HARD (give it weight)

    }

    public void removeProblem() {

    }

    public void updateProblem() {

    }

}
