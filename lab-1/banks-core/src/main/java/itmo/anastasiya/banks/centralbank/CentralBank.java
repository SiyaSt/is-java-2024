package itmo.anastasiya.banks.centralbank;

import itmo.anastasiya.banks.account.Account;
import itmo.anastasiya.banks.bank.Bank;
import itmo.anastasiya.banks.exception.BankException;

import java.time.LocalDate;

/**
 * main central bank interface, contains central bank methods
 *
 * @author Anastasiya
 */
public interface CentralBank {
    /**
     * create new account in central bank
     *
     * @param name          bank name
     * @param limit         amount of limit for accounts in this banks
     * @param commission    amount of commission for accounts in this banks
     * @param maxRemittance amount of maxRemittance for suspicious accounts in this banks
     * @param percentage    amount of percentage for accounts in this banks
     * @param term          date when ends deposit accounts term
     * @return created bank
     */
    Bank createBank(String name, double limit, double maxRemittance, double percentage, double commission, LocalDate term);

    int getAccountIdNumber();

    /**
     * send money from one account to another
     *
     * @param amount      amount of money
     * @param recipientId recipient id
     * @param sender sender account
     * @throws BankException if account did not find
     */
    void sendMoney(double amount, int recipientId, Account sender);

    /**
     * find specific bank in list
     *
     * @param name bank name
     * @return bank
     * @throws BankException if bank did not find
     */
    Bank findBank(String name);

    /**
     * notify banks to accrue interests and commission
     *
     * @param date next day
     */
    void notify(LocalDate date);
}
