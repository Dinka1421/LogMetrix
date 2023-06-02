package org.tvz.logmetrix.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tvz.logmetrix.dto.UserDTO;
import org.tvz.logmetrix.service.UserService;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService){ this.userService = userService; }

    @GetMapping
    public List<UserDTO> getUsers() {
        return userService.getUsers();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        boolean isDeleted = userService.deleteUser(id);

        return isDeleted ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
       
    }

    @PostMapping
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO) {
        userService.addUser(userDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        userService.updateUser(userDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}
