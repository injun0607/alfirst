package org.alham.alhamfirst.repository.todo;

import org.alham.alhamfirst.entity.TodoList;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface TodoListRepository extends JpaRepository<TodoList, Long>{

    @EntityGraph(attributePaths = {"user","todoList"})
    @Query("SELECT tl FROM TodoList tl WHERE tl.user.id = :userId")
    public Optional<TodoList> findByUserIdWithTodo(@Param("userId")long userId);

    @EntityGraph(attributePaths = {"user","todoList"})
    @Query("SELECT tl FROM TodoList tl WHERE tl.user.id = :userId AND tl.date = :date")
    public Optional<TodoList> findByUserIdAndDateWithTodoList(@Param("userId") long userId, @Param("date") LocalDate date);



}
