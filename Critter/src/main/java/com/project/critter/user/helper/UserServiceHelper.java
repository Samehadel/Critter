package com.udacity.jdnd.course3.critter.user.helper;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import com.udacity.jdnd.course3.critter.user.entities.Employee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class UserServiceHelper {

    public static List<Employee> filterEmployeesBasedOnSkills(List<Employee> employees, Set<EmployeeSkill> requiredSkills){
        int numberOfSkills = requiredSkills.size();
        List<Employee> availableEmployees = new ArrayList<>();

        /*
            Filter employees' skills to meet the required skills,
            Then sort descending to pick the employees with maximum overlap with required skills
         */
        for(int i = 0; i < employees.size(); i++){
            Employee emp = employees.get(i);

            emp.getSkills().retainAll(requiredSkills);
        }
        sortDesc(employees);

        for(int i = 0; i < employees.size(); i++){
            Employee emp = employees.get(i);

            emp.getSkills().retainAll(requiredSkills);

            requiredSkills.removeAll(emp.getSkills());
        }
        sortDesc(employees);

        for(int i = 0; i < employees.size(); i++){
            Employee emp = employees.get(i);

            if(emp.getSkills().size() == numberOfSkills){
                availableEmployees.add(emp);
                break;
            }else{
                availableEmployees.add(emp);
                numberOfSkills -= emp.getSkills().size();
            }

            if(numberOfSkills == 0)
                break;
        }

        return availableEmployees;
    }

    private static void sortDesc(List<Employee> employees){
        employees.sort(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return Integer.compare(o2.getSkills().size(), o1.getSkills().size());
            }
        });

    }
}
