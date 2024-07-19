package org.example.todolistsecurity.repository;

import org.example.todolistsecurity.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
