package itmo.anastasiya.banks.account;

import itmo.anastasiya.banks.transaction.Transaction;

import java.time.LocalDate;
import java.util.List;

/**
 * main account interface, contains methods of interaction with account balance
 *
 * @author Anastasiya
 */
public interface Account {
    double getBalance();

    int getId();

    LocalDate getStart();

    List<Transaction> getTransactions();

    void setMaxRemittance(double maxRemittance);

    double getMaxRemittance();

    double getLimit();

    double getPercentage();

    void setPercentage(double percentage);

    void setLimit(double limit);

    /**
     * add money to balance
     *
     * @param amount amount of money to withdraw
     */
    void addMoney(double amount);

    /**
     * withdraw money from balance
     *
     * @param amount amount of money to withdraw
     */
    void withDrawMoney(double amount);

    /**
     * save transaction history
     *
     * @param transaction transaction that was made
     */
    void addTransaction(Transaction transaction);

    /**
     * method to change date and accrue interest and commission
     *
     * @param date next date
     */
    void timeChange(LocalDate date);

}
