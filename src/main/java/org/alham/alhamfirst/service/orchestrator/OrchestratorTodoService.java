package org.alham.alhamfirst.service.orchestrator;

import org.alham.alhamfirst.document.stat.StatDocument;
import org.alham.alhamfirst.document.stat.UserStatDocument;
import org.alham.alhamfirst.dto.todo.TodoDTO;

public interface OrchestratorTodoService {
    public StatDocument createTodo(TodoDTO todoDTO);

    public UserStatDocument completeTodo(TodoDTO todoDTO);

    public UserStatDocument unCompleteTodo(TodoDTO todoDTO);

}
