package org.alham.alhamfirst.service.orchestrator;

import lombok.RequiredArgsConstructor;
import org.alham.alhamfirst.dto.todo.TodoDTO;
import org.alham.alhamfirst.entity.todo.Todo;
import org.alham.alhamfirst.service.orchestrator.stat.StatService;
import org.alham.alhamfirst.service.orchestrator.todo.TodoService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrchestratorTodoServiceImpl implements OrchestratorTodoService {

    private final TodoService todoService;
    private final StatService statService;
    @Override
    public void createTodo(TodoDTO todoDTO) {
        Todo todo = todoService.createTodo(todoDTO);
        statService.calculateStat(todo.getDetail());
    }
}
