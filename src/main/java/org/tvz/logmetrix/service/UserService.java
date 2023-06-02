package org.tvz.logmetrix.service;

import org.springframework.stereotype.Service;
import org.tvz.logmetrix.dto.UserDTO;

import java.util.List;

@Service
public interface UserService {
    List<UserDTO> getUsers();
    boolean deleteUser(Long id);
    void addUser(UserDTO userDTO);
    void updateUser(UserDTO userDTO);
}
