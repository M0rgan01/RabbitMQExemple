package com.example.listener.demo.rabbit;

import com.example.listener.demo.object.Foo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


@Service
public class Listener {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /////////////// TEST QUEUE 1 ///////////////////

   /* @RabbitListener(queues = "${rabbit.queue1}")
    public void consumeMessage(String message) {
        logger.info("--- Listener 1 ---");
        logger.info("Message : " + message);
    }*/

    @RabbitListener(queues = "${rabbit.queue1}")
    public void consumeMessage1(Message message) {
        logger.info("--- Listener 2 ---");
        logger.info("Message : {}", message);
    }

    /////////////// TEST QUEUE 2 ///////////////////

    @RabbitListener(queues = "${rabbit.queue2}")
    public void consumeMessage2(Message message) {
        logger.info("--- Listener 3 ---");
        logger.info("Message : {}", message);
    }

    /////////////// TEST QUEUE 3 ///////////////////

    @RabbitListener(queues = "${rabbit.queue3}")
    public void consumeMessage3(Foo foo) {
        logger.info("--- Listener 4 ---");
        logger.info("Message : {}", foo.getId());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
