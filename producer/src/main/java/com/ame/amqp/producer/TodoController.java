package com.ame.amqp.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/todos")
@Slf4j
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public ResponseEntity<Todo> save(@RequestBody Todo todo) {
        Todo todoSaved = todoService.save(todo);
        log.info("Todo saved: {}", todoSaved.toString());
        return ResponseEntity.ok(todoSaved);
    }
}
