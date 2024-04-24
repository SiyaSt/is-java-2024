package itmo.anastasiya.banks.bank;

import itmo.anastasiya.banks.account.Account;
import itmo.anastasiya.banks.centralbank.CentralBank;
import itmo.anastasiya.banks.centralbank.CentralBankImpl;
import itmo.anastasiya.banks.client.ClientImpl;
import itmo.anastasiya.banks.timemachine.TimeMachine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class BankImplTest {
    LocalDate date1;
    LocalDate date2;
    CentralBank centralBank;
    Bank bank;
    Account account;
    TimeMachine timeMachine;
    Account sender;
    Account recipient;
    Bank bank2;

    @BeforeEach
    public void init() {
        date1 = LocalDate.of(2070, 3, 13);
        date2 = LocalDate.of(2070, 4, 1);
        centralBank = new CentralBankImpl();
        bank = centralBank.createBank("nn", 10, 10, 10, 10, date1);
        account = bank.createDebitAccount(new ClientImpl("nn", "nn", "nn", 000), 100);
        timeMachine = new TimeMachine();
        sender = bank.createDebitAccount(new ClientImpl("nn", "nn", "nn", 000), 100);
        recipient = bank.createDebitAccount(new ClientImpl("nn", "nn", "nn", 000), 100);
        bank2 = centralBank.createBank("nn", 10, 10, 10, 10, date1);
    }

    @Test
    public void cancelAdd() {
        bank.add(account.getId(), 100);
        Assertions.assertEquals(200, account.getBalance());
        bank.cancel(account.getId());
        Assertions.assertEquals(100, account.getBalance());
    }

    @Test
    public void cancelWithdraw() {
        bank.withdraw(account.getId(), 100);
        Assertions.assertEquals(0, account.getBalance());
        bank.cancel(account.getId());
        Assertions.assertEquals(100, account.getBalance());
    }

    @Test
    public void cancelRemittanceInSameBank() {
        bank.remittance(sender.getId(), recipient.getId(), 100);
        Assertions.assertEquals(0, sender.getBalance());
        Assertions.assertEquals(200, recipient.getBalance());
        bank.cancel(sender.getId());
        Assertions.assertEquals(100, sender.getBalance());
        Assertions.assertEquals(100, recipient.getBalance());
    }

    @Test
    public void cancelRemittanceInDifferentBanks() {
        bank.remittance(sender.getId(), recipient.getId(), 100);
        Assertions.assertEquals(0, sender.getBalance());
        Assertions.assertEquals(200, recipient.getBalance());
        bank.cancel(sender.getId());
        Assertions.assertEquals(100, sender.getBalance());
        Assertions.assertEquals(100, recipient.getBalance());
    }

}