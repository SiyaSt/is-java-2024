package itmo.anastasiya.console;

import itmo.anastasiya.cat.CatService;
import itmo.anastasiya.entity.Color;
import itmo.anastasiya.owner.OwnerService;
import picocli.CommandLine;

import java.util.Date;

/**
 * contains console cat methods
 *
 * @author Anastasiya
 */

//rename controller to condole
@CommandLine.Command(name = "CatConsole", description = "contains main cat methods")
public class CatConsole {
    private final CatService catService = ServiceProvider.catService;
    private final OwnerService ownerService = ServiceProvider.ownerService;

    /**
     * save new cat
     * @param name cat`s name
     * @param date cat`s birthday
     * @param breed cat`s breed
     * @param color cat`s color
     * @param id cat`s owner id
     */
    @CommandLine.Command(name = "save", description = "save new cat")
    public void save(@CommandLine.Parameters(index = "0", description = "Cat name") String name,
                     @CommandLine.Parameters(index = "1", description = "Cat birthday") Date date,
                     @CommandLine.Parameters(index = "2", description = "Car breed") String breed,
                     @CommandLine.Parameters(index = "3", description = "Enum values: ${COMPLETION-CANDIDATES}") Color color,
                     @CommandLine.Parameters(index = "4", description = "owner id") long id) {
        var owner = ownerService.findByIdDto(id);
        catService.save(name, date, breed, color, owner);
        CommandUtils.writeMessageBlue("Success");
    }

    /**
     * delete cat with id
     * @param id cat`s id
     */
    @CommandLine.Command(name = "delete", description = "delete cat")
    public void delete(@CommandLine.Parameters(index = "0", description = "cat id") long id) {
        catService.delete(id);
        CommandUtils.writeMessageBlue("Success");
    }

    /**
     * add cat new friends
     * @param firstId first cat id
     * @param secondId second cat id
     */
    @CommandLine.Command(name = "addFriend", description = "add new cat friend")
    public void addFriend(@CommandLine.Parameters(index = "0", description = "first cat id") long firstId,
                          @CommandLine.Parameters(index = "1", description = "second cat id") long secondId) {
        catService.addCatFriend(firstId, secondId);
        CommandUtils.writeMessageBlue("Success");
    }

    /**
     * show cat owner`s id and name
     * @param id cat`s id
     */
    @CommandLine.Command(name = "showOwner", description = "show cat`s owner")
    public void showOwner(@CommandLine.Parameters(index = "0", description = "cat id") long id) {
        var owner = catService.getCatOwner(id);
        System.out.printf("Id: %d Name: %s%n", owner.getId(), owner.getName());
    }

    /**
     * show cat`s friends
     * @param id cat`s id
     */
    @CommandLine.Command(name = "showFriends", description = "show cat`s friends")
    public void showFriends(@CommandLine.Parameters(index = "0", description = "cat id") long id) {
        var friends = catService.getCatFriends(id);
        for (var friend : friends) {
            System.out.printf("Id: %d Name: %s%n", friend.getId(), friend.getName());
        }
        CommandUtils.writeMessageBlue("Success");
    }

    /**
     * show all cats
     */
    @CommandLine.Command(name = "showAll", description = "show all cats")
    public void showAll() {
        var cats = catService.getAll();
        for (var cat : cats) {
            var owner = cat.getOwner();
            System.out.printf("Id: %d Name: %s Owner: %d %s%n", cat.getId(), cat.getName(), owner.getId(), owner.getName());
        }
        CommandUtils.writeMessageBlue("Success");
    }


}
