package itmo.anastasiya.banks.bank;

import itmo.anastasiya.banks.account.Account;
import itmo.anastasiya.banks.client.Client;
import itmo.anastasiya.banks.exception.BankException;

import java.time.LocalDate;
import java.util.List;

/**
 * main bank interface, contains methods of interaction with bank accounts
 *
 * @author Anastasiya
 */
public interface Bank {
    String getName();

    void setLimit(double limit);

    void setPercentage(double percentage);

    List<Account> getAccounts();

    /**
     * subscribe clients on bank notification
     *
     * @param client bank client
     */
    void clientRegister(Client client);

    /**
     * create credit account in that bank
     *
     * @param client       bank client
     * @param startBalance start balance amount
     * @return account
     */
    Account createCreditAccount(Client client, double startBalance);

    /**
     * create deposit account in that bank
     *
     * @param client       bank client
     * @param startBalance start balance amount
     * @return account
     */
    Account createDepositAccount(Client client, double startBalance);

    /**
     * create debit account in that bank
     *
     * @param client       bank client
     * @param startBalance start balance amount
     * @return account
     */
    Account createDebitAccount(Client client, double startBalance);

    /**
     * add money to specific account
     *
     * @param accountId account id
     * @param amount    amount of money
     * @throws BankException if account did not find
     */
    void add(int accountId, double amount);

    /**
     * withdraw money from specific account
     *
     * @param accountId account id
     * @param amount    amount of money
     * @throws BankException if account did not find
     */
    void withdraw(int accountId, double amount);

    /**
     * remittance from one specific account to another
     *
     * @param senderId    sender id
     * @param recipientId recipient id
     * @param amount      amount of money
     * @throws BankException if sender account did not find
     */
    void remittance(int senderId, int recipientId, double amount);

    /**
     * cancel last transaction of specific account
     *
     * @param accountId account id
     * @throws BankException if account did not find
     */
    void cancel(int accountId);

    /**
     * notify account about time changes
     *
     * @param date date
     */
    void timeChangeNotify(LocalDate date);

    /**
     * find specific account by id in bank list
     *
     * @param id account id
     * @return account if account was found and null if account did not find
     */
    Account findAccount(int id);

    void updateAccountRemittance(Client client);
}
