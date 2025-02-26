package org.alham.alhamfirst.service.orchestrator;

import org.alham.alhamfirst.document.stat.StatDocument;
import org.alham.alhamfirst.document.stat.UserStatDocument;
import org.alham.alhamfirst.dto.quest.QuestDTO;
import org.alham.alhamfirst.dto.todo.TodoDTO;

import java.util.List;

public interface OrchestratorTodoService {

    public List<QuestDTO> getUnDoQuestListByEncryptedUserId(String encryptedId);
    public void getTodoById(Long todoId);

    public StatDocument createTodo(TodoDTO todoDTO, String encryptedId);

    public UserStatDocument completeTodo(TodoDTO todoDTO);

    public UserStatDocument unCompleteTodo(TodoDTO todoDTO);

    public void deleteTodoById(String todoId);


}
