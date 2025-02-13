package org.alham.alhamfirst.dto.schedule;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.alham.alhamfirst.entity.Goal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Deprecated
public class GoalDTO {

    private Long id;
    private String description;
    private Boolean completed = false;


    public GoalDTO(Goal goal) {
        this.id = goal.getId();
        this.description = goal.getDescription();
        this.completed = goal.getCompleted();
    }
}
