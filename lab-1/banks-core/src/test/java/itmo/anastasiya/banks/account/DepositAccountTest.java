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

class DepositAccountTest {
    LocalDate date1;
    LocalDate date2;
    CentralBank centralBank;
    Bank bank;
    Account account;
    TimeMachine timeMachine;
    Account sender;
    Account recipient;

    @BeforeEach
    public void init() {
        date1 = LocalDate.of(2070, 3, 13);
        date2 = LocalDate.of(2070, 3, 14);
        centralBank = new CentralBankImpl();
        bank = centralBank.createBank("nn", 10, 10, 10, 10, date1);
        account = bank.createDepositAccount(new ClientImpl("nn", "nn", "nn", 000), 100);
        timeMachine = new TimeMachine();
        sender = bank.createDepositAccount(new ClientImpl("nn", "nn", "nn", 10010), 100);
        recipient = bank.createDepositAccount(new ClientImpl("nn", "nn", "nn", 10010), 100);
    }
    @Test
    public void withdraw() {
        Assertions.assertThrows(BankException.class, () -> account.withDrawMoney(100));
        timeMachine.timeChange(centralBank, date2);
        Assertions.assertDoesNotThrow(() -> account.withDrawMoney(100));

    }

    @Test
    public void remittance() {
        Assertions.assertThrows(BankException.class, () -> bank.remittance(sender.getId(), recipient.getId(), 100));
        timeMachine.timeChange(centralBank, date2);
        Assertions.assertDoesNotThrow(() -> bank.remittance(sender.getId(), recipient.getId(), 100));
    }

    @Test
    public void suspiciousCustomer() {
        sender = bank.createDepositAccount(new ClientImpl("nn", "nn", "-", 0), 100);
        timeMachine.timeChange(centralBank, date2);
        Assertions.assertThrows(BankException.class, () -> bank.remittance(sender.getId(), recipient.getId(), 100));
    }

}