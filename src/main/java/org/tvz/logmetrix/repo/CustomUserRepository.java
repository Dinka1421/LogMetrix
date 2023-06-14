package org.tvz.logmetrix.repo;

import org.tvz.logmetrix.entity.User;

import java.util.List;
import java.util.Optional;

public interface CustomUserRepository {
    List<User> getUsers();
    boolean deleteUser(Long id);
    User addUser(User user);
    User updateUser(User user);
    Optional<User> findOneByUsername(String username);
}
