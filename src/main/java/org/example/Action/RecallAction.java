package org.example.Action;

import org.example.Service.UpdateService;

public class RecallAction implements Action{
    private UpdateService updateService;
    private int problemID;
    private String result;

    public RecallAction( UpdateService updateService, String result, int problemID) {
        this.result = result;
        this.updateService = updateService;
        this.problemID = problemID;
    }

    @Override
    public void execute() {
        updateService.recallService(problemID, result);
    }
}
