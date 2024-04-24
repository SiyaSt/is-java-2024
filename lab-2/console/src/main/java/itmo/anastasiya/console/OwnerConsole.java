package itmo.anastasiya.console;

import itmo.anastasiya.owner.OwnerService;
import picocli.CommandLine;

import java.util.Date;

/**
 * contains console owner methods
 *
 * @author Anastasiya
 */
@CommandLine.Command(name = "OwnerConsole", description = "contains main owner console methods ")
public class OwnerConsole {
    private final OwnerService ownerService = ServiceProvider.ownerService;

    /**
     * save new owner
     * @param name owner`s name
     * @param date owner`s birthday
     */
    @CommandLine.Command(name = "save", description = "save new owner")
    public void save(@CommandLine.Parameters(index = "0", description = "Owner name") String name,
                     @CommandLine.Parameters(index = "1", description = "Owner birthday") Date date) {
        ownerService.save(date, name);
        CommandUtils.writeMessageBlue("Success");
    }

    /**
     * delete owner
     * @param id owner`s id
     */
    @CommandLine.Command(name = "delete", description = "save new owner")
    public void delete(@CommandLine.Parameters(index = "0", description = "owner id") long id) {
        ownerService.delete(id);
        CommandUtils.writeMessageBlue("Success");
    }

    /**
     * show owner`s cats
     * @param id owner`s id
     */
    @CommandLine.Command(name = "showCats", description = "show all owner cats")
    public void showCats(@CommandLine.Parameters(index = "0", description = "owner id") long id) {
        var cats = ownerService.getAllOwnerCats(id);
        for (var cat : cats) {
            System.out.printf("Id: %d Name: %s%n", cat.getId(), cat.getName());
        }
        CommandUtils.writeMessageBlue("Success");
    }

    /**
     * show all owners
     */
    @CommandLine.Command(name = "showAll", description = "show all owners")
    public void showAll() {
        var owners = ownerService.getAll();
        for (var owner : owners) {
            System.out.printf("Id: %d Name: %s%n", owner.getId(), owner.getName());
        }
        CommandUtils.writeMessageBlue("Success");
    }

}
