package itmo.anastasiya.banks.account;

import itmo.anastasiya.banks.transaction.Transaction;

import java.time.LocalDate;
import java.util.List;

public abstract class AccountDecorator implements Account {
    public AccountDecorator(Account account) {
        this.account = account;
    }

    protected Account account;

    public void setMaxRemittance(double maxRemittance) {
        account.setMaxRemittance(maxRemittance);
    }

    public int getId() {
        return account.getId();
    }

    public LocalDate getStart() {
        return account.getStart();
    }

    public double getMaxRemittance() {
        return account.getMaxRemittance();
    }

    public double getLimit() {
        return account.getLimit();
    }

    public double getPercentage() {
        return account.getPercentage();
    }

    public void setPercentage(double percentage) {
        account.setPercentage(percentage);
    }

    public void setLimit(double limit) {
        account.setLimit(limit);
    }

    public double getBalance() {
        return account.getBalance();
    }

    public List<Transaction> getTransactions() {
        return account.getTransactions();
    }

    public void addMoney(double amount) {
        account.addMoney(amount);
    }

    public void withDrawMoney(double amount) {
        account.withDrawMoney(amount);
    }

    public void addTransaction(Transaction transaction) {
        account.addTransaction(transaction);
    }

    public void timeChange(LocalDate date) {
        account.timeChange(date);
    }

}
