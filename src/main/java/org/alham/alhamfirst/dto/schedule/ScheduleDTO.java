package org.alham.alhamfirst.dto.schedule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.alham.alhamfirst.entity.Schedule;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ScheduleDTO {

    private long id;
    private String title;
    private String scheduleDesc;
    private List<TimeSlotDTO> timeSlotList = new ArrayList<>();

    public ScheduleDTO(Schedule schedule) {
        this.id =  schedule.getId();
        this.title = schedule.getTitle();
        this.scheduleDesc = schedule.getScheduleDesc();
    }

    public void addTimeSlot(TimeSlotDTO timeSlotDTO){
        timeSlotList.add(timeSlotDTO);
    }



}
