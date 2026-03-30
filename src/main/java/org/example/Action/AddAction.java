package org.example.Action;

import org.example.Model.Problem;
import org.example.Service.UpdateService;

public class AddAction implements Action{
    private UpdateService updateService;
    private int problemNumber;
    private String result;

    public AddAction(int problemNumber, String result, UpdateService updateService) {
        this.problemNumber = problemNumber;
        this.result = result;
        this.updateService = updateService;
    }

    @Override
    public void execute() {
        updateService.addProblem(problemNumber, result);
    }

}
