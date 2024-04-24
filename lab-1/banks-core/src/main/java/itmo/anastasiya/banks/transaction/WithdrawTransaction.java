package itmo.anastasiya.banks.transaction;

import itmo.anastasiya.banks.account.Account;
import itmo.anastasiya.banks.exception.BankException;

/**
 * withdraw transaction implementation
 *
 * @author Anastasiya
 */
public class WithdrawTransaction implements Transaction {
    private final Account account;
    private final double amount;

    public WithdrawTransaction(Account account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    public void execute() {
        account.withDrawMoney(amount);
    }

    public void undo() {
        if ((long) account.getTransactions().size() == 0) {
            throw new BankException("No operation in history");
        }
        if (!account.getTransactions().remove(this)) {
            throw new BankException("Transaction was canceled");
        }

        account.addMoney(amount);
    }
}
