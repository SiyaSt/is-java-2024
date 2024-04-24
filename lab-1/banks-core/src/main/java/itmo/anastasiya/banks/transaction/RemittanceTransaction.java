package itmo.anastasiya.banks.transaction;

import itmo.anastasiya.banks.account.Account;
import itmo.anastasiya.banks.exception.BankException;

/**
 * remittance transaction implementation
 *
 * @author Anastasiya
 */
public class RemittanceTransaction implements Transaction {
    private final Account sender;
    private final Account recipient;
    private final double amount;

    public RemittanceTransaction(Account sender, Account recipient, double amount) {
        this.amount = amount;
        this.recipient = recipient;
        this.sender = sender;
    }


    public void execute() {
        if (sender.getMaxRemittance() != -1 && sender.getMaxRemittance() < amount) {
            throw new BankException("Maximum transfer limit exceeded: " + sender.getMaxRemittance());
        }
        sender.withDrawMoney(amount);
        recipient.addMoney(amount);
    }

    public void undo() {
        if ((long) sender.getTransactions().size() == 0) {
            throw new BankException("No operation in history");
        }

        if (!sender.getTransactions().remove(this)) {
            throw new BankException("Transaction was canceled");
        }

        if (!recipient.getTransactions().remove(this)) {
            throw new BankException("Transaction was canceled");
        }
        sender.addMoney(amount);
        recipient.withDrawMoney(amount);
    }
}
