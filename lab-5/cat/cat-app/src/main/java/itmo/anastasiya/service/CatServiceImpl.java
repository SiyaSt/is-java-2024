package itmo.anastasiya.service;

import itmo.anastasiya.CatDto;
import itmo.anastasiya.entity.Cat;
import itmo.anastasiya.entity.Color;
import itmo.anastasiya.repository.CatRepository;
import itmo.anastasiya.repository.Cat_;
import itmo.anastasiya.repository.CustomCatRepository;
import itmo.anastasiya.repository.Filter;
import itmo.anastasiya.repository.QueryOperator;
import itmo.anastasiya.service.exceptions.CatServiceException;
import itmo.anastasiya.service.map.MappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.springframework.data.jpa.domain.Specification.where;

@Service
@Transactional(readOnly = true)
public class CatServiceImpl implements CatService {
    private final CatRepository catRepository;

    @Autowired
    public CatServiceImpl(CatRepository catRepository) {
        this.catRepository = catRepository;
    }


    @Override
    public CatDto findById(long catId, long ownerId) {
        Cat cat = find(catId);
        checkOwner(cat, ownerId);
        return MappingUtils.mapToCatDto(cat);
    }

    @Override
    public CatDto findByIdAdmin(long catId) {
        Cat cat = find(catId);
        return MappingUtils.mapToCatDto(cat);
    }

    @Override
    public List<CatDto> getCatFriends(long catId, long ownerId) {
        Cat cat = find(catId);
        checkOwner(cat, ownerId);
        List<Long> friends = cat.getFriends().stream().map(Cat::getId).toList();
        return catRepository
                .findAll(
                        where(CustomCatRepository.belongsToCat(friends)
                                .and(CustomCatRepository.ownerIdLike(ownerId))))
                .stream()
                .map(MappingUtils::mapToCatDto)
                .toList();
    }

    @Override
    public List<CatDto> getCatFriendsAdmin(long catId) {
        Cat cat = find(catId);
        return cat.getFriends().stream().map(MappingUtils::mapToCatDto).toList();
    }

    @Override
    public List<CatDto> getAll(long ownerId) {
        return catRepository
                .findAll(CustomCatRepository.ownerIdLike(ownerId))
                .stream()
                .map(MappingUtils::mapToCatDto)
                .toList();
    }

    @Override
    public List<CatDto> getAllAdmin() {
        return catRepository.findAll().stream().map(MappingUtils::mapToCatDto).toList();
    }

    @Override
    public List<CatDto> filter(String breed, Color color, long ownerId) {
        List<Filter> filters = new ArrayList<>();
        filters.add(Filter.builder().field(Cat_.OWNER).operator(QueryOperator.IN).values(List.of(ownerId)).build());

        if (color != null) {
            filters.add(Filter.builder().field(Cat_.COLOR).operator(QueryOperator.LIKE).value(color.name()).build());
        }
        if (breed != null) {
            filters.add(Filter.builder().field(Cat_.BREED).operator(QueryOperator.LIKE).value(breed).build());
        }

        var specification = createSpecifications(filters);
        return catRepository.findAll(specification).stream().map(MappingUtils::mapToCatDto).toList();
    }

    @Override
    public List<CatDto> filterAdmin(String breed, Color color) {
        List<Filter> filters = new ArrayList<>();

        if (color != null) {
            filters.add(Filter.builder().field(Cat_.COLOR).operator(QueryOperator.LIKE).value(color.name()).build());
        }
        if (breed != null) {
            filters.add(Filter.builder().field(Cat_.BREED).operator(QueryOperator.LIKE).value(breed).build());
        }

        var specification = createSpecifications(filters);
        return catRepository.findAll(specification).stream().map(MappingUtils::mapToCatDto).toList();
    }

    private void checkOwner(Cat cat, long id) {
        if (!Objects.equals(id, cat.getOwner())) {
            throw CatServiceException.accessDenied();
        }
    }

    private Cat find(long id) {
        return catRepository
                .findById(id)
                .orElseThrow(() -> CatServiceException.noSuchCat(id));
    }

    private Specification<Cat> createSpecification(Filter input) {
        return switch (input.getOperator()) {
            case LIKE -> (root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get(input.getField()), "%" + input.getValue() + "%");
            case IN -> (root, query, criteriaBuilder) ->
                    criteriaBuilder.in(root.get(input.getField())).value(input.getValues());
        };

    }

    private Specification<Cat> createSpecifications(List<Filter> inputs) {
        Specification<Cat> specification = createSpecification(inputs.getFirst());
        for (int i = 1; i < inputs.size(); i++) {
            var input = inputs.get(i);
            specification = specification.and(createSpecification(input));
        }
        return specification;
    }

}
