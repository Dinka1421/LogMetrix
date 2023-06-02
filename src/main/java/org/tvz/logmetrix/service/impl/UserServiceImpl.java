/*
package org.tvz.logmetrix.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tvz.logmetrix.entity.User;
import org.tvz.logmetrix.repo.UserRepository;
import org.tvz.logmetrix.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;

    @Autowired
    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public boolean deleteUser(Long id) {
        boolean exists = userRepo.existsById(id);

        userRepo.deleteById(id);

        return exists; 
    }

    @Override
    public User addUser(User user) { return userRepo.save(user);}

    @Override
    public User updateUser(User user) {
        return userRepo.save(user);
    }


}
*/
