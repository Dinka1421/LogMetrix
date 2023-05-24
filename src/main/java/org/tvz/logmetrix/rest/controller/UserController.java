package org.tvz.logmetrix.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tvz.logmetrix.entity.User;
import org.tvz.logmetrix.service.UserService;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService){ this.userService = userService; }

    @GetMapping
    public List<User> getUsers() {
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
    public ResponseEntity<User> addUser(@RequestBody User user) {
        var addedUser = userService.addUser(user);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(addedUser);
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        var updatedUser = userService.updateUser(user);
        return ResponseEntity.status(updatedUser == null ? HttpStatus.NOT_FOUND : HttpStatus.OK)
                .body(updatedUser);
    }
}
