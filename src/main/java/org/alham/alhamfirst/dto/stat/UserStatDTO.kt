package org.alham.alhamfirst.dto.stat;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
data class UserStatDTO(
        var id: String? = null,
        var userIdx: Long = 0,
        var statData: MutableMap<String,Double> = mutableMapOf()
) {

}
