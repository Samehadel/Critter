package com.project.critter.schedule;

import com.project.critter.pet.Pet;
import com.project.critter.user.entities.Employee;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ScheduleControllerHelper {

    public static Schedule convertScheduleDto(ScheduleDTO scheduleDto){
        Schedule schedule = new Schedule();

        BeanUtils.copyProperties(scheduleDto, schedule);

        /*if(schedule.getId() < 1)
            schedule.setId(null);*/

        /*
        for(Long employeeId: scheduleDto.getEmployeeIds())
            schedule.getEmployees().add(new Employee(employeeId));

        for(Long petId: scheduleDto.getPetIds())
            schedule.getPets().add(new Pet(petId));
*/
        return schedule;
    }

    public static ScheduleDTO convertScheduleEntity(Schedule schedule){
        ScheduleDTO scheduleDto = new ScheduleDTO();

        BeanUtils.copyProperties(schedule, scheduleDto);

        for(Employee emp: schedule.getEmployees())
            scheduleDto.getEmployeeIds().add(emp.getId());

        for(Pet pet: schedule.getPets())
            scheduleDto.getPetIds().add(pet.getId());

        return scheduleDto;
    }

    public static List<ScheduleDTO> convertListOfScheduleEntities(List<Schedule> schedules){
        List<ScheduleDTO> scheduleDtos = new ArrayList<>();

        for(Schedule schedule: schedules){
            scheduleDtos.add(convertScheduleEntity(schedule));
        }

        return scheduleDtos;
    }
    public static List<ScheduleDTO> convertListOfScheduleEntities(Set<Schedule> schedules){
        List<ScheduleDTO> scheduleDtos = new ArrayList<>();

        for(Schedule schedule: schedules){
            scheduleDtos.add(convertScheduleEntity(schedule));
        }

        return scheduleDtos;
    }
}
