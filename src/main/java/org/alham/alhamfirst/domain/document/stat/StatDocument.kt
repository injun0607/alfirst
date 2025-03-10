package org.alham.alhamfirst.domain.document.stat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@Document(collection = "stat")
@Getter
@Setter
@NoArgsConstructor
class StatDocument(
        @Id
        var id: String? = null,
        var todoIdx: Long = 0,
        var statData: MutableMap<String, Double> = mutableMapOf()
) {

}
