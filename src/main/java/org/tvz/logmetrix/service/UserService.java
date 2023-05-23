package org.tvz.logmetrix.service;

import org.tvz.logmetrix.entity.User;

import java.util.Optional;
import java.util.Set;

public interface UserService {
    Set<User> getUsers();
    boolean deleteUser(Long id);
    Optional<User> addUser(User user);
    User updateUser(User user);
}
