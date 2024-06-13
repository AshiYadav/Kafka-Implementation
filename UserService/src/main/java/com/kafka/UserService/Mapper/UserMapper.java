package com.kafka.UserService.Mapper;

import com.kafka.UserService.dto.UserDTO;
import com.kafka.UserService.entity.User;

public class UserMapper {

    public static User dtoToEntitiy(UserDTO dto){
        User user = new User();
        user.setUserName(dto.getUserName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        return user;
    }

    public static UserDTO entityToDTO(User user){
        UserDTO dto = new UserDTO();
        dto.setUserName(user.getUserName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());

        return dto;
    }
}
