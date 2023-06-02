package org.tvz.logmetrix.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tvz.logmetrix.dto.UserDTO;
import org.tvz.logmetrix.entity.User;
import org.tvz.logmetrix.repo.UserRepository;
import org.tvz.logmetrix.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;

    @Autowired
    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<UserDTO> getUsers() {
        return userRepo.getUsers().stream().map(this::mapUserToDTO).collect(Collectors.toList());
    }

    @Override
    public boolean deleteUser(Long id) {
        return userRepo.deleteUser(id);
    }

    @Override
    public void addUser(UserDTO userDTO) {
        User user = mapDTOToUser(userDTO);
        userRepo.addUser(user);
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        User user = mapDTOToUser(userDTO);
        userRepo.updateUser(user);
    }

    private UserDTO mapUserToDTO(final User user){
        return new UserDTO(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail());
    }

    private User mapDTOToUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        return user;
    }
}
