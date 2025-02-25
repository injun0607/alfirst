package org.alham.alhamfirst.repository.todo;

import org.alham.alhamfirst.entity.todo.Todo;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    Todo findByUserId(Long userId);
    @EntityGraph(attributePaths = {"user"})
    @Query("select t from Todo t where t.user.id = :userId and t.completed = false")
    List<Todo> findUndoListByUserId(@Param("userId") Long userId);

}

