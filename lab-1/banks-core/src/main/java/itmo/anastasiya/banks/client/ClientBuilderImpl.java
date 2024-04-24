package itmo.anastasiya.banks.client;

import itmo.anastasiya.banks.exception.BankException;

/**
 * client builder implementation
 *
 * @author Anastasiya
 */
public class ClientBuilderImpl implements ClientBuilder {
    private String name;
    private String surname;
    private String address;
    private int passportId;

    public ClientBuilder addName( String name) {
        this.name = name;
        return this;
    }

    public ClientBuilder addSurname(String surname) {
        this.surname = surname;
        return this;
    }


    public ClientBuilder addAddress(String address) {
        this.address = address;
        return this;
    }


    public ClientBuilder addPassportID(int id) {
        this.passportId = id;
        return this;
    }

    public Client build() {
        if(name == null)
        {
            throw new BankException("Name can not be null");
        }
        if(surname == null)
        {
            throw new BankException("Surname can not be null");
        }
        return new ClientImpl(name, surname, address, passportId);
    }
}
