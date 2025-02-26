package org.alham.alhamfirst.service.orchestrator;

import org.alham.alhamfirst.document.stat.StatDocument
import org.alham.alhamfirst.document.stat.UserStatDocument
import org.alham.alhamfirst.dto.quest.QuestDTO
import org.alham.alhamfirst.dto.stat.UserStatDTO
import org.alham.alhamfirst.dto.todo.TodoDTO


interface OrchestratorTodoService {


    fun getUnDoQuestListByEncryptedUserId(encryptedId: String): List<QuestDTO>
    fun getTodoById(todoId: Long)
    fun createTodo(todoDTO: TodoDTO, encryptedId: String): StatDocument
    fun completeTodo(todoId: Long, encryptedId: String): UserStatDTO
    fun unCompleteTodo(todoDTO: TodoDTO): UserStatDocument
    fun deleteTodoById(todoId: String)
}
