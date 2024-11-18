package org.alham.alhamfirst.service.orchestrator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alham.alhamfirst.common.error.MariaDBCustomError;
import org.alham.alhamfirst.common.error.MongoCustomError;
import org.alham.alhamfirst.common.error.OrchestratorCustomError;
import org.alham.alhamfirst.document.stat.StatDocument;
import org.alham.alhamfirst.document.stat.UserStatDocument;
import org.alham.alhamfirst.dto.stat.StatDTO;
import org.alham.alhamfirst.dto.todo.TodoDTO;
import org.alham.alhamfirst.entity.todo.Todo;
import org.alham.alhamfirst.service.orchestrator.stat.TodoStatService;
import org.alham.alhamfirst.service.orchestrator.todo.TodoService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrchestratorTodoServiceImpl implements OrchestratorTodoService {

    /**
     * TODO - 보상처리 방식은 생각해야할 부분 -> Queue를 이용해서 처리하는 방식도 고려해볼만함(비동기)
     * 유저가 할일을 만들때 수행 되는 로직
     */
    private final TodoService todoService;
    private final TodoStatService todoStatService;

    //TODO - 유저의아이디로 조회시 todo와 같이 stat을 가지고 오는 로직
    @Override
    public void getTodoByUserId(Long userId) {
        try {
            TodoDTO todo = todoService.getTodoDetailByUserId(userId);
            StatDocument stat = todoStatService.findByTodoIdx(todo.getId());
        } catch (MariaDBCustomError e){
            //마리아 디비 커스텀에러
            throw new OrchestratorCustomError("OrchestratorTodoService getTodoByUserId error");
        } catch (MongoCustomError e){
            //몽고디비 커스텀에러
            //보상 리워드

            throw new OrchestratorCustomError("OrchestratorTodoService getTodoByUserId error");
        } catch (Exception e){
            throw new OrchestratorCustomError("OrchestratorTodoService getTodoByUserId error");
        }
    }

    @Override
    public void getTodoById(Long todoId) {

    }

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
                StatDTO statDTO = todoStatService.calculateStat(todo.getDetail());
                return todoStatService.saveStat(todo.getId(), statDTO.getStatData());
            }catch (MongoCustomError e) {
                log.info("OrchestratorTodoService createTodo error");
                todoService.deleteTodoWithStatReward(todo.getId());
                throw new OrchestratorCustomError("OrchestratorTodoService createTodo error");
            }
        }catch (MongoCustomError e){
            throw new OrchestratorCustomError("OrchestratorTodoService createTodo error");
        }

        try {
            /*
            생길수 있는 에러는 1. 투두 생성시 에러, 2. 스탯 생성시 에러
             */
            log.info("OrchestratorTodoService createTodo");
            Todo todo = todoService.createTodo(todoDTO);
            try{
                log.info("OrchestratorTodoService createTodo statService.calculateStat");
                StatDTO statDTO = todoStatService.calculateStat(todo.getDetail());
                return todoStatService.saveStat(todo.getId(), statDTO.getStatData());
            }catch (MongoCustomError e) {
                log.info("OrchestratorTodoService createTodo error");
                todoService.deleteTodoWithStatReward(todo.getId());
                throw new OrchestratorCustomError("OrchestratorTodoService createTodo error");
            }
        }catch (MongoCustomError e){
            throw new OrchestratorCustomError("OrchestratorTodoService createTodo error");
        }

    }

    /**
     * 유저가 할일을 완료했을때 수행되는 로직
     * @param todoDTO
     * @return
     */
    @Override
    public UserStatDocument completeTodo(TodoDTO todoDTO) {
        /*
        수행되어야 하는것
        todoId를 가지고 stat을 가져온다.
        stat을 가지고 유저의 stat을 업데이트한다.
         */
        StatDocument todoStat = todoStatService.findByTodoIdx(todoDTO.getId());
//        todoStatService.getUserStatDocument(todoStat);

        return null;
    }

    /**
     * 유저가 할일을 완료하지 못했을때 수행되는 로직
     * @param todoDTO
     * @return
     */
    @Override
    public UserStatDocument unCompleteTodo(TodoDTO todoDTO) {
        return null;
    }

    @Override
    public void deleteTodoById(String todoId) {

    }
}
