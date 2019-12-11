package com.example.rabbitmq.demo;

import com.example.rabbitmq.demo.mail.MailService;
import com.example.rabbitmq.demo.rabbit.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Properties;

@SpringBootApplication
@EnableScheduling
public class RabbitMqSenderApplication implements CommandLineRunner {

    @Autowired
    private MailService mailService;
    @Autowired
    private Sender sender;

    public static void main(String[] args) {
        SpringApplication.run(RabbitMqSenderApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
       mailService.sendSimpleHTMLMessage("pichat.morgan@gmail.com", "test", "<h1><a href=\"#\">Titre lien</a></h1>");

        //sender.sendMessage3();
    }
}
