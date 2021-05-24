package com.project.critter.user.repository;

import com.project.critter.user.entities.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long>{

    @Transactional
    public List<Employee> findByDaysAvailable(DayOfWeek dayOfWeek);
}
