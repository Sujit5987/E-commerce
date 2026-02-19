package user_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import user_api.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);
    void deleteByName(String name);
    Optional<User> findByEmailAndPassword(String email, String password);

    Optional<User> findByEmail(String email);

}
