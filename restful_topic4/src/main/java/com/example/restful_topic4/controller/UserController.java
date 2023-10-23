package com.example.restful_topic4.controller;

import com.example.restful_topic4.dto.UserDTO;
import com.example.restful_topic4.enums.UserStatus;
import com.example.restful_topic4.request.SignUpReq;
import com.example.restful_topic4.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService<UserDTO> userService;

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        try {
            return new ResponseEntity<List<UserDTO>>(userService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") UUID id) {
        try {
            return new ResponseEntity<UserDTO>(userService.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> newUser(@RequestBody UserDTO user) throws URISyntaxException {
        UserDTO result = userService.addUser(user);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signup(@RequestBody SignUpReq user) throws URISyntaxException {
        UserDTO result = userService.signup(user);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> replaceUser(
            @PathVariable("id") UUID id,
            @RequestBody UserDTO user
    ) throws URISyntaxException {
        UserDTO result = userService.replaceUser(id, user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<?> updateUserStatus(
            @PathVariable("id") UUID id,
            @RequestBody UserStatus stt
    ) throws URISyntaxException {
        UserDTO tempUser = new UserDTO();
        tempUser.setStatus(stt);
        UserDTO result = userService.replaceUser(id, tempUser);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") UUID id) throws URISyntaxException {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //note........

}
