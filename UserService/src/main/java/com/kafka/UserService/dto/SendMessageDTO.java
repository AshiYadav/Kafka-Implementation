package com.kafka.UserService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendMessageDTO {

    private String to;
    private String from;
    private String subject;
    private String body;

}
