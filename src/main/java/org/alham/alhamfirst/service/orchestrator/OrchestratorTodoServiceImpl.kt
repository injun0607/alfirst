package org.alham.alhamfirst.service.orchestrator;

import lombok.RequiredArgsConstructor;
import org.alham.alhamfirst.common.exception.*;
import org.alham.alhamfirst.common.logger
import org.alham.alhamfirst.domain.document.stat.StatDocument;
import org.alham.alhamfirst.domain.dto.quest.QuestDTO;
import org.alham.alhamfirst.domain.dto.stat.UserStatDTO;
import org.alham.alhamfirst.domain.dto.todo.TodoDTO;
import org.alham.alhamfirst.mapper.QuestMapper;
import org.alham.alhamfirst.mapper.TodoMapper
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
        TODO()
//        try{
//            val todoListByUserIdWithUndo = todoService.getTodoListByUserIdWithUnCompleted(CommonUtil.getDecryptedId(encryptedId))
//            val idxList = todoListByUserIdWithUndo.mapNotNull(TodoDTO::id)
//            val statList = todoStatService.findListInTodoIdxList(idxList)
//            return QuestMapper().createQuestListDTO(todoListByUserIdWithUndo, statList)
//        }catch(e: MariaDBCustomException){
//            throw OrchestratorCustomException("OrchestratorTodoService MariaDB error")
//        }catch(e: MongoCustomException){
//            //몽고디비 커스텀에러
//            //보상 리워드
//            throw OrchestratorCustomException("OrchestratorTodoService MongonDB error");
//        }catch(e: AlhamCustomException){
//            AlhamCustomErrorLog(exception = e);
//            throw OrchestratorCustomException("OrchestratorTodoService getTodoByUserId error");
//        }catch(e: Exception){
//            throw OrchestratorCustomException("OrchestratorTodoService getTodoByUserId error");
//        }
    }

    override fun getTodoById(todoId: Long) {
        TODO("Not yet implemented")
    }

    override fun createQuest(todoDTO: TodoDTO, encryptedId: String): QuestDTO{
        TODO()
//        try{
//            log.info("OrchestratorTodoService createTodo")
//            todoDTO.userId = CommonUtil.getDecryptedId(encryptedId)
//            val todo = todoService.createTodo(todoDTO)
//            val todoId = todo.id?:throw MongoCustomException("TODO ID 없음")
//
//            try{
//                log.info("OrchestratorTodoService createTodo")
//                val calculateStat = todoStatService.calculateStat(todo.detail)
//                val statDocument = todoStatService.saveStat(todo.id, calculateStat.statData)
//                return QuestDTO(id = todoId, detail = todo.detail, completed = false, statData = statDocument.statData)
//            }catch(e: MongoCustomException){
//                log.info("OrchestratorTodoService createTodo error")
//                todoService.deleteTodoWIthStartReward(todo.id)
//                throw OrchestratorCustomException("OrchestratorTodoService createTodo error")
//            }
//        }catch(e: MongoCustomException){
//            AlhamCustomErrorLog(exception = e)
//            throw OrchestratorCustomException("OrchestratorTodoService createTodo error")
//        }catch (e: AlhamCustomException){
//            AlhamCustomErrorLog(exception = e)
//            throw OrchestratorCustomException("OrchestratorTodoService createTodo error")
//        }
    }


    override fun completeQuest(todoId: Long, encryptedId: String, completed: Boolean): UserStatDTO {

        TODO()
//        val todo = todoService.completeTodo(TodoDTO(id=todoId, completed = completed))
//        val todoStat = todoStatService.findByTodoIdx(todoId)
//        val userStat = userStatService.findByEncryptedId(encryptedId)
//
//        //여기서 stat계산 후 updateStat 해주면 된다.
//        return userStatService.updateUserStat(userStat.userIdx, todoStat.statData, completed)
    }

    override fun deleteTodoById(todoId: String){
        TODO()
    }

    override fun changeQuest(todoDTO: TodoDTO, encryptedId: String): QuestDTO{
        TODO()
//        try{
//            val decryptedId = CommonUtil.getDecryptedId(encryptedId)
//            val todoId = todoDTO.id?:throw OrchestratorCustomException("Todo Id is null")
//            var todo = todoService.getTodoByIdAndUserId(todoId, decryptedId)
//            val changedTodo = TodoMapper().updateTodoFromDTO(todo, todoDTO)
//
//
//            try{
//                log.info("OrchestratorTodoService changeTodo")
//                val calculateStat = todoStatService.calculateStat(todo.detail)
//                val statDocument = todoStatService.saveStat(todoId, calculateStat.statData)
//                return QuestDTO(id = todoId, detail = todo.detail, completed = false, statData = statDocument.statData)
//            }catch(e: MongoCustomException){
//                log.info("OrchestratorTodoService createTodo error")
//                todoService.deleteTodoWIthStartReward(todoId)
//                throw OrchestratorCustomException("OrchestratorTodoService createTodo error")
//            }
//
//
//
//        }catch(e: Exception){
//            AlhamCustomErrorLog(exception = e)
//            throw OrchestratorCustomException("OrchestratorTodoService changeTodo error")
//        }
    }

}
