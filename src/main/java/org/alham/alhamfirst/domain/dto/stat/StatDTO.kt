package org.alham.alhamfirst.domain.dto.stat;

data class StatDTO(
        var id: String? = null,
        var todoIdx: Long? = null,
        var statData: MutableMap<String,Double> = mutableMapOf()
) {

}
