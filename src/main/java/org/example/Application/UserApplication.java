package org.example.Application;
import org.example.Parser.CommandParser;
import org.example.Repository.Database;
import org.example.Repository.ProblemFactory;
import org.example.Repository.ProblemRepository;
import org.example.Service.ScheduleService;
import org.example.Service.UpdateService;

import javax.sql.DataSource;
import java.util.Scanner;


public class UserApplication {

    public UserApplication() {}
    public static void main(String[] args) {
        // Get configured H2 datasource
        DataSource dataSource = Database.getDataSource();

        // inject dependencies
        ProblemFactory factory = new ProblemFactory();
        ProblemRepository problemRepo = new ProblemRepository(dataSource);
        ScheduleService scheduleService = new ScheduleService();
        UpdateService updateService = new UpdateService(scheduleService, factory, problemRepo);
        Scanner read = new Scanner(System.in);
        CommandParser mainParser = new CommandParser(updateService);

        // Initiate Tool
        final String message = "Leetcode Spaced Repetition Tool v.0.0";
        mainParser.parseCommand(message);

    }
}
