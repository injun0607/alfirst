package org.alham.alhamfirst.service.orchestrator;

import lombok.RequiredArgsConstructor;
import org.alham.alhamfirst.common.error.*;
import org.alham.alhamfirst.common.logger
import org.alham.alhamfirst.document.stat.StatDocument;
import org.alham.alhamfirst.document.stat.UserStatDocument;
import org.alham.alhamfirst.dto.quest.QuestDTO;
import org.alham.alhamfirst.dto.stat.UserStatDTO;
import org.alham.alhamfirst.dto.todo.TodoDTO;
import org.alham.alhamfirst.mapper.QuestMapper;
import org.alham.alhamfirst.service.orchestrator.ai.AIService;
import org.alham.alhamfirst.service.orchestrator.stat.TodoStatService;
import org.alham.alhamfirst.service.orchestrator.stat.UserStatService;
import org.alham.alhamfirst.service.orchestrator.todo.TodoService;
import org.alham.alhamfirst.util.CommonUtil;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
//@Slf4j
class OrchestratorTodoServiceImpl(
        private val todoService: TodoService,
        private val todoStatService: TodoStatService,
        private val userStatService: UserStatService,
        private val questMapper: QuestMapper,
        private val aiService: AIService
): OrchestratorTodoService {

    private val log = logger()

    /**
     * TODO - 보상처리 방식은 생각해야할 부분 -> Queue를 이용해서 처리하는 방식도 고려해볼만함(비동기)
     * 유저가 할일을 만들때 수행 되는 로직
     */

    /**
     * 완료하지 않은 할일과 stat 들을 가져오는 로직
     *
     * @param encryptedId
     * @return
     */
    override fun getUnDoQuestListByEncryptedUserId(encryptedId: String): List<QuestDTO>{
        try{
            val todoListByUserIdWithUndo = todoService.getTodoListByUserIdWithUndo(CommonUtil.getDecryptedId(encryptedId))
            val idxList = todoListByUserIdWithUndo.mapNotNull(TodoDTO::id)
            val statList = todoStatService.findListInTodoIdxList(idxList)
            return questMapper.createQuestListDTO(todoListByUserIdWithUndo, statList)
        }catch(e: MariaDBCustomError){
            throw OrchestratorCustomError("OrchestratorTodoService MariaDB error")
        }catch(e: MongoCustomError){
            //몽고디비 커스텀에러
            //보상 리워드
            throw OrchestratorCustomError("OrchestratorTodoService MongonDB error");
        }catch(e: AlhamCustomException){
            AlhamCustomErrorLog(e);
            throw OrchestratorCustomError("OrchestratorTodoService getTodoByUserId error");
        }catch(e: Exception){
            throw OrchestratorCustomError("OrchestratorTodoService getTodoByUserId error");
        }
    }

    override fun getTodoById(todoId: Long) {
        TODO("Not yet implemented")
    }

    override fun createTodo(todoDTO: TodoDTO, encryptedId: String): StatDocument{
        try{
            log.info("OrchestratorTodoService createTodo")
            todoDTO.userId = CommonUtil.getDecryptedId(encryptedId)
            val todo = todoService.createTodo(todoDTO)
            val todoId = todo.id?:throw MongoCustomError("TODO ID 없음")

            try{
                log.info("OrchestratorTodoService createTodo")
                val calculateStat = todoStatService.calculateStat(todo.detail)
                return todoStatService.saveStat(todo.id, calculateStat.statData)
            }catch(e: MongoCustomError){
                log.info("OrchestratorTodoService createTodo error")
                todoService.deleteTodoWIthStartReward(todo.id)
                throw OrchestratorCustomError("OrchestratorTodoService createTodo error")
            }
        }catch(e: MongoCustomError){
            AlhamCustomErrorLog(e)
            throw OrchestratorCustomError("OrchestratorTodoService createTodo error")
        }catch (e: AlhamCustomException){
            AlhamCustomErrorLog(e)
            throw OrchestratorCustomError("OrchestratorTodoService createTodo error")
        }
    }


    override fun completeTodo(todoId: Long, encryptedId: String): UserStatDTO{

        val todo = todoService.updateTodoDetail(TodoDTO(id=todoId, completed = true))
        val todoStat = todoStatService.findByTodoIdx(todoId)
        val userStat = userStatService.findByEncryptedId(encryptedId)

        //여기서 stat계산 후 updateStat 해주면 된다.
        return userStatService.updateUserStat(userStat.userIdx, todoStat.statData)
    }

    override fun unCompleteTodo(todoDTO: TodoDTO): UserStatDocument{
        TODO()
    }

    override fun deleteTodoById(todoId: String){
        TODO()
    }

}
