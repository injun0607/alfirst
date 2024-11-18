package org.alham.alhamfirst.repository.todo;

import org.alham.alhamfirst.entity.todo.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    Todo findByUserId(Long userId);
}

