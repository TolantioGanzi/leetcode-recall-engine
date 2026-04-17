package org.example.Parser;

import org.example.Action.AddAction;
import org.example.Service.UpdateService;

import java.util.Scanner;

public class AddParser implements Parser{
    private final UpdateService updateService;
    public AddParser(UpdateService updateService) {
        this.updateService = updateService;
    };

    public AddAction parse() {

        Scanner read = new Scanner(System.in);
        System.out.print("Enter Problem Number: ");
        int problemNumber = Integer.parseInt(read.nextLine());
        System.out.print("Enter Result (e.g, PASS | FAIL ) ");
        String result = read.nextLine();

        return new AddAction(problemNumber, result, updateService);
    }
}
