package com.project.critter.schedule;

import com.project.critter.pet.Pet;
import com.project.critter.user.entities.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends CrudRepository<Schedule, Long> {
    public List<Schedule> findByPets(Pet pet);
    public List<Schedule> findByEmployees(Employee employee);
}
