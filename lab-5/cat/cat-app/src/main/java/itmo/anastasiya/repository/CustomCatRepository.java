package itmo.anastasiya.repository;

import itmo.anastasiya.entity.Cat;
import itmo.anastasiya.entity.Color;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class CustomCatRepository {

    public static Specification<Cat> breedLike(String breed) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Cat_.BREED), "%" + breed + "%");
    }

    public static Specification<Cat> colorLike(Color color) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Cat_.COLOR), "%" + color + "%");
    }

    public static Specification<Cat> ownerIdLike(Long ownerId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.in(root.get(Cat_.OWNER)).value(ownerId);
    }

    public static Specification<Cat> belongsToCat(List<Long> id) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.in(root.get(Cat_.ID)).value(id);
    }
}
