package itmo.anastasiya.banks.timemachine;

import itmo.anastasiya.banks.centralbank.CentralBank;

import java.time.LocalDate;

/**
 * class to change time
 *
 * @author Anastasiya
 */
public class TimeMachine {
    /**
     * time change machine, notify central bank about new date
     *
     * @param bank central bank
     * @param date new date
     */
    public void timeChange(CentralBank bank, LocalDate date) {
        bank.notify(date);
    }
}
