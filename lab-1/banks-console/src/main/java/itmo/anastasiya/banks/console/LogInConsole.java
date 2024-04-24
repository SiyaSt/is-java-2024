package itmo.anastasiya.banks.console;

import itmo.anastasiya.banks.bank.Bank;
import picocli.CommandLine;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;

/**
 * Contains console methods to log in how central bank, bank or user
 *
 * @author Anastasiya
 */
@CommandLine.Command(name = "LogInConsole", description = "contains log in method")
public class LogInConsole implements Callable<Integer> {
    public CentralBankConsole centralBankConsole = new CentralBankConsole();
    public BankConsole bankConsole;
    public UserConsole userConsole = new UserConsole(centralBankConsole.centralBank);
    /**
     * print message from threw exceptions
     */
    public CommandLine.IExecutionExceptionHandler errorHandler = (ex, commandLine, parseResult) -> {
        commandLine.getErr().println(ex.getMessage());
        return commandLine.getCommandSpec().exitCodeOnExecutionException();
    };


    /**
     * log in how central bank
     */
    @CommandLine.Command(name = "centralBank", description = "contains central bank console methods")
    public void centralBank() {
        List<String> centralBankCommands = CommandUtils.getCommands(centralBankConsole.getClass());
        while (true) {
            CommandUtils.printCommands(centralBankCommands);
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            String[] arguments = input.split(" ");
            if (input.equals("exit")) {
                break;
            } else {
                CommandLine commandLine = new CommandLine(centralBankConsole);
                commandLine.setExecutionExceptionHandler(errorHandler).execute(arguments);
            }
        }

    }

    /**
     * log in how bank
     *
     * @param name bank name
     */
    @CommandLine.Command(name = "bank", description = "contains bank console methods")
    public void bank(@CommandLine.Parameters(index = "0", description = "bank name") String name) {

        Bank bank = null;
        bank = centralBankConsole.centralBank.findBank(name);
        bankConsole = new BankConsole(bank);
        List<String> bankCommands = CommandUtils.getCommands(centralBankConsole.getClass());
        while (true) {
            CommandUtils.printCommands(bankCommands);
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            String[] arguments = input.split(" ");
            if (input.equals("exit")) {
                break;
            } else {
                CommandLine commandLine = new CommandLine(bankConsole);
                commandLine.setExecutionExceptionHandler(errorHandler).execute(arguments);
            }
        }

    }

    /**
     * log in how user
     */
    @CommandLine.Command(name = "user", description = "contains user console methods")
    public void user() {
        List<String> userCommands = CommandUtils.getCommands(userConsole.getClass());
        while (true) {
            CommandUtils.printCommands(userCommands);
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            String[] arguments = input.split(" ");
            if (input.equals("exit")) {
                break;
            } else {
                CommandLine commandLine = new CommandLine(userConsole);
                commandLine.setExecutionExceptionHandler(errorHandler).execute(arguments);
            }
        }

    }

    public Integer call() {
        return 0;
    }
}
