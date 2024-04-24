package itmo.anastasiya.banks.console;

import itmo.anastasiya.banks.centralbank.CentralBank;
import itmo.anastasiya.banks.centralbank.CentralBankImpl;
import picocli.CommandLine;

import java.time.LocalDate;
import java.util.concurrent.Callable;

/**
 * Contains central bank`s console methods
 *
 * @author Anastasiya
 */
@CommandLine.Command(name = "CentralBankConsole", description = "contains central bank`s methods")
public class CentralBankConsole implements Callable<Integer> {
    public CentralBank centralBank = new CentralBankImpl();

    /**
     * central bank console method to create bank
     *
     * @param name          bank name which we create
     * @param limit         amount of limit for accounts in this banks
     * @param commission    amount of commission for accounts in this banks
     * @param maxRemittance amount of maxRemittance for suspicious accounts in this banks
     * @param percentage    amount of percentage for accounts in this banks
     * @param term          date when ends deposit accounts term
     */
    @CommandLine.Command(name = "createBank", header = "create bank", description = "Create new bank")
    public void createBank(@CommandLine.Parameters(index = "0", description = "bank name") String name,
                           @CommandLine.Parameters(index = "1", description = "credit limit") double limit,
                           @CommandLine.Parameters(index = "2", description = "maximum remittance") double maxRemittance,
                           @CommandLine.Parameters(index = "3", description = "percentage") double percentage,
                           @CommandLine.Parameters(index = "4", description = "credit commission") double commission,
                           @CommandLine.Parameters(index = "5", description = "term for deposit account") LocalDate term) {
        centralBank.createBank(name, limit, maxRemittance, percentage, commission, term);
        CommandUtils.writeMessageBlue("Success");
    }

    public Integer call() {
        return 0;
    }
}
