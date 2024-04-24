package itmo.anastasiya.console;

import picocli.CommandLine;

import java.util.List;
import java.util.Scanner;

/**
 * contains console methods to generate owner and cat commands
 *
 * @author Anastasiya
 */
@CommandLine.Command(name = "Console", description = "contains methods to generate owner and cat commands")
public class Console {
    private final CatConsole catConsole = new CatConsole();
    private final OwnerConsole ownerConsole = new OwnerConsole();
    /**
     * print message from threw exceptions
     */
    public CommandLine.IExecutionExceptionHandler errorHandler = (ex, commandLine, parseResult) -> {
        commandLine.getErr().println(ex.getMessage());
        return commandLine.getCommandSpec().exitCodeOnExecutionException();
    };

    /**
     * cat commands
     */
    @CommandLine.Command(name = "cat", description = "generate cat`s methods")
    public void cat() {
        List<String> catCommands = CommandUtils.getCommands(catConsole.getClass());
        while (true) {
            CommandUtils.printCommands(catCommands);
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            String[] arguments = input.split(" ");
            if (input.equals("exit")) {
                break;
            } else {
                CommandLine commandLine = new CommandLine(catConsole);
                commandLine.setExecutionExceptionHandler(errorHandler).execute(arguments);
            }
        }
    }

    /**
     * owner commands
     */
    @CommandLine.Command(name = "owner", description = "generate owner`s methods")
    public void owner() {
        List<String> ownerCommands = CommandUtils.getCommands(ownerConsole.getClass());
        while (true) {
            CommandUtils.printCommands(ownerCommands);
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            String[] arguments = input.split(" ");
            if (input.equals("exit")) {
                break;
            } else {
                CommandLine commandLine = new CommandLine(ownerConsole);
                commandLine.setExecutionExceptionHandler(errorHandler).execute(arguments);

            }
        }
    }

}
