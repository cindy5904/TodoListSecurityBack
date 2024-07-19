package org.example.todolistsecurity.service;

import org.example.todolistsecurity.model.Todo;
import org.example.todolistsecurity.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService implements ItodoService{
    @Autowired
    private TodoRepository todoRepository;

    @Override
    public Todo saveTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    @Override
    public Optional<Todo> getTodoById(Long id) {
        return todoRepository.findById(id);
    }

    @Override
    public Todo updateTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }
}
