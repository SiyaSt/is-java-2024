package itmo.anastasiya.repository;

import itmo.anastasiya.entity.Cat;
import itmo.anastasiya.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {
    List<Cat> findByColor(Color color);

    List<Cat> findByBreed(String breed);

    List<Cat> findByColorAndBreed(Color color, String breed);
}
