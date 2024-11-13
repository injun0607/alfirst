package org.alham.alhamfirst.service.orchestrator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alham.alhamfirst.common.error.MongoCustomError;
import org.alham.alhamfirst.common.error.OrchestratorCustomError;
import org.alham.alhamfirst.document.stat.StatDocument;
import org.alham.alhamfirst.dto.stat.StatDTO;
import org.alham.alhamfirst.dto.todo.TodoDTO;
import org.alham.alhamfirst.entity.todo.Todo;
import org.alham.alhamfirst.service.orchestrator.stat.StatService;
import org.alham.alhamfirst.service.orchestrator.todo.TodoService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrchestratorTodoServiceImpl implements OrchestratorTodoService {

    /*
     * TODO - 보상처리 방식은 생각해야할 부분 -> Queue를 이용해서 처리하는 방식도 고려해볼만함(비동기)
     */
    private final TodoService todoService;
    private final StatService statService;
    @Override
    public StatDocument createTodo(TodoDTO todoDTO) {
        try {
            /*
            생길수 있는 에러는 1. 투두 생성시 에러, 2. 스탯 생성시 에러
             */
            log.info("OrchestratorTodoService createTodo");
            Todo todo = todoService.createTodo(todoDTO);
            try{
                log.info("OrchestratorTodoService createTodo statService.calculateStat");
                StatDTO statDTO = statService.calculateStat(todo.getDetail());
                return statService.saveStat(todo.getId(), statDTO.getStatData());
            }catch (MongoCustomError e) {
                log.info("OrchestratorTodoService createTodo error");
                todoService.deleteTodoWithStatReward(todo.getId());
                throw new OrchestratorCustomError("OrchestratorTodoService createTodo error");
            }
        }catch (Exception e){
            throw new OrchestratorCustomError("OrchestratorTodoService createTodo error");
        }

    }
}
