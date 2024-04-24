package itmo.anastasiya;


import itmo.anastasiya.console.Console;
import picocli.CommandLine;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Console console = new Console();
        while (true) {
            System.out.println("1. owner\n2. cat\n");
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            String[] arguments = input.split(" ");
            CommandLine commandLine = new CommandLine(console);
            commandLine.execute(arguments);

        }
    }
}