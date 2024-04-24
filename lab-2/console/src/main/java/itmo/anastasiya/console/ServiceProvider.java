package itmo.anastasiya.console;

import itmo.anastasiya.cat.CatService;
import itmo.anastasiya.cat.CatServiceImpl;
import itmo.anastasiya.dao.HibernateCatDao;
import itmo.anastasiya.dao.HibernateOwnerDao;
import itmo.anastasiya.owner.OwnerService;
import itmo.anastasiya.owner.OwnerServiceImpl;

/**
 * contains service initialization
 */
public class ServiceProvider {
    public static final CatService catService = new CatServiceImpl(new HibernateCatDao());
    public static final OwnerService ownerService = new OwnerServiceImpl(new HibernateOwnerDao(), catService);
}
