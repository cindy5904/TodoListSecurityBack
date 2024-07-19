package org.example.todolistsecurity.controller;

import org.example.todolistsecurity.model.Todo;
import org.example.todolistsecurity.model.User;
import org.example.todolistsecurity.service.TodoService;
import org.example.todolistsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;
    @Autowired
    private UserService userService;

    @PostMapping("/admin/post/{id}")
    public ResponseEntity<Todo> createTodo(@PathVariable long id,
                                           @RequestBody Todo todo) {
        try {

            User user = userService.findUserById(id);
            if (user != null) {
                todo.setUser(user);
            } else {
                return ResponseEntity.notFound().build();
            }

            Todo savedTodo = todoService.saveTodo(todo);
            return ResponseEntity.ok(savedTodo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos() {
        return ResponseEntity.ok(todoService.getAllTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
        Optional<Todo> todo = todoService.getTodoById(id);
        return todo.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/admin/update")
    public ResponseEntity<Todo> updateTodo(@RequestBody Todo todo) {
        return ResponseEntity.ok(todoService.updateTodo(todo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.ok().build();
    }
}


