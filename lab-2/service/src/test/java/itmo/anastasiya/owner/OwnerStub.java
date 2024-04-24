package itmo.anastasiya.owner;

import itmo.anastasiya.dao.OwnerDao;
import itmo.anastasiya.entity.Owner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OwnerStub implements OwnerDao {
    Map<Long, Owner> ownerMap = new HashMap<>();
    long counter = 0;

    @Override
    public void save(Owner entity) {
        counter++;
        entity.setId(counter);
        ownerMap.put(counter, entity);
    }

    @Override
    public void deleteById(long id) {
        ownerMap.remove(id);
    }

    @Override
    public void deleteByEntity(Owner entity) {
        ownerMap.remove(entity.getId());
    }

    @Override
    public void update(Owner entity) {
        ownerMap.put(entity.getId(), entity);
    }

    @Override
    public Owner getById(long id) {
        return ownerMap.get(id);
    }

    @Override
    public List<Owner> getAll() {
        return ownerMap.values().stream().toList();
    }
}
