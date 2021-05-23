package com.udacity.jdnd.course3.critter.user.service;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import com.udacity.jdnd.course3.critter.user.dto.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.user.entities.Employee;
import com.udacity.jdnd.course3.critter.user.exceptions.EmployeeNotFoundException;
import com.udacity.jdnd.course3.critter.user.helper.UserServiceHelper;
import com.udacity.jdnd.course3.critter.user.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    /*
        Save Or Update Employee based on id nullability
     */
    @Transactional
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    /*
        Retrieve all employees from the database
     */
    @Transactional
    public Employee findEmployee(Long employeeId){
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);

        if(optionalEmployee.isPresent())
            return optionalEmployee.get();

        throw new EmployeeNotFoundException("Employee Not Found For ID: " + employeeId);
    }

    /*
        Set available days for a specific employee
     */
    @Transactional
    public void setEmployeeAvailability(Long employeeId, Set<DayOfWeek> daysAvailable) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);

        if(!optionalEmployee.isPresent())
            throw new EmployeeNotFoundException("Employee Not Found For ID: " + employeeId);

        // Update available days
        Employee employee = optionalEmployee.get();
        employee.setDaysAvailable(daysAvailable);

        // Resave employee to the database
        employeeRepository.save(employee);
    }

    /*
        Find available employee(s) to do a service and meet employee-request needs
     */
    public List<Employee> assignEmployeesForService(EmployeeRequestDTO employeeRequestDto){
        DayOfWeek day = employeeRequestDto.getDate().getDayOfWeek();
        Set<EmployeeSkill> requiredSkills = employeeRequestDto.getSkills();
        List<Employee> employees = employeeRepository.findByDaysAvailable(day);

        /*
            Note: Maximum number of assigned employees is less than or equal to number of required skills.
            We will try to find one employee to fit all required skills first, if not found
            we will try to find two employees to fit all required skills, then three and so on.

            Approach: Pick the employee that meets maximum number of skills.
         */

        return UserServiceHelper.filterEmployeesBasedOnSkills(employees, requiredSkills);
    }

}
