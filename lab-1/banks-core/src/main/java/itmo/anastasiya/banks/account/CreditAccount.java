package itmo.anastasiya.banks.account;

import itmo.anastasiya.banks.exception.BankException;

public class CreditAccount extends AccountDecorator {
    public CreditAccount(Account account) {
        super(account);
    }

    @Override
    public void withDrawMoney(double amount) {
        if (account.getBalance() - amount < -account.getLimit()) {
            throw new BankException("Exceeding the credit limit: limit-" + account.getLimit());
        }
        account.withDrawMoney(amount);
    }
}
