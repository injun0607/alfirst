package org.alham.alhamfirst.repository.todo;

import org.alham.alhamfirst.domain.entity.todo.Todo;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
interface TodoRepository : JpaRepository<Todo, Long> {
    fun findByUserId(userId: Long) : Todo?
    @EntityGraph(attributePaths = ["user"])
    @Query("select t from Todo t where t.user.id = :userId and t.completed = false")
    fun findUndoListByUserId(@Param("userId")userId: Long): List<Todo>
    fun findTodoByIdAndUserId(id: Long, userId: Long): Todo?
    fun deleteTodoByIdAndUserId(id: Long, userId: Long)

}

