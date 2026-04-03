package org.example.Service;

import org.example.Model.LeetcodeProblem;
import org.example.Model.Problem;
import org.example.Repository.ProblemFactory;
import org.example.Repository.ProblemRepository;

public class UpdateService {
    private ProblemRepository problemRepo;
    private ProblemFactory factory;
    public UpdateService(ProblemRepository problemRepo, ProblemFactory factory) {
        this.problemRepo = problemRepo;
        this.factory = factory;
    }

    public void addService(int problemNumber, String result) {
        LeetcodeProblem leetProblem = problemRepo.getProblem(problemNumber);
        Problem problem = factory.userProblemBuilder(result, leetProblem);
        problemRepo.addProblem(problem);
    }

    public void fetchDueService() {
        problemRepo.fetchDueProblems();
    }
    public void removeProblem() {

    }
    public void updateProblem() {

    }
    public void recallProblem() {

    }
}
