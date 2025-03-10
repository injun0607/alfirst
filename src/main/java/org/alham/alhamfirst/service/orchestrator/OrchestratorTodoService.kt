package org.alham.alhamfirst.service.orchestrator;

import org.alham.alhamfirst.domain.document.stat.StatDocument
import org.alham.alhamfirst.domain.dto.quest.QuestDTO
import org.alham.alhamfirst.domain.dto.stat.UserStatDTO
import org.alham.alhamfirst.domain.dto.todo.TodoDTO


interface OrchestratorTodoService {


    fun getUnDoQuestListByEncryptedUserId(encryptedId: String): List<QuestDTO>
    fun getTodoById(todoId: Long)
    fun createQuest(todoDTO: TodoDTO, encryptedId: String): QuestDTO
    fun completeQuest(todoId: Long, encryptedId: String, completed: Boolean): UserStatDTO
    fun deleteTodoById(todoId: String)

    fun changeQuest(todoDTO: TodoDTO, encryptedId: String): QuestDTO
}
