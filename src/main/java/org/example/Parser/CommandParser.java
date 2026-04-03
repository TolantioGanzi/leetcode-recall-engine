package org.example.Parser;

import org.example.Action.Action;
import org.example.Service.UpdateService;

import java.util.Scanner;

// Read / Clean / Validate User input
public class CommandParser {
    private final UpdateService updateService;

    public CommandParser(UpdateService updateService) {
        this.updateService = updateService;
    }
    public void parseCommand(String message) {
        System.out.println(message);
        Scanner read = new Scanner(System.in);
        while (true) {
            System.out.print("leetcode-cli>> ");
            String command = read.nextLine();
            switch (command.toLowerCase()) {
                case "add":
                    AddParser addParser = new AddParser(updateService);
                    Action addAction = addParser.parse(command);
                    addAction.execute();
                    System.out.println("Problem added.");
                    break;
                case "due":
                    updateService.fetchDueService();
                    break;
                case "exit":
                    System.out.print("CLI closed");
                    System.exit(1);
                default:
                    System.out.println("Invalid command");
                    break;
            }
        }
    }
}
