package itmo.anastasiya.banks.bank;

import itmo.anastasiya.banks.account.*;
import itmo.anastasiya.banks.centralbank.CentralBank;
import itmo.anastasiya.banks.client.Client;
import itmo.anastasiya.banks.exception.BankException;
import itmo.anastasiya.banks.transaction.AddTransaction;
import itmo.anastasiya.banks.transaction.RemittanceTransaction;
import itmo.anastasiya.banks.transaction.Transaction;
import itmo.anastasiya.banks.transaction.WithdrawTransaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * bank implementation
 *
 * @author Anastasiya
 */
public class BankImpl implements Bank {
    private final String name;
    private final List<Client> clients = new ArrayList<>();
    private final List<Account> accounts = new ArrayList<>();
    private double limit;
    private final double maxRemittance;
    private double percentage;
    private final double commission;
    private final LocalDate term;
    private final CentralBank centralBank;

    public BankImpl(String name, double limit, double maxRemittance, double percentage, double commission, LocalDate term, CentralBank centralBank) {
        this.name = name;
        this.limit = limit;
        this.maxRemittance = maxRemittance;
        this.percentage = percentage;
        this.commission = commission;
        this.term = term;
        this.centralBank = centralBank;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public String getName() {
        return name;
    }

    private void limitChangesNotify() {
        clients.forEach(client -> client.limitUpdate(name, limit));
        accounts.forEach(account -> account.setLimit(limit));
    }

    private void percentageChangesNotify() {
        clients.forEach(client -> client.percentageUpdate(name, percentage));
        accounts.forEach(account -> account.setPercentage(percentage));
    }

    public void setLimit(double limit) {
        this.limit = limit;
        limitChangesNotify();
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
        percentageChangesNotify();
    }

    public void clientRegister(Client client) {
        clients.add(client);
        client.setName(name);
    }

    public Account createCreditAccount(Client client, double startBalance) {
        Account account = new CreditAccount(new AccountImpl(startBalance, centralBank.getAccountIdNumber(), limit, percentage, commission));

        if (client.checkClient()) {
            account.setMaxRemittance(maxRemittance);
        }
        accounts.add(account);
        client.addAccount(account);
        return account;
    }


    public Account createDepositAccount(Client client, double startBalance) {
        Account account = new DepositAccount(new AccountImpl(startBalance, centralBank.getAccountIdNumber(), limit, percentage, 0), term);

        if (client.checkClient()) {
            account.setMaxRemittance(maxRemittance);
        }
        accounts.add(account);
        client.addAccount(account);
        return account;
    }


    public Account createDebitAccount(Client client, double startBalance) {
        Account account = new DebitAccount(new AccountImpl(startBalance, centralBank.getAccountIdNumber(), limit, percentage, 0));

        if (client.checkClient()) {
            account.setMaxRemittance(maxRemittance);
        }
        accounts.add(account);
        client.addAccount(account);
        return account;
    }


    public void add(int accountId, double amount) {
        Account account = findAccount(accountId);
        if (account == null) {
            throw new BankException("no such account in bank" + name);
        }
        Transaction transaction = new AddTransaction(account, amount);
        transaction.execute();
        account.addTransaction(transaction);
    }


    public void withdraw(int accountId, double amount) {
        Account account = findAccount(accountId);
        if (account == null) {
            throw new BankException("no such account in bank " + name);
        }
        Transaction transaction = new WithdrawTransaction(account, amount);
        transaction.execute();
        account.addTransaction(transaction);
    }


    public void remittance(int senderId, int recipientId, double amount) {
        Account sender = findAccount(senderId);
        Account recipient = findAccount(recipientId);
        if (sender == null) {
            throw new BankException("no such account in bank " + name);
        }
        if (recipient != null) {
            Transaction transaction = new RemittanceTransaction(sender, recipient, amount);
            transaction.execute();
            sender.addTransaction(transaction);
            recipient.addTransaction(transaction);
        } else {
            centralBank.sendMoney(amount, recipientId, sender);
        }

    }


    public void cancel(int accountId) {
        Account account = findAccount(accountId);
        if (account == null) {
            throw new BankException("no such account in bank " + name);
        }
        var transactions = account.getTransactions();
        transactions.get(transactions.size() - 1).undo();
    }


    public void timeChangeNotify(LocalDate date) {
        accounts.forEach(account -> account.timeChange(date));
    }


    public Account findAccount(int id) {
        Account result = null;
        for (Account account : accounts) {
            if (account.getId() == id) {
                result = account;
            }
        }
        return result;
    }

    /**
     * update max remittance in accounts, when client change profile
     *
     * @param client client
     */
    public void updateAccountRemittance(Client client) {
        if (client.checkClient()) throw new BankException("Profile is not correct");
        List<Account> accountList = client.getAccounts();
        accountList.forEach(account -> account.setMaxRemittance(-1));
    }
}
