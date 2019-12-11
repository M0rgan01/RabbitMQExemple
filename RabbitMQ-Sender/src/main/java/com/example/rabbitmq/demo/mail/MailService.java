package com.example.rabbitmq.demo.mail;

import org.springframework.mail.SimpleMailMessage;

public interface MailService {
    void sendSimpleMessage(String to,
                           String subject,
                           String text);
    void sendSimpleHTMLMessage(String to,
                           String subject,
                           String text);
    void sendSimpleMessageUsingTemplate(String to,
                                        String subject,
                                        SimpleMailMessage template,
                                        String ...templateArgs);
    void sendMessageWithAttachment(String to,
                                   String subject,
                                   String text,
                                   String pathToAttachment);
}
