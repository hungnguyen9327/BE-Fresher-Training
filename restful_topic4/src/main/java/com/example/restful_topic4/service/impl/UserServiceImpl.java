package com.example.restful_topic4.service.impl;

import com.example.restful_topic4.dto.UserDTO;
import com.example.restful_topic4.enums.UserStatus;
import com.example.restful_topic4.exception.UserNotFoundException;
import com.example.restful_topic4.mapper.UserMapper;
import com.example.restful_topic4.model.User;
import com.example.restful_topic4.repo.UserRepo;
import com.example.restful_topic4.request.SignUpReq;
import com.example.restful_topic4.service.interfaces.UserService;
import org.hibernate.annotations.CurrentTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService<UserDTO> {

    @Autowired
    private UserRepo repo;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public List<UserDTO> getAll() {
        return repo.findAll().stream()
                .map(UserMapper.MAPPER::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getById(UUID id) {
        Optional<User> optional = repo.findById(id);
        User user = optional.orElseThrow(() -> new UserNotFoundException(id));
        return UserMapper.MAPPER.mapToDTO(user);
    }

    @Override
    public UserDTO signup(SignUpReq user) {
        User newUser = new User();
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setEmail(user.getEmail());
        newUser.setUsername(user.getUsername());
        newUser.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        newUser.setStatus(UserStatus.ACTIVED);

        return UserMapper.MAPPER.mapToDTO(repo.save(newUser));
    }

    @Override
    public UserDTO replaceUser(UUID id, UserDTO user) {
        Optional<User> optional = repo.findById(id);
        User currentUser = optional.orElseThrow(() -> new UserNotFoundException(id));
        currentUser.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        if (user.getUsername() == null) {
            currentUser.setStatus(user.getStatus());
        } else {
            currentUser.setUsername(user.getUsername());
            currentUser.setEmail(user.getEmail());
            currentUser.setStatus(user.getStatus());
        }

        return UserMapper.MAPPER.mapToDTO(repo.save(currentUser));
    }

    @Override
    public UserDTO addUser(UserDTO user) {
        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        User savedUser = UserMapper.MAPPER.mapToEntity(user);
        return UserMapper.MAPPER.mapToDTO(repo.save(savedUser));
    }

    @Override
    public UserDTO updateStatus(UUID id, UserStatus status) {
        Optional<User> optional = repo.findById(id);
        User user = optional.orElseThrow(() -> new UserNotFoundException(id));
        user.setStatus(status);
        return UserMapper.MAPPER.mapToDTO(repo.save(user));
    }

    @Override
    public void delete(UUID id) {
        repo.deleteById(id);
    }
}
