package itmo.anastasiya.banks.centralbank;

import itmo.anastasiya.banks.account.Account;
import itmo.anastasiya.banks.bank.Bank;
import itmo.anastasiya.banks.bank.BankImpl;
import itmo.anastasiya.banks.exception.BankException;
import itmo.anastasiya.banks.transaction.RemittanceTransaction;
import itmo.anastasiya.banks.transaction.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * central bank implementation
 *
 * @author Anastasiya
 */
public class CentralBankImpl implements CentralBank {
    private final List<Bank> banks = new ArrayList<>();
    private int accountIdNumber = 0;


    public Bank createBank(
            String name,
            double limit,
            double maxRemittance,
            double percentage,
            double commission,
            LocalDate term) {
        var bank = new BankImpl(name, limit, maxRemittance, percentage, commission, term, this);
        banks.add(bank);
        return bank;
    }

    public int getAccountIdNumber() {
        accountIdNumber++;
        return accountIdNumber;
    }


    public void sendMoney(double amount, int recipientId, Account sender) {
        Account recipient = null;
        for (Bank bank : banks) {
            recipient = bank.findAccount(recipientId);
            if (recipient != null) {
                Transaction transaction = new RemittanceTransaction(sender, recipient, amount);
                transaction.execute();
                sender.addTransaction(transaction);
                recipient.addTransaction(transaction);
                return;
            }
        }
        throw new BankException("no such account");
    }

    public void notify(LocalDate date) {
        banks.forEach(bank -> bank.timeChangeNotify(date));
    }


    public Bank findBank(String name) {
        Bank result = null;
        for (Bank bank : banks) {
            if (bank.getName().equals(name)) {
                result = bank;
                return result;
            }
        }
        throw new BankException("no such bank");
    }
}
