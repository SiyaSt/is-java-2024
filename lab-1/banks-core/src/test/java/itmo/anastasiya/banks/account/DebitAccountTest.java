package itmo.anastasiya.banks.account;

import itmo.anastasiya.banks.bank.Bank;
import itmo.anastasiya.banks.centralbank.CentralBank;
import itmo.anastasiya.banks.centralbank.CentralBankImpl;
import itmo.anastasiya.banks.client.ClientImpl;
import itmo.anastasiya.banks.exception.BankException;
import itmo.anastasiya.banks.timemachine.TimeMachine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;


class DebitAccountTest {
    LocalDate date1;
    LocalDate date2;
    CentralBank centralBank;
    Bank bank;
    Account account;
    TimeMachine timeMachine;

    @BeforeEach
    public void init() {
        date1 = LocalDate.of(2070, 3, 13);
        date2 = LocalDate.of(2070, 4, 1);
        centralBank = new CentralBankImpl();
        bank = centralBank.createBank("nn", 10, 10, 10, 10, date1);
        account = bank.createDebitAccount(new ClientImpl("nn", "nn", "nn", 000), 100);
        timeMachine = new TimeMachine();
    }
    @Test
    public void withdraw() {
        Assertions.assertThrows(BankException.class, () -> account.withDrawMoney(120));
    }

    @Test
    public void interest() {
        timeMachine.timeChange(centralBank, date2);
        Assertions.assertTrue(account.getBalance() > 100);
    }

}