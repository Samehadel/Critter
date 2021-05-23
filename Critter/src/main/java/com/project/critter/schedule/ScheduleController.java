package com.udacity.jdnd.course3.critter.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;


    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDto) {
        Schedule schedule = scheduleService.save(scheduleDto);

        return ScheduleControllerHelper.convertScheduleEntity(schedule);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> schedules = scheduleService.findAllSchedules();

        return ScheduleControllerHelper.convertListOfScheduleEntities(schedules);
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<Schedule> schedules = scheduleService.findScheduleForPet(petId);

        return ScheduleControllerHelper.convertListOfScheduleEntities(schedules);
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<Schedule> schedules = scheduleService.findScheduleForEmployee(employeeId);

        return ScheduleControllerHelper.convertListOfScheduleEntities(schedules);
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        Set<Schedule> schedules = scheduleService.findScheduleForCustomer(customerId);

        return ScheduleControllerHelper.convertListOfScheduleEntities(schedules);
    }
}
