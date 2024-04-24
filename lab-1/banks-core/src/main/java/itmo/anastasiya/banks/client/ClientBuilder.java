package itmo.anastasiya.banks.client;

/**
 * builder to build client profile
 *
 * @author Anastasiya
 */
public interface ClientBuilder {
    /**
     * add client name
     *
     * @param name client name
     * @return client builder
     */
    ClientBuilder addName(String name);

    /**
     * add client surname
     *
     * @param surname client surname
     * @return client builder
     */
    ClientBuilder addSurname(String surname);

    /**
     * add client address
     *
     * @param address client address
     * @return client builder
     */
    ClientBuilder addAddress(String address);

    /**
     * add client passport id
     *
     * @param id client passport id
     * @return client builder
     */
    ClientBuilder addPassportID(int id);

    Client build();
}
