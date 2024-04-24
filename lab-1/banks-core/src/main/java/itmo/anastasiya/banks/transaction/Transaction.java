package itmo.anastasiya.banks.transaction;

import itmo.anastasiya.banks.exception.BankException;

/**
 * transaction interface, contains methods to execute and undo transaction
 *
 * @author Anastasiya
 */
public interface Transaction {
    /**
     * execute transaction
     *
     * @throws BankException if something went wrong
     */
    void execute();

    /**
     * undo transaction
     *
     * @throws BankException if something went wrong
     */
    void undo();

}
