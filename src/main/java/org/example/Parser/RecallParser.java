package org.example.Parser;

import org.example.Action.Action;
import org.example.Action.RecallAction;
import org.example.Service.UpdateService;

import java.util.Scanner;

public class RecallParser implements Parser{
    private final UpdateService updateService;

    public RecallParser(UpdateService updateService) {
        this.updateService = updateService;
    }

    @Override
    public Action parse() {
        Scanner read = new Scanner(System.in);
        System.out.print("Enter Recall Problem-ID :");
        int problemID = Integer.parseInt(read.nextLine());
        System.out.print("Result (Pass | Fail | Pass(Non-Op)) :");
        String result = read.nextLine();

        return new RecallAction(updateService, result, problemID);
    }
}
