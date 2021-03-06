package SpringBootCRUD.service;

import SpringBootCRUD.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void addUser(User user);

    User getUser(Long id);

    void updateUser(User user);

    void deleteUserById(Long id);

    Optional<User> getUserById(Long id);

    User getByUsername(String username);

    List<User> getAllUsers();

}
