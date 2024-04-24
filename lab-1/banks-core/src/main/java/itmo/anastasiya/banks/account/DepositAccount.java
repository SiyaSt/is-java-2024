package itmo.anastasiya.banks.account;

import itmo.anastasiya.banks.exception.BankException;


import java.time.LocalDate;


public class DepositAccount extends AccountDecorator {

    private final LocalDate end;

    public DepositAccount(Account account, LocalDate end) {
        super(account);
        this.end = end;
    }

    @Override
    public void withDrawMoney(double amount) {

        if (end.isAfter(account.getStart())) {
            throw new BankException("Card term not expired: term-" + end);
        }
        if (account.getBalance() - amount < 0) {
            throw new BankException("Not enough money: balance-" + account.getBalance());
        }

        account.withDrawMoney(amount);
    }
}
