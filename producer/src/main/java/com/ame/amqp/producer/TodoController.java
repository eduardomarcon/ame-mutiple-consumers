package com.ame.amqp.producer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public ResponseEntity<Todo> save(@RequestBody Todo todo) {
        Todo todoSaved = todoService.save(todo);
        System.out.printf("Todo saved: %s%n", todoSaved.toString());
        return ResponseEntity.ok(todoSaved);
    }
}
