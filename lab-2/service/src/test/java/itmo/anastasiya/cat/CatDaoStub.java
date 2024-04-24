package itmo.anastasiya.cat;

import itmo.anastasiya.dao.CatDao;
import itmo.anastasiya.entity.Cat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CatDaoStub implements CatDao {

    Map<Long, Cat> catMap = new HashMap<>();
    long counter = 0;

    @Override
    public void save(Cat entity) {
        counter++;
        entity.setId(counter);
        catMap.put(counter, entity);
    }

    @Override
    public void deleteById(long id) {
        catMap.remove(id);
    }

    @Override
    public void deleteByEntity(Cat entity) {
        catMap.remove(entity.getId());
    }

    @Override
    public void update(Cat entity) {
        catMap.put(entity.getId(), entity);
    }

    @Override
    public Cat getById(long id) {
        return catMap.get(id);
    }

    @Override
    public List<Cat> getAll() {
        return catMap.values().stream().toList();
    }
}
