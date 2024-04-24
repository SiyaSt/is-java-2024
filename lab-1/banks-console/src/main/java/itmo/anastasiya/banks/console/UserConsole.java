package itmo.anastasiya.banks.console;

import itmo.anastasiya.banks.account.Account;
import itmo.anastasiya.banks.bank.Bank;
import itmo.anastasiya.banks.centralbank.CentralBank;
import itmo.anastasiya.banks.client.Client;
import itmo.anastasiya.banks.client.ClientBuilder;
import itmo.anastasiya.banks.client.ClientBuilderImpl;
import itmo.anastasiya.banks.exception.BankException;
import picocli.CommandLine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Contains user`s console methods
 *
 * @author Anastasiya
 */
@CommandLine.Command(name = "UserConsole", description = "contains user`s methods")
public class UserConsole implements Callable<Integer> {
    private final ClientBuilder clientBuilder = new ClientBuilderImpl();
    public Client client;

    public Map<String, Client> clientMap = new HashMap<>();
    public CentralBank centralBank;

    public UserConsole(CentralBank centralBank) {
        this.centralBank = centralBank;
    }


    /**
     * set current user
     *
     * @param name to find user
     */
    @CommandLine.Command(name = "logIn", description = "log in")
    public void logIn(@CommandLine.Parameters(index = "0", description = "client`s name") String name) {
        var maybeClient = clientMap.get(name);
        if (maybeClient == null) {
            throw new BankException("client has not registered");
        }
        client = maybeClient;
        CommandUtils.writeMessageBlue("Success");
    }

    /**
     * set null for current user
     */
    @CommandLine.Command(name = "logOut", description = "log out")
    public void logOut() {
        client = null;
        CommandUtils.writeMessageBlue("Success");
    }

    /**
     * register new user
     *
     * @param name       user`s name
     * @param surname    user`s surname"
     * @param address    user`s address
     * @param passportID user`s passportID
     */
    @CommandLine.Command(name = "registration", description = "registers new client")
    public void registration(@CommandLine.Parameters(index = "0", description = "client`s name") String name,
                             @CommandLine.Parameters(index = "1", description = "client`s surname") String surname,
                             @CommandLine.Parameters(index = "2", defaultValue = "-", description = "client`s address") String address,
                             @CommandLine.Parameters(index = "3", defaultValue = "0", description = "client`s passportID") int passportID) {
        if (client != null) {
            throw new BankException("user already registered");
        }
        clientBuilder.addName(name);
        clientBuilder.addAddress(address);
        clientBuilder.addSurname(surname);
        clientBuilder.addPassportID(passportID);
        client = clientBuilder.build();
        clientMap.put(name, client);
        CommandUtils.writeMessageBlue("Success");
    }

    /**
     * change user`s address or passport ID
     *
     * @param address    user`s address
     * @param passportID user`s passportID
     */
    @CommandLine.Command(name = "refactorProfile", description = "change profile`s information")
    public void refactorProfile(@CommandLine.Parameters(index = "0", description = "client`s address") String address,
                                @CommandLine.Parameters(index = "1", description = "client`s passportID") int passportID) {
        clientBuilder.addAddress(address);
        clientBuilder.addPassportID(passportID);
        client = clientBuilder.build();
        List<String> bankNames = client.getBanksNames();
        for (String bankName : bankNames) {
            Bank bank = centralBank.findBank(bankName);
            bank.updateAccountRemittance(client);
        }
        CommandUtils.writeMessageBlue("Success");
    }

    /**
     * subscribe user to receive sightings from bank
     *
     * @param bankName bank name
     */
    @CommandLine.Command(name = "subscribe", description = "user subscribes to receive sightings from bank")
    public void subscribe(@CommandLine.Parameters(index = "0", description = "bank name") String bankName) {

        if (client == null) {
            throw new BankException("client has not log in");
        }
        Bank bank = null;
        bank = centralBank.findBank(bankName);
        bank.clientRegister(client);
        CommandUtils.writeMessageBlue("Success");
    }

    /**
     * create new debit account for current client
     *
     * @param bankName bank name
     * @param amount   start amount of balance
     */
    @CommandLine.Command(name = "createDebitAccount", description = "create new debit account")
    public void createDebitAccount(@CommandLine.Parameters(index = "0", description = "bank name") String bankName,
                                   @CommandLine.Parameters(index = "1", description = "start balance amount") double amount) {
        Bank bank = null;
        if (client == null) {
            throw new BankException("client has not log in");
        }
        client.findBankName(bankName);
        bank = centralBank.findBank(bankName);
        bank.createDebitAccount(client, amount);
        CommandUtils.writeMessageBlue("Success");
    }

    /**
     * create new deposit account for current client
     *
     * @param bankName bank name
     * @param amount   start amount of balance
     */
    @CommandLine.Command(name = "createDepositAccount", description = "create new deposit account")
    public void createDepositAccount(@CommandLine.Parameters(index = "0", description = "bank name") String bankName,
                                     @CommandLine.Parameters(index = "1", description = "start balance amount") double amount) {
        Bank bank = null;
        if (client == null) {
            throw new BankException("client has not log in");
        }
        client.findBankName(bankName);
        bank = centralBank.findBank(bankName);
        bank.createDepositAccount(client, amount);
        CommandUtils.writeMessageBlue("Success");
    }

    /**
     * create new credit account for current client
     *
     * @param bankName bank name
     * @param amount   start amount of balance
     */
    @CommandLine.Command(name = "createCreditAccount", description = "create new credit account")
    public void createCreditAccount(@CommandLine.Parameters(index = "0", description = "bank name") String bankName,
                                    @CommandLine.Parameters(index = "1", description = "start balance amount") double amount) {
        Bank bank = null;
        if (client == null) {
            throw new BankException("client has not log in");
        }
        client.findBankName(bankName);
        bank = centralBank.findBank(bankName);
        bank.createCreditAccount(client, amount);
        CommandUtils.writeMessageBlue("Success");
    }

    /**
     * show type of current user accounts and ID
     */
    @CommandLine.Command(name = "showId", description = "show account`s id")
    public void getAccountsId() {
        if (client == null) {
            throw new BankException("client has not log in");
        }
        var accounts = client.getAccounts();
        accounts.forEach(account -> CommandUtils.writeMessageBlue(account.getClass().getSimpleName() + " " + account.getId()));
    }

    /**
     * show current user account balance
     *
     * @param bankName bank name
     * @param id       account id
     */
    @CommandLine.Command(name = "showBalance", description = "show account balance")
    public void getBalance(@CommandLine.Parameters(index = "0", description = "bank name") String bankName,
                           @CommandLine.Parameters(index = "1", description = "account id") int id) {
        Bank bank = null;
        if (client == null) {
            throw new BankException("client has not log in");
        }

        client.findBankName(bankName);
        bank = centralBank.findBank(bankName);
        Account account = bank.findAccount(id);
        CommandUtils.writeMessageBlue(String.valueOf(account.getBalance()));
    }

    /**
     * show all notifications which sent to user
     */
    @CommandLine.Command(name = "showNotifications", description = "show notifications from bank")
    public void getNotifications() {
        if (client == null) {
            throw new BankException("client has not log in");
        }
        var messages = client.getMessages();
        messages.forEach(CommandUtils::writeMessageBlue);
    }

    /**
     * add amount of money to current user account
     *
     * @param bankName bank name
     * @param id       account id
     * @param amount   amount of money to add
     */
    @CommandLine.Command(name = "add", description = "add money to balance")
    public void addMoney(@CommandLine.Parameters(index = "0", description = "bank name") String bankName,
                         @CommandLine.Parameters(index = "1", description = "account id") int id,
                         @CommandLine.Parameters(index = "2", description = "amount") double amount) {
        Bank bank = null;
        if (client == null) {
            throw new BankException("client has not log in");
        }

        client.findBankName(bankName);
        bank = centralBank.findBank(bankName);
        bank.add(id, amount);
        CommandUtils.writeMessageBlue("Success");
    }

    /**
     * withdraw amount of money to current user account
     *
     * @param bankName bank name
     * @param id       account id
     * @param amount   amount of money to withdraw
     */
    @CommandLine.Command(name = "withdraw", description = "withdraw money from balance")
    public void withdrawMoney(@CommandLine.Parameters(index = "0", description = "bank name") String bankName,
                              @CommandLine.Parameters(index = "1", description = "account id") int id,
                              @CommandLine.Parameters(index = "2", description = "amount") double amount) {
        Bank bank = null;
        if (client == null) {
            throw new BankException("client has not log in");
        }
        client.findBankName(bankName);
        bank = centralBank.findBank(bankName);
        bank.withdraw(id, amount);
        CommandUtils.writeMessageBlue("Success");
    }

    /**
     * remittance amount of money from current user account to another account
     *
     * @param bankName    bank name
     * @param idSender    id sender
     * @param idRecipient id recipient
     * @param amount      amount of money to remittance
     */
    @CommandLine.Command(name = "remittance", description = "remittance money from one account to another")
    public void remittanceMoney(@CommandLine.Parameters(index = "0", description = "bank name") String bankName,
                                @CommandLine.Parameters(index = "1", description = "sender`s id") int idSender,
                                @CommandLine.Parameters(index = "2", description = "recipient`s id") int idRecipient,
                                @CommandLine.Parameters(index = "3", description = "amount") double amount) {
        Bank bank = null;
        if (client == null) {
            throw new BankException("client has not log in");
        }
        client.findBankName(bankName);
        bank = centralBank.findBank(bankName);
        bank.remittance(idSender, idRecipient, amount);
        CommandUtils.writeMessageBlue("Success");
    }

    /**
     * cancel last transaction made current user account
     *
     * @param bankName bank name
     * @param id       account id
     */
    @CommandLine.Command(name = "cancel", description = "cancel last transaction")
    public void cancelTransaction(@CommandLine.Parameters(index = "0", description = "bank name") String bankName,
                                  @CommandLine.Parameters(index = "1", description = "account id") int id) {
        Bank bank = null;
        if (client == null) {
            throw new BankException("client has not log in");
        }
        client.findBankName(bankName);
        bank = centralBank.findBank(bankName);
        bank.cancel(id);
        CommandUtils.writeMessageBlue("Success");
    }

    public Integer call() {
        return 0;
    }
}
