package itmo.anastasiya.banks.console;

import itmo.anastasiya.banks.bank.Bank;
import picocli.CommandLine;

import java.util.concurrent.Callable;

/**
 * Contains bank`s console methods
 *
 * @author Anastasiya
 */
@CommandLine.Command(name = "BankConsole", description = "contains bank`s methods")
public class BankConsole implements Callable<Integer> {
    public Bank bank;

    public BankConsole(Bank bank) {
        this.bank = bank;
    }

    /**
     * bank`s method to set limit
     *
     * @param limit new limit for accounts
     */
    @CommandLine.Command(name = "setLimit", description = "set new credit limit")
    public void setLimit(@CommandLine.Parameters(index = "0", description = "new credit limit") double limit) {
        bank.setLimit(limit);
        CommandUtils.writeMessageBlue("Success");
    }

    /**
     * bank`s method to set limit
     *
     * @param percentage new percentage for accounts
     */
    @CommandLine.Command(name = "setPercentage", description = "set new credit percentage")
    public void setPercentage(@CommandLine.Parameters(index = "0", description = "new credit percentage") double percentage) {
        bank.setPercentage(percentage);
        CommandUtils.writeMessageBlue("Success");
    }

    public Integer call() {
        return 0;
    }
}
