package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetService;
import com.udacity.jdnd.course3.critter.user.entities.Customer;
import com.udacity.jdnd.course3.critter.user.entities.Employee;
import com.udacity.jdnd.course3.critter.user.service.CustomerService;
import com.udacity.jdnd.course3.critter.user.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PetService petService;

    @Autowired
    private CustomerService customerService;


    public Schedule save(ScheduleDTO scheduleDto){
        Schedule schedule = ScheduleControllerHelper.convertScheduleDto(scheduleDto);

        for(Long employeeId: scheduleDto.getEmployeeIds()){
            Employee emp = employeeService.findEmployee(employeeId);
            schedule.getEmployees().add(emp);
        }
        for(Long petId: scheduleDto.getPetIds()){
            Pet pet = petService.getPetById(petId);
            schedule.getPets().add(pet);
        }

        return scheduleRepository.save(schedule);
    }

    public List<Schedule> findAllSchedules(){
        return (List<Schedule>) scheduleRepository.findAll();
    }

    public List<Schedule> findScheduleForPet(Long petId){
        Pet pet = petService.getPetById(petId);

        return scheduleRepository.findByPets(pet);
    }

    public List<Schedule> findScheduleForEmployee(Long employeeId){
        Employee employee = employeeService.findEmployee(employeeId);

        return scheduleRepository.findByEmployees(employee);
    }

    public Set<Schedule> findScheduleForCustomer(Long customerId) {
        Set<Schedule> schedules = new HashSet<>();
        List<Pet> pets = petService.getPetsByOwner(customerId);

        for(int i = 0; i < pets.size(); i++)
            schedules.addAll(findScheduleForPet(pets.get(i).getId()));

        return schedules;
    }
}
