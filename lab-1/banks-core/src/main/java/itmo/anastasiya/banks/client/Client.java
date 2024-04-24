package itmo.anastasiya.banks.client;

import itmo.anastasiya.banks.account.Account;
import itmo.anastasiya.banks.exception.BankException;

import java.util.List;

/**
 * main client interface, contains client`s method
 *
 * @author Anastasiya
 */
public interface Client {
    List<String> getMessages();

    void setName(String name);

    List<String> getBanksNames();

    List<Account> getAccounts();

    /**
     * add account to list
     *
     * @param account client`s new account
     */
    void addAccount(Account account);

    /**
     * find bank name to which the customer subscribes
     *
     * @param name bank name
     * @return string bank name
     * @throws BankException if bank did not find
     */
    String findBankName(String name);

    /**
     * set messages to list, when bank change limit
     *
     * @param name  bank name
     * @param limit amount of limit
     */
    void limitUpdate(String name, double limit);

    /**
     * set messages to list, when bank change percentage
     *
     * @param name       bank name
     * @param percentage amount of percentage
     */
    void percentageUpdate(String name, double percentage);

    /**
     * check client profile, if they registered correctly
     *
     * @return true if not correct and false if correct
     */
    boolean checkClient();

    /**
     * director to organized clint builder steps
     *
     * @param clientBuilder client builder
     * @return client builder
     */
    ClientBuilder direct(ClientBuilder clientBuilder);
}
