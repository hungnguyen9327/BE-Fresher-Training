package com.example.mongodb_topic4.service.impl;

import com.example.mongodb_topic4.dto.UserDTO;
import com.example.mongodb_topic4.exception.ExceedPageBoundException;
import com.example.mongodb_topic4.exception.NotFoundException;
import com.example.mongodb_topic4.exception.MappingException;
import com.example.mongodb_topic4.model.User;
import com.example.mongodb_topic4.repo.UserRepo;
import com.example.mongodb_topic4.request.SignInReq;
import com.example.mongodb_topic4.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService<UserDTO> {

  private final ModelMapper modelMapper;
  private final MongoTemplate mongoTemplate;
  private final UserRepo repo;
  private final PasswordEncoder encode;

  @Override
  public UserDTO getById(String id) {
//        Query query = new Query();
//        query.addCriteria(Criteria.where("_id").is(id));
//
//        User user = mongoTemplate.findOne(query, User.class);
    Optional<User> optional = repo.findById(id);
    User user = optional.orElseThrow(() -> new NotFoundException("user", id));
    return modelMapper.map(user, UserDTO.class);
  }

  @Override
  public List<UserDTO> getAll() {
//        return mongoTemplate.findAll(UserDTO.class);
    return repo.findAll().stream()
        .map(user -> modelMapper.map(user, UserDTO.class))
        .collect(Collectors.toList());
  }

  @Override
  public Page<UserDTO> getAllByPage(Pageable pageable) {
//        Query query = new Query().with(pageable);
//        List<User> users = mongoTemplate.find(query, User.class);
    return repo.findAll(pageable)
        .map(user -> modelMapper.map(user, UserDTO.class));
  }

  @Override
  public UserDTO create(SignInReq data) {
    try {
      User user = new User();
      user.setUsername(data.getUsername());
      user.setEmail(data.getEmail());
      user.setPassword(data.getPassword());
      user.setStatus("ACTIVE");
      //mongoTemplate.save(user);
      return modelMapper.map(repo.insert(user), UserDTO.class);
    } catch (MappingException e) {
      throw new MappingException("user");
    } catch (NullPointerException e) {
      throw new NullPointerException();
    }
  }

  @Override
  public UserDTO update(String id, UserDTO data) {
    Optional<User> optional = repo.findById(id);
    try {
      User user = optional.orElseThrow(() -> new NotFoundException("user", id));
      user.setUsername(data.getUsername());
      user.setEmail(data.getEmail());
      user.setStatus(data.getStatus());
      //mongoTemplate.save(user);
      return modelMapper.map(repo.save(user), UserDTO.class);
    } catch (MappingException e) {
      throw new MappingException("user");
    } catch (NullPointerException e) {
      throw new NullPointerException();
    }
  }

  @Override
  public void remove(String id) {

    Optional<User> optional = repo.findById(id);
    try {
      User user = optional.orElseThrow(() -> new NotFoundException("user", id));
      user.setStatus("DELETED");
      //mongoTemplate.save(user);
      repo.save(user);
    } catch (NullPointerException e) {
      throw new NullPointerException();
    }
  }

  @Override
  public void delete(String id) {
//        Query query = new Query(Criteria.where("_id").is(id));
//        mongoTemplate.remove(query, User.class);
    repo.deleteById(id);
  }
}
