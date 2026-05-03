package user_api.dao;

import user_api.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    User save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByName(String username);
    List<User> findAll();
    void deleteById(Long id);
    void deleteByUsername(String username);
}
