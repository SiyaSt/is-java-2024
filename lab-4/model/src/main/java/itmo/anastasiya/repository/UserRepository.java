package itmo.anastasiya.repository;

import itmo.anastasiya.entity.Owner;
import itmo.anastasiya.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByOwner(Owner owner);
}
