package org.alham.alhamfirst.service.stat;


import org.alham.alhamfirst.domain.document.stat.StatDocument;
import org.alham.alhamfirst.domain.dto.stat.StatDTO;


interface TodoStatService {


    fun saveStat(todoIdx: Long, statData: MutableMap<String, Double>): StatDocument

    /**
     * desc를 받아서 stat을 산출하는 서비스
     * @param desc
     */
    fun calculateStat(desc: String): StatDTO
    fun updateStat(stat: String)
    fun findByTodoIdx(todoIdx: Long): StatDTO
    fun findListInTodoIdxList(todoIdxList: List<Long>): List<StatDTO>
}
