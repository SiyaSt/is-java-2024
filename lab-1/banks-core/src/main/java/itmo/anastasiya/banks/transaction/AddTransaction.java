package itmo.anastasiya.banks.transaction;

import itmo.anastasiya.banks.account.Account;
import itmo.anastasiya.banks.exception.BankException;

/**
 * add transaction implementation
 *
 * @author Anastasiya
 */
public class AddTransaction implements Transaction {
    private final Account account;
    private final double amount;

    public AddTransaction(Account account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    public void execute() {
        account.addMoney(amount);
    }

    public void undo() {
        if (account.getTransactions().isEmpty()) {
            throw new BankException("No transaction in history");
        }
        if (!account.getTransactions().remove(this)) {
            throw new BankException("Transaction was canceled");
        }
        account.withDrawMoney(amount);
    }
}
