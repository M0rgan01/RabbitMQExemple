package com.example.rabbitmq.demo.rabbit;

import com.example.rabbitmq.demo.object.Foo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class Sender {

    private RabbitTemplate rabbitTemplate;
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Value("${rabbit.queue1}")
    private String queue1;
    @Value("${rabbit.fanout}")
    private String fanout;
    @Value("${rabbit.direct}")
    private String direct;

    public Sender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    ///////////////////////// Send method ///////////////////

    public void sendMessage(String exchange, String routingKey, Object data) {
        logger.info("Sending message to the queue using routingKey {}", routingKey);
        rabbitTemplate.convertAndSend(exchange, routingKey, data);
        logger.info("The message has been sent to the queue.");
    }

    ///////////////////////// TEST ///////////////////

    /*// envoie un message direct à la queue1
    @Scheduled(fixedRate = 5000)
    public void sendMessage1(){
        logger.info("Sending message to the queue using queue {}", "queue1");
        rabbitTemplate.convertAndSend(queue1, "coucou1");
        logger.info("Notification stored in queue sucessfully");
    }

   // envoie un message à toutes les files de l'exchange topic
    @Scheduled(fixedRate = 5000)
    public void sendMessage2(){
        logger.info("Sending message to the queue using exchange {}", fanout);
        rabbitTemplate.convertAndSend(fanout,"","coucou2");
        logger.info("Notification stored in queue sucessfully");
    }

    // envoie des messages a l'exchange 'direct',à toutes les queues concerné par le routage k4
    @Scheduled(fixedRate = 5000)
    public void sendMessage3(){
        for (long index = 0; index < 5; index ++){
            logger.info("Sending message to the queue using exchange {} and routing key {}", direct, "k4");
            Foo foo = new Foo(index, "test");
            rabbitTemplate.convertAndSend(direct, "k4", foo);
            logger.info("Notification stored in queue sucessfully");
        }
    }*/


}
