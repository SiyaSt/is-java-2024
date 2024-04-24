package itmo.anastasiya.banks.account;

import itmo.anastasiya.banks.exception.BankException;


public class DebitAccount extends AccountDecorator {
    public DebitAccount(Account account) {
        super(account);
    }

    @Override
    public void withDrawMoney(double amount) {
        if (account.getBalance() - amount < 0) {
            throw new BankException("Not enough money: balance-" + account.getBalance());
        }
        account.withDrawMoney(amount);
    }
}
