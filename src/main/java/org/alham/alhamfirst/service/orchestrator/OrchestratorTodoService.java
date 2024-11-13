package org.alham.alhamfirst.service.orchestrator;

import org.alham.alhamfirst.document.stat.StatDocument;
import org.alham.alhamfirst.dto.todo.TodoDTO;

public interface OrchestratorTodoService {
    public StatDocument createTodo(TodoDTO todoDTO);
}
