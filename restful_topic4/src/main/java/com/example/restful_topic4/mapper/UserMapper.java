package com.example.restful_topic4.mapper;

import com.example.restful_topic4.dto.UserDTO;
import com.example.restful_topic4.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

  UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

  User mapToEntity(UserDTO dto);

  UserDTO mapToDTO(User entity);
}