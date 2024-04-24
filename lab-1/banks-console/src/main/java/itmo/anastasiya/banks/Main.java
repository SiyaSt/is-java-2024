package itmo.anastasiya.banks;

import itmo.anastasiya.banks.console.LogInConsole;
import picocli.CommandLine;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        LogInConsole logInConsole = new LogInConsole();

        CommandLine.IExecutionExceptionHandler errorHandler = (ex, commandLine, parseResult) -> {
            commandLine.getErr().println(ex.getMessage());
            return commandLine.getCommandSpec().exitCodeOnExecutionException();
        };

        /*
        createBank pupa 10 10 10 10 2024-08-03
        createBank zalupa 10 10 10 10 2024-08-03
        registration nnn nnn nnn 100
        subscribe pupa
        createDebitAccount pupa 100
        registration ooo ooo ooo 100
        subscribe zalupa
        createDebitAccount zalupa 100
         */
        while (true) {
            System.out.println("1. centralBank\n2. bank\n3. user\n");
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            String[] arguments = input.split(" ");
            CommandLine commandLine = new CommandLine(logInConsole);
            commandLine.setExecutionExceptionHandler(errorHandler).execute(arguments);

        }

    }
}