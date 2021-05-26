package com.project.critter.schedule;

import com.project.critter.pet.Pet;
import com.project.critter.pet.PetService;
import com.project.critter.user.entities.Employee;
import com.project.critter.user.service.CustomerService;
import com.project.critter.user.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
            Pet pet = petService.findPetById(petId);
            schedule.getPets().add(pet);
        }

        return scheduleRepository.save(schedule);
    }

    @Transactional
    public List<Schedule> findAllSchedules(){
        return (List<Schedule>) scheduleRepository.findAll();
    }

    @Transactional
    public List<Schedule> findScheduleForPet(Long petId){
        Pet pet = petService.findPetById(petId);

        return scheduleRepository.findByPets(pet);
    }

    @Transactional
    public List<Schedule> findScheduleForEmployee(Long employeeId){
        Employee employee = employeeService.findEmployee(employeeId);

        return scheduleRepository.findByEmployees(employee);
    }

    @Transactional
    public Set<Schedule> findScheduleForCustomer(Long customerId) {
        Set<Schedule> schedules = new HashSet<>();
        List<Pet> pets = petService.findPetsByOwner(customerId);

        for(int i = 0; i < pets.size(); i++)
            schedules.addAll(findScheduleForPet(pets.get(i).getId()));

        return schedules;
    }
}
