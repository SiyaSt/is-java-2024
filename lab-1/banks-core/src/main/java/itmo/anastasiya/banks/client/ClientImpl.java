package itmo.anastasiya.banks.client;

import itmo.anastasiya.banks.account.Account;
import itmo.anastasiya.banks.exception.BankException;

import java.util.ArrayList;
import java.util.List;

/**
 * client implementation
 *
 * @author Anastasiya
 */
public class ClientImpl implements Client {
    private final String name;
    private final String surname;
    private final String address;
    private final int passportId;
    private final List<Account> accounts = new ArrayList<>();
    private final List<String> messages = new ArrayList<>();
    private final List<String> banksNames = new ArrayList<>();


    public ClientImpl(String name, String surname, String address, int passportId) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.passportId = passportId;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setName(String name) {
        banksNames.add(name);
    }

    public String findBankName(String name) {
        String result = null;
        for (String bankName : banksNames) {
            if (bankName.equals(name)) {
                result = bankName;
            }
        }
        if (result == null) {
            throw new BankException("no such bank");
        }
        return result;
    }

    public void limitUpdate(String name, double limit) {
        messages.add("Bank - " + name + " change credit limit - " + limit);
    }


    public void percentageUpdate(String name, double percentage) {
        messages.add("Bank - " + name + " change credit percentage - " + percentage);
    }

    public boolean checkClient() {
        return address.equals("-") && passportId == 0;
    }

    public List<String> getBanksNames() {
        return banksNames;
    }

    public ClientBuilder direct(ClientBuilder clientBuilder) {
        clientBuilder.addName(name);
        clientBuilder.addSurname(surname);
        clientBuilder.addAddress(address);
        clientBuilder.addPassportID(passportId);
        return clientBuilder;
    }
}
