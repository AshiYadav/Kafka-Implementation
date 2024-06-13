package com.kafka.EmailService.DTO;

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
