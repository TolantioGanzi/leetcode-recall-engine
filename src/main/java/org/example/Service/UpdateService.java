package org.example.Service;

import org.example.Model.LeetcodeProblem;
import org.example.Model.Problem;
import org.example.Repository.ProblemFactory;
import org.example.Repository.ProblemRepository;

import java.util.ArrayList;
import java.util.List;

public class UpdateService {
    private ProblemRepository problemRepo;
    private ProblemFactory factory;
    public UpdateService(ProblemRepository problemRepo, ProblemFactory factory) {
        this.problemRepo = problemRepo;
        this.factory = factory;
    }
    public void addProblem(int problemNumber, String result) {
        LeetcodeProblem leetProblem = problemRepo.getProblem(problemNumber);
        Problem problem = factory.userProblemBuilder(result, leetProblem);
        problemRepo.addProblem(problem);
    }

    public void displayTodaysProblems() {
        List<String> today = new ArrayList<>();
    }
    public void removeProblem() {

    }
    public void updateProblem() {

    }
    public void recallProblem() {

    }
}
