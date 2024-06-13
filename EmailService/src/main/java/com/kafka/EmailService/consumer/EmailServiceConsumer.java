package com.kafka.EmailService.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.EmailService.DTO.SendMessageDTO;
import com.kafka.EmailService.Utility.EmailUtility;
import lombok.Setter;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

@Service
public class EmailServiceConsumer {

    private ObjectMapper objectMapper;
    private EmailUtility emailUtility;

    public EmailServiceConsumer(ObjectMapper objectMapper, EmailUtility emailUtility) {
        this.objectMapper = objectMapper;
        this.emailUtility = emailUtility;
    }

    @KafkaListener(topics = "sendEmail" , groupId = "EmailService")
    public void handleEmailEvent(String message){
        try {
            SendMessageDTO dto = objectMapper.readValue(message, SendMessageDTO.class);
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
            props.put("mail.smtp.port", "587"); //TLS Port
            props.put("mail.smtp.auth", "true"); //enable authentication
            props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

            //create Authenticator object to pass in Session.getInstance argument
            Authenticator auth = new Authenticator() {
                //override the getPasswordAuthentication method
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("ashish.y.9@gmail.com", "");
                }
            };
            Session session = Session.getInstance(props, auth);
            emailUtility.sendEmail(session,dto.getTo(), dto.getSubject(), dto.getBody());
        }
        catch (Exception e){
            System.out.println("Error while consuming events : " + e);
        }

    }
}
