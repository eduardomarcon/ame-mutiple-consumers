package com.ame.amqp.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    private final RabbitTemplate rabbitTemplate;

    public TodoService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public Todo save(Todo todo) {
        sendMachineToRabbit(todo);
        return todo;
    }

    public void sendMachineToRabbit(Todo todo) {
        try {
            String json = new ObjectMapper().writeValueAsString(todo);
            rabbitTemplate.convertAndSend(TodoAMQPConfig.EXCHANGE_NAME, "routing-key.todos", json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
