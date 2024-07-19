package org.example.todolistsecurity.service;

import org.example.todolistsecurity.model.Todo;

import java.util.List;
import java.util.Optional;

public interface ItodoService {
    public Todo saveTodo(Todo todo);
    public List<Todo> getAllTodos();
    public Optional<Todo> getTodoById(Long id);
    public Todo updateTodo(Todo todo);
    public void deleteTodo(Long id);
}
