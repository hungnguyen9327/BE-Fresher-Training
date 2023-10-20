package com.example.mongodb_topic4.controller;

import com.example.mongodb_topic4.dto.UserDTO;
import com.example.mongodb_topic4.model.SortField;
import com.example.mongodb_topic4.request.SignInReq;
import com.example.mongodb_topic4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/user")
public class UserController {
    UserService<UserDTO> userService;

    public UserController(UserService<UserDTO> userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return new ResponseEntity<List<UserDTO>>(userService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/all/paging_sorting")
    public Page<UserDTO> findAllByPage(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "2") int sizePerPage,
                                       @RequestParam(defaultValue = "ID") SortField sortField,
                                       @RequestParam(defaultValue = "DESC") Sort.Direction sortDirection) {
        Pageable pageable = PageRequest.of(page, sizePerPage, sortDirection, sortField.getDatabaseFieldName());
        return userService.findAllByPage(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") String id) {
        return new ResponseEntity<UserDTO>(userService.getById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody SignInReq data) throws URISyntaxException {
        return new ResponseEntity<UserDTO>(userService.create(data), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> updateUser(
            @PathVariable("id") String id,
            @RequestBody UserDTO data) throws URISyntaxException {
        return new ResponseEntity<UserDTO>(userService.update(id, data), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") String id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
