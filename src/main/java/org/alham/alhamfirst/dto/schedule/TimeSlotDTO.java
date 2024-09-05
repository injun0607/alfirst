package org.alham.alhamfirst.dto.schedule;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.alham.alhamfirst.common.enums.Category;
import org.alham.alhamfirst.entity.TimeSlot;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class TimeSlotDTO {

    private Long id;
    private String startTime;
    private String endTime;
    private Category category;
    private List<GoalDTO> goalList = new ArrayList<>();

    public void addGoal(GoalDTO goalDTO){
        goalList.add(goalDTO);
    }


    public TimeSlotDTO (TimeSlot timeSlot){
        this.id = timeSlot.getId();
        this.startTime = timeSlot.getStartTime();
        this.endTime = timeSlot.getEndTime();
    }



}
