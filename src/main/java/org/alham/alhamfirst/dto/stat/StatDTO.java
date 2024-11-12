package org.alham.alhamfirst.dto.stat;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class StatDTO {
    private String id;
    private int todoIdx;
    private Map<String, Integer> statData = new HashMap<>();
}
