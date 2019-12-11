package com.example.rabbitmq.demo.rabbit;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitSenderConfig {

    // https://dzone.com/articles/rabbitmq-and-spring-boot-integration-with-fault-to


    @Value("${rabbit.fanout}")
    private String fanout;
    @Value("${rabbit.direct}")
    private String direct;
    @Value("${rabbit.queue1}")
    private String queue1;
    @Value("${rabbit.queue2}")
    private String queue2;
    @Value("${rabbit.queue3}")
    private String queue3;

    // boolean DURABLE -> persistance des queues sur un arrêt / reboot
    @Bean
    Queue queueSimpleTest() {
        return new Queue(queue1, true);
    }

    @Bean
    Queue queueSimpleTest2() {
        return new Queue(queue2, true);
    }

    @Bean
    Queue queueSimpleTest3() {
        return new Queue(queue3, true);
    }



    // -> FANOUT EXCHANGE : envoie le message à toutes les queues bindées à cette stratégie d'échange
    @Bean
    FanoutExchange fanout() {
        return new FanoutExchange(fanout);
    }


    // -> DIRECT EXCHANGE : envoie le message à toutes les queues qui ont la même routing key
    @Bean
    DirectExchange direct() {
        return new DirectExchange(direct);
    }



    // association d'une stratégie d'échange pour chaque queue
    @Bean
    public Declarables bs() {
        return new Declarables(
                new Binding(queue1, Binding.DestinationType.QUEUE, fanout, "k2", null),
                new Binding(queue2, Binding.DestinationType.QUEUE, fanout, "k3", null),
                new Binding(queue3, Binding.DestinationType.QUEUE, direct, "k4", null));
    }


    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

}
