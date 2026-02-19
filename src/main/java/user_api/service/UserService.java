package user_api.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import user_api.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);
    Optional<User> getUserById(Long id);
    Optional<User> getUserByUsername(String username);
    List<User> getAllUsers();
    User updateUser(Long id, User user);
    void deleteUserById(Long id);
    void deleteUserByUsername(String username);
    Optional<User> login(String name, String password);

    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;
}
