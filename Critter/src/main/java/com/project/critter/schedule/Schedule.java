package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import com.udacity.jdnd.course3.critter.user.entities.Employee;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
public class Schedule {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany( cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
                 targetEntity = Employee.class)
    private List<Employee> employees = new ArrayList<>();

    @ManyToMany( cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
                 targetEntity = Pet.class)
    private List<Pet> pets = new ArrayList<>();

    @ElementCollection
    private Set<EmployeeSkill> activities = new HashSet<>();

    private LocalDate date;

    public Schedule(){}

    // Setters THEN Getters
    public void setId(Long id) {
        this.id = id;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public Set<EmployeeSkill> getActivities() {
        return activities;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return id.equals(schedule.id) && Objects.equals(employees, schedule.employees) && Objects.equals(pets, schedule.pets) && Objects.equals(activities, schedule.activities) && date.equals(schedule.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employees, pets, activities, date);
    }
}
