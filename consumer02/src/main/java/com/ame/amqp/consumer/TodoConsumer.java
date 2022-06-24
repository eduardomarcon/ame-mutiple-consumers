package com.ame.amqp.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TodoConsumer {

    private final TodoService service;

    public TodoConsumer(TodoService service) {
        this.service = service;
    }

    @RabbitListener(queues = "todos")
    public void consumer(Message message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        String tipo = new ObjectMapper().readTree(message.getBody()).get("tipo").asText();
        if ("planck".equalsIgnoreCase(tipo)){
            service.printTipo(tipo);
            channel.basicAck(tag, true);
        } else {
            channel.basicReject(tag, true);
        }
    }
}
