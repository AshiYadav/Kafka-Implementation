package com.kafka.UserService.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.UserService.Mapper.UserMapper;
import com.kafka.UserService.dto.SendMessageDTO;
import com.kafka.UserService.dto.UserDTO;
import com.kafka.UserService.entity.User;
import com.kafka.UserService.producer.KafkaProducerClient;
import com.kafka.UserService.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    private UserRepo userRepo;
    private KafkaProducerClient kafkaProducerClient;
    private ObjectMapper objectMapper;

    public UserService(UserRepo userRepo, KafkaProducerClient kafkaProducerClient, ObjectMapper objectMapper) {
        this.userRepo = userRepo;
        this.kafkaProducerClient = kafkaProducerClient;
        this.objectMapper = objectMapper;
    }

    public UserDTO signup(UserDTO userDTO) throws JsonProcessingException {
        User user = UserMapper.dtoToEntitiy(userDTO);
        User savedUser = userRepo.save(user);

        //Implementing Kafka producer
        try {
            SendMessageDTO dto = new SendMessageDTO();
            dto.setTo(savedUser.getEmail());
            dto.setFrom("ashish.y124@yahoo.com");
            dto.setSubject("Welcome to Kafka");
            dto.setBody("Thanks for signup, We will study Kafka ");

            kafkaProducerClient.sendMessage("sendEmail", objectMapper.writeValueAsString(dto));
        }
        catch(Exception e){
            System.out.println("Error while sending data in kafka"+ e);
        }

        return UserMapper.entityToDTO(savedUser);
    }
}
