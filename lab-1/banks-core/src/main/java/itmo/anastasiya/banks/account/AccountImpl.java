package itmo.anastasiya.banks.account;

import itmo.anastasiya.banks.transaction.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * account implementation
 *
 * @author Anastasiya
 */
public class AccountImpl implements Account {
    private final int id;
    private double balance;
    private double maxRemittance = -1;
    private double limit;
    private double percentage;
    private final double commission;
    private double summaryPercentage = 0;
    private double summaryCommission = 0;
    private LocalDate start = LocalDate.now();

    private final List<Transaction> transactions = new ArrayList<>();

    public AccountImpl(double balance, int id, double limit, double percentage, double commission) {
        this.balance = balance;
        this.id = id;
        this.limit = limit;
        this.percentage = percentage;
        this.commission = commission;
    }

    public int getId() {
        return id;
    }

    public LocalDate getStart() {
        return start;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public double getBalance() {
        return balance;
    }

    public void setMaxRemittance(double maxRemittance) {
        this.maxRemittance = maxRemittance;
    }

    public double getMaxRemittance() {
        return maxRemittance;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public void addMoney(double amount) {
        balance += amount;
    }

    public void withDrawMoney(double amount) {
        balance -= amount;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void timeChange(LocalDate date) {
        if (commission != 0) {
            if (balance > 0) {
                return;
            }

            while (!date.equals(start)) {
                summaryCommission += commission;
                start = start.plusDays(1);

                if (start.getDayOfMonth() == 1) {
                    balance -= (summaryCommission);
                    summaryCommission = 0;
                }
            }
        } else {
            int year = 365;
            if (start.isLeapYear()) {
                year = 366;
            }

            double summary = (double) Math.round((getPercentage() / year) * 100) / 100;
            while (!date.equals(start)) {
                summaryPercentage += getBalance() * summary / 100;

                start = start.plusDays(1);
                if (start.getDayOfMonth() == 1) {
                    addMoney(summaryPercentage);
                    summaryPercentage = 0;
                }
            }
        }

    }
}
